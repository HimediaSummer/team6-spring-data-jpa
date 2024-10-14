package jpa.category.controller;

import jpa.category.dto.CategoryDTO;
import jpa.category.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/category")
public class CateogoryController {

    private final CategoryService categoryService;

    /* 설명. CategoryService 생성자 주입 */
    // @Autowired를 작성하지 않아도 자동 적용됨을 잊지 말자.
    public CateogoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /* 설명. fetch() 비동기 요청
     * 클라이언트의 브라우저에서 /menu/regist.html이 열리자마자
     * JS의 fetch() 함수를 통해 'GET /category/list' 비동기 HTTP 요청이 SpringBoot 측으로 날라온다.
     * */
    @GetMapping(value="/list", produces="application/json; charset=UTF-8")
    @ResponseBody
    /* 설명. @ResponseBody
     *  1) 메소드에 @ResponseBody가 붙은 메소드의 반환형은 View Resolver가 해석하지 않는다.
     *     즉, 다른 핸들러 메서드와는 다르게, 이 핸들러 메서드는 Thymeleaf 화면으로 응답하지 않음.
     *  2) 핸들러 메소드의 반환형은 don't care: 어차피 요청측에 반환값을 모두 JSON 문자열 처리되어 응답한다.
     *     즉, List<CategoryDTO>를 반환한다면 JSON 문자열에서는 CategoryDTO라는 객체의 배열(객체 배열)로 표시된다.
     *  3) 한글이 포함된 데이터는 produces 속성에 'application/json'라는 MIME 타입과 charset=UTF-8' 인코딩 타입을 설정해줘야 한다.
     *     지금까지 우리가 작성해온 HTTP GET 요청에 대한 응답의 MIME 타입은 'text/html'이라는 것을 잊지 말자.
     *     ex)
     *     HTTP/1.1 200 OK
     *     Content-Type: text/html;charset=UTF-8
     * */
    public List<CategoryDTO> findCategoryList() {

        return categoryService.findAllCategory();
    }
}
