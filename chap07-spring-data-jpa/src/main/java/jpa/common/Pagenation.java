package com.ohgiraffers.springdatajpa.common;

import org.springframework.data.domain.Page;

/**
 * 페이지네이션의 시작 페이지와 끝 페이지를 계산하는 유틸리티 클래스.
 *
 * <p>Spring Data의 {@link org.springframework.data.domain.Page} 객체를 사용하여
 * 현재 페이지, 시작 페이지, 끝 페이지 정보를 계산한다.</p>
 * <p>기본적으로 한 번에 10개의 페이지 버튼이 표시되도록 설정되어 있으며,
 * 필요에 따라 현재 페이지와 총 페이지 개수에 따라 값이 변동될 수 있다.</p>
 */
public class Pagenation {

	/**
	 * 현재 페이지와 총 페이지 수를 바탕으로 페이지 네이션 버튼의 시작 페이지와 끝 페이지를 계산한다.
	 *
	 * <p>현재 페이지 번호는 0부터 시작하므로 1을 더하여 1부터 시작하도록 한다.</p>
	 * <p>기본적으로 한 번에 표시할 페이지 버튼의 수는 10개로 설정된다.</p>
	 * <p>시작 페이지는 현재 페이지가 속한 블록의 첫 페이지로 계산되며, 끝 페이지는 시작 페이지에서
	 * 10을 더한 값이다. 총 페이지 수가 끝 페이지보다 적을 경우, 끝 페이지는 총 페이지 수로 변경된다.</p>
	 *
	 * @param page {@link org.springframework.data.domain.Page} 객체로,
	 *  현재 페이지 정보와 총 페이지 수를 관리한다.
	 * @return {@link PagingButtonInfo} 객체로, 현재 페이지, 시작 페이지, 끝 페이지 정보를 관리한다.
	 */
	public static PagingButtonInfo getPagingButtonInfo(Page page) {
		
		int currentPage = page.getNumber() + 1;
		int defaultButtonCount = 10;
		int startPage;
		int endPage;
		
		startPage = (int) (Math.ceil((double) currentPage / defaultButtonCount) - 1) * defaultButtonCount + 1;
		endPage = startPage + defaultButtonCount - 1;
		
		if(page.getTotalPages() < endPage)
			endPage = page.getTotalPages();
		
		if(page.getTotalPages() == 0 && endPage == 0)
			endPage = startPage;
		
		return new PagingButtonInfo(currentPage, startPage, endPage);
	}

}
