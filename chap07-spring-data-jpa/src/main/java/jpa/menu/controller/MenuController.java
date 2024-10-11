package jpa.menu.controller;

import jpa.menu.dto.MenuDTO;
import jpa.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/* 설명. @Slf4j(Simple Logging Facade for Java):
 *  Lombok 라이브러리의 어노테이션으로 클래스에 자동으로 SLF4J Logger 인스턴스를 추가해준다.
 *  따라서 개발자는 코드에 별도의 Logger 객체 선언 없이 'log' 변수를 사용해 바로 로깅 가능하다.
 *  로깅 프레임워크에 종속되지 않는 방식으로 로깅 인터페이스를 사용할 수 있게 해준다.
 * */
@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
//    private final Logger log = LoggerFactory.getLogger(getClass());
//    private final Logger log = LoggerFactory.getLogger("내맘대로");

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 설명. Spring Data JPA의 페이징 처리 미적용 */
    @GetMapping("/list")
    public String findMenuList(Model model) {

        List<MenuDTO> menuList = menuService.findMenuList();

        model.addAttribute("menuList", menuList);

        return "menu/list";
    }

    @GetMapping("/{menuCode}")
    public String findMenuByCode(Model model, @PathVariable int menuCode) {

//        System.out.println("menuCode = " + menuCode);
        log.info("input menuCode : {}", menuCode);

        MenuDTO foundMenu = menuService.findMenuByCode(menuCode);

        model.addAttribute("menu", foundMenu);

        return "menu/detail";
    }

    /* Spring Data JPA 페이징 처리할 때 */
    @GetMapping("/querymethod")
    public void queryMethodPage() {}

    @GetMapping("/search")
    public String findByMenuPrice(@RequestParam Integer menuPrice, Model model) {

        List<MenuDTO> menuList = menuService.findByMenuPrice(menuPrice);

        model.addAttribute("menuList", menuList);
        model.addAttribute("menuPrice", menuPrice);

        return "menu/searchResult";

    }

	@GetMapping("/delete")
	public void deletePage() {}
	
	@PostMapping("/delete")
	public String deleteMenu(@RequestParam Integer menuCode) {

		menuService.deleteMenu(menuCode);
		
		return "redirect:/menu/list";
	}
}
