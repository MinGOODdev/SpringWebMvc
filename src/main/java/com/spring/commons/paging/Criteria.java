package com.spring.commons.paging;

public class Criteria {

  private int page;             // 현재 페이지 번호
  private int perPageNum;       // 페이지 당 출력되는 게시글의 수

  public Criteria() {
    this.page = 1;
    this.perPageNum = 10;
  }

  public void setPage(int page) {
    if (page <= 0) {
      this.page = 1;
      return;
    }
    this.page = page;
  }

  public int getPage() {
    return page;
  }

  public void setPerPageNum(int perPageNum) {
    if (perPageNum <= 0 || perPageNum > 100) {
      this.perPageNum = 10;
      return;
    }
    this.perPageNum = perPageNum;
  }

  public int getPerPageNum() {
    return this.perPageNum;
  }

  public int getPageStart() {
    return (this.page - 1) * perPageNum;
  }

}
