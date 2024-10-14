package jpa.menu.controller;

import jpa.common.Pagenation;
import jpa.common.PagingButtonInfo;
import jpa.menu.dto.MenuDTO;
import jpa.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String findMenuList(@PageableDefault Pageable pageable, Model model) {

        /* page -> number, size, sort 파라미터가 Pageable 객체에 담긴다. */
        log.info("pageable : {}", pageable);

        Page<MenuDTO> menuList = menuService.findMenuList(pageable);

        log.info("조회한 내용 목록 : {}", menuList.getContent());
        log.info("총 페이지 수 : {}", menuList.getTotalPages());
        log.info("총 메뉴 수 : {}", menuList.getTotalElements());
        log.info("해당 페이지에 표시 될 요소 수 : {}", menuList.getSize());
        log.info("해당 페이지에 실제 요소 수 : {}", menuList.getNumberOfElements());
        log.info("첫 페이지 여부 : {}", menuList.isFirst());
        log.info("마지막 페이지 여부 : {}", menuList.isLast());
        log.info("정렬 방식 : {}", menuList.getSort());
        log.info("여러 페이지 중 현재 인덱스 : {}", menuList.getNumber());

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(menuList);

        model.addAttribute("paging", paging);
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

        System.out.println("main 확인용 sout");

        return "menu/searchResult";
    }


}
