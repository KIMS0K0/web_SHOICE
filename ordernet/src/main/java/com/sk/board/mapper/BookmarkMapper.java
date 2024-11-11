package com.sk.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sk.board.dtos.BoardDto;
import com.sk.board.dtos.BookmarkDto;

@Mapper
public interface BookmarkMapper {

	public int getBookmark(BookmarkDto fdto);

	public boolean insertBookmark(BookmarkDto fdto);

	public boolean deleteBookmark(BookmarkDto fdto);

	public List<BoardDto> getAllList(String id);
	
}
