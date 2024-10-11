package com.ohgiraffers.springdatajpa.common;

/**
 * 페이지네이션 버튼 정보(현재 페이지, 시작 페이지, 끝 페이지)를 저장하는 클래스.
 *
 * <p>{@link Pagenation} 클래스에서 사용되며,
 * 페이지네이션의 버튼 배치를 위해 필요한 정보를 관리한다.</p>
 */
public class PagingButtonInfo {
	
	private int currentPage;
	private int startPage;
	private int endPage;
	
	public PagingButtonInfo() {}

	public PagingButtonInfo(int currentPage, int startPage, int endPage) {
		super();
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PagingButtonInfo [currentPage=" + currentPage + ", startPage=" + startPage + ", endPage=" + endPage
				+ "]";
	}

}
