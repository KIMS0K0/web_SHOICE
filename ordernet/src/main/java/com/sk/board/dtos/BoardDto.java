package com.sk.board.dtos;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
//@Data
@Alias(value = "boardDto") //mapper.xml에서 사용하는 변수명 설정
public class BoardDto {
	private int board_seq;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	private String delflag;
	
	private String name;
	private int memberid;
	
	//Join용 맴버필드
	private List<FileBoardDto> fileBoardDto;
	
	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int board_seq, String id, String title, String content, Date regdate, String delflag, String name,
			int memberid, List<FileBoardDto> fileBoardDto) {
		super();
		this.board_seq = board_seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.delflag = delflag;
		this.name = name;
		this.memberid = memberid;
		this.fileBoardDto = fileBoardDto;
	}

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public List<FileBoardDto> getFileBoardDto() {
		return fileBoardDto;
	}

	public void setFileBoardDto(List<FileBoardDto> fileBoardDto) {
		this.fileBoardDto = fileBoardDto;
	}

	@Override
	public String toString() {
		return "BoardDto [board_seq=" + board_seq + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", delflag=" + delflag + ", name=" + name + ", memberid=" + memberid
				+ ", fileBoardDto=" + fileBoardDto + "]";
	}

}
