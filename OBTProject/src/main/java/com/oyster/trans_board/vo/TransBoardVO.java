package com.oyster.trans_board.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("transBoardVO")
public class TransBoardVO {
	private int tb_number;/* 거래글 번호 */
	private String tb_title; /* 거래글 제목 */
	private String tb_content; /* 거래글 내용 */
	private Date write_date;/* 작성일 */
	private int tb_countnum; /* 조회수 */
	private int tb_likenum; /* 좋아요 */
	private String member_id; /* 아이디 */
	
	
	public int getTb_number() {
		return tb_number;
	}
	public void setTb_number(int tb_number) {
		this.tb_number = tb_number;
	}
	public String getTb_title() {
		return tb_title;
	}
	public void setTb_title(String tb_title) {
		this.tb_title = tb_title;
	}
	public String getTb_content() {
		return tb_content;
	}
	public void setTb_content(String tb_content) {
		this.tb_content = tb_content;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public int getTb_countnum() {
		return tb_countnum;
	}
	public void setTb_countnum(int tb_countnum) {
		this.tb_countnum = tb_countnum;
	}
	public int getTb_likenum() {
		return tb_likenum;
	}
	public void setTb_likenum(int tb_likenum) {
		this.tb_likenum = tb_likenum;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


}
