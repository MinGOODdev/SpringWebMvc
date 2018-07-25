package com.spring.commons.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PageMaker {

  private int totalCount;                   // 전체 게시글 수
  private int startPage;
  private int endPage;
  private boolean prev;
  private boolean next;
  private int displayPageNumCount = 10;     // 뷰 하단의 페이지 번호의 수

  private Criteria criteria;

  public Criteria getCriteria() {
    return criteria;
  }

  public void setCriteria(Criteria criteria) {
    this.criteria = criteria;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
    calculateData();
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

  public boolean isPrev() {
    return prev;
  }

  public void setPrev(boolean prev) {
    this.prev = prev;
  }

  public boolean isNext() {
    return next;
  }

  public void setNext(boolean next) {
    this.next = next;
  }

  public int getDisplayPageNumCount() {
    return displayPageNumCount;
  }

  public void setDisplayPageNumCount(int displayPageNumCount) {
    this.displayPageNumCount = displayPageNumCount;
  }

  public String makeQuery(int page) {
    UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .queryParam("page", page)
            .queryParam("perPageNum", criteria.getPerPageNum())
            .build();

    return uriComponents.toUriString();
  }

  private void calculateData() {
    endPage = (int) (Math.ceil(criteria.getPage() / (double) this.displayPageNumCount) * this.displayPageNumCount);
    startPage = (endPage - this.displayPageNumCount) + 1;
    int tempEndPage = (int) (Math.ceil(this.totalCount / (double) criteria.getPerPageNum()));

    if (endPage > tempEndPage) {
      endPage = tempEndPage;
    }
    prev = (startPage == 1) ? false : true;
    next = ((endPage * criteria.getPerPageNum()) >= this.totalCount) ? false : true;
  }

  public String makeSearch(int page) {
    UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .queryParam("page", page)
            .queryParam("perPageNum", criteria.getPerPageNum())
            .queryParam("searchType", ((SearchCriteria) criteria).getSearchType())
            .queryParam("keyword", encoding(((SearchCriteria) criteria).getKeyword()))
            .build();

    return uriComponents.toUriString();
  }

  public String encoding(String keyword) {
    if (keyword == null || keyword.trim().length() == 0) return "";

    try {
      return URLEncoder.encode(keyword, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

}
