package com.sk.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sk.board.dtos.BoardDto;
import com.sk.board.dtos.MemberDto;

@Mapper
public interface BoardMapper {

	//글목록
	public List<BoardDto> getAllList();
	
	//글목록
	public List<BoardDto> getMyList(String id);
	
	//글상세조회
	public BoardDto getBoard(int board_seq);
	//글추가
	public boolean insertBoard(BoardDto dto);
	//글 수정
	public boolean updateBoard(BoardDto dto);
	//글 삭제
	public boolean mulDel(String[] seqs);

	public boolean deleteBoard(int board_seq);

	public List<BoardDto> searchBoard(String searchInput);
	
}







