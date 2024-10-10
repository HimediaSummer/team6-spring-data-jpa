package com.ohgiraffers.springdatajpa.category.service;

import com.ohgiraffers.springdatajpa.category.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.category.entity.Category;
import com.ohgiraffers.springdatajpa.category.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    // @Autowired를 작성하지 않아도 자동 적용됨을 잊지 말자.
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    /* 목차. 5. JPQL or native query */
    public List<CategoryDTO> findAllCategory() {

        /* 설명. findAll() 메소드를 사용해 전체 카테고리를 조회하는 것이 당연히 가능하다.
         *  하지만 여기서는 SpringDataJPA에서 JPQL이나 Native Query를 사용하고자 할 때 어떻게 설정해야 하는지를 볼 것이다.
         *  따라서 CategoryRepository에 Native Query를 사용해 직접 메서드를 정의할 것이다.
         * */
        List<Category> categoryList = categoryRepository.findAllCategory();

        // 람다식 사용 버전
        return categoryList
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());

        // 람다식 대신 for문 사용한 버전
//		List<CategoryDTO> categoryDTOList = new ArrayList<>();
//		for (Category category : categoryList) {
//			CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
//			categoryDTOList.add(categoryDTO);
//		}
//		return categoryDTOList;
    }
}
