package jpa.menu.repository;

import jpa.menu.entity.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findMenuByMenuPriceGreaterThan(Integer menuPrice);

    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice);

    List<Menu> findByMenuPriceGreaterThan(Integer menuPrice, Sort menuName);

    List<Menu> findByMenuPriceGreaterThanOrderByMenuPrice(Integer menuPrice);
}
