package jpa.menu.service;

import jakarta.transaction.Transactional;
import jpa.menu.dto.MenuDTO;
import jpa.menu.entity.Menu;
import jpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.modelMapper = modelMapper;
    }

    public List<MenuDTO> findMenuList() {
        List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode").descending());

        // 1. 람다식사용
        return menuList
                .stream()
                .map(m -> modelMapper.map(m, MenuDTO.class))
                .toList();
        // 2. for문 사용
//        List<MenuDTO> menuDTOList = new ArrayList<>();
//
//        for(Menu m : menuList){
//            MenuDTO menuDTO = modelMapper.map(m, MenuDTO.class);
//            menuDTOList.add(menuDTO);
//        }
//
//        return menuDTOList;
    }

    public MenuDTO findMenuByCode(int menuCode) {

        Menu foundMenu = menuRepository.findById(menuCode)
                                        .orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(foundMenu, MenuDTO.class);
    }

    public List<MenuDTO> findByMenuPrice(Integer menuPrice) {

//        List<Menu> menuList = menuRepository.findMenuByMenuPriceGreaterThan(menuPrice);
//        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice);
//        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);
        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice, Sort.by("menuName").ascending());

        return menuList
                .stream()
                .map(m -> modelMapper.map(m, MenuDTO.class))
                .toList();
    }

    	/* 목차. 5. JPQL or native query */
	// CategoryService 참고

	/* 목차. 6. save - 엔티티 저장 */
	@Transactional
	public void registNewMenu(MenuDTO newMenu) {
		
		/* 설명. CrudRepository에 미리 정의 되어 있는 save() 메소드를 통해 하나의 엔티티를 저장할 수 있다. */
		menuRepository.save(modelMapper.map(newMenu, Menu.class));
	}

	/* 목차. 7. 수정하기 - 엔티티 조회 후 값 변경 */
	@Transactional
	public void modifyMenu(MenuDTO modifyMenu) {

		/* Note. Menu 엔티티의 @Setter 작성 후 수행 */
		Menu foundMenu = menuRepository.findById(modifyMenu.getMenuCode()).orElseThrow(IllegalArgumentException::new);
		foundMenu.setMenuName(modifyMenu.getMenuName());
	}

	/* 목차. 8. delete */
	@Transactional
	public void deleteMenu(Integer menuCode) {
		menuRepository.deleteById(menuCode);
	}
}
