package com.spring.article.dto;

import java.util.Date;

public class Reply {

  private Integer replyNo;
  private Integer articleNo;
  private String replyText;
  private String replyWriter;
  private Date regDate;
  private Date updateDate;

  public Integer getReplyNo() {
    return replyNo;
  }

  public void setReplyNo(Integer replyNo) {
    this.replyNo = replyNo;
  }

  public Integer getArticleNo() {
    return articleNo;
  }

  public void setArticleNo(Integer articleNo) {
    this.articleNo = articleNo;
  }

  public String getReplyText() {
    return replyText;
  }

  public void setReplyText(String replyText) {
    this.replyText = replyText;
  }

  public String getReplyWriter() {
    return replyWriter;
  }

  public void setReplyWriter(String replyWriter) {
    this.replyWriter = replyWriter;
  }

  public Date getRegDate() {
    return regDate;
  }

  public void setRegDate(Date regDate) {
    this.regDate = regDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

}
