package com.sk.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sk.board.dtos.BoardDto;
import com.sk.board.dtos.BookmarkDto;
import com.sk.board.dtos.MemberDto;
import com.sk.board.service.BookmarkService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/bookmark")
public class BookmarkController {

	@Autowired private BookmarkService bookmarkService;
	
	@GetMapping(value = "/bookmarklistGet")
	public String bookmarklistGet(HttpServletRequest request, Model model) {
		MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
		List<BoardDto> list = null;
		list = bookmarkService.getAllList(mdto.getId());
		model.addAttribute("list", list);
		return "/bookmark/bookmarkList";
	}
	
	@GetMapping(value = "/bookmarkInsert")
	public String bookmarkInsert(int board_seq, HttpServletRequest request) {
		MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
		BookmarkDto fdto = new BookmarkDto(0, mdto.getId(), board_seq);
		
		if (bookmarkService.insertBookmark(fdto)) {
			System.out.println("북마크 삽입 성공");
		}
		return "redirect:/board/boardDetail?board_seq="+board_seq;
	}
	
	@GetMapping(value = "/bookmarkDelete")
	public String bookmarkDelete(int board_seq, HttpServletRequest request) {
		
		MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
		BookmarkDto fdto = new BookmarkDto(0, mdto.getId(), board_seq);
		
		if (bookmarkService.deleteBookmark(fdto)) {
			System.out.println("북마크 삭제 성공");
		}
		
		return "redirect:/board/boardDetail?board_seq="+board_seq;
	}
}
