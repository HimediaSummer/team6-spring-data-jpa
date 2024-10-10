package com.ohgiraffers.springdatajpa.category.repository;

import java.util.List;

import com.ohgiraffers.springdatajpa.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository <Category, Integer> {
	
	/* 설명. @Query 어노테이션을 사용해 JPQL 작성시에는 value만 작성해도 되지만,
	 * Native Query를 작성할 때는 반드시 'nativeQuery' 속성을 true로 설정해야 한다.
	 * */
	@Query(value="SELECT c.category_code, c.category_name, c.ref_category_code " +
				 "FROM tbl_category c " +
				 "ORDER BY c.category_code ASC",
			nativeQuery = true)
	public List<Category> findAllCategory();
	
}
