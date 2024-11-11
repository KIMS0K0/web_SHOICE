package com.sk.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.board.dtos.BoardDto;
import com.sk.board.dtos.BookmarkDto;
import com.sk.board.mapper.BookmarkMapper;

@Service
public class BookmarkService {
    @Autowired
    private BookmarkMapper bookmarkMapper;

	public int getBookmark(BookmarkDto fdto) {
		return bookmarkMapper.getBookmark(fdto);
	}

	public boolean insertBookmark(BookmarkDto fdto) {
		return bookmarkMapper.insertBookmark(fdto);
	}

	public boolean deleteBookmark(BookmarkDto fdto) {
		return bookmarkMapper.deleteBookmark(fdto);
	}

	public List<BoardDto> getAllList(String id) {
		return bookmarkMapper.getAllList(id);
	}
}
