package com.sk.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sk.board.dtos.CommentDto;
import com.sk.board.dtos.MemberDto;
import com.sk.board.dtos.ReplyDto;
import com.sk.board.service.BoardService;
import com.sk.board.service.CommentService;
import com.sk.board.service.FileService;
import com.sk.board.service.MemberService;
import com.sk.board.service.ReplyService;
import com.sk.board.service.SearchService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private FileService fileService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping(value = "/addComment")
	public String commentInsert(@RequestParam int board_seq,
	        @RequestParam String commentContent,
	        HttpServletRequest request, Model model) {
		
		System.out.println(board_seq);
		MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
		
		if (mdto != null) {
	        // Create a CommentDto object
	        CommentDto commentDto = new CommentDto();
	        commentDto.setBoard_seq(board_seq);
	        commentDto.setId(mdto.getId());
	        commentDto.setContent(commentContent);
	        
	        // Call the service to save the comment
	        commentService.addComment(commentDto);
	        System.out.println(commentDto);
	    } else {
	        // If not logged in, redirect to login page
	        return "redirect:/user/login";
	    }
	    
		return "redirect:/board/boardDetail?board_seq=" + board_seq;
	}
	
	@GetMapping(value = "/deleteComment")
	public String commentDelete(int board_seq, int comment_seq) {
		commentService.deleteComment(comment_seq);
		return "redirect:/board/boardDetail?board_seq=" + board_seq;
	}
	@GetMapping(value = "/deleteReply")
	public String replyDelete(int board_seq, int reply_seq) {
		replyService.deleteReply(reply_seq);
		return "redirect:/board/boardDetail?board_seq=" + board_seq;
	}
	
	@PostMapping(value = "/addReply")
	public String replyInsert(int board_seq, int comment_seq, String replyContent, HttpServletRequest request) {
		MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
		
		if (mdto != null) {
			ReplyDto replyDto = new ReplyDto();
			replyDto.setComment_seq(comment_seq);
			replyDto.setId(mdto.getId());
			replyDto.setContent(replyContent);
	        
	        replyService.addReply(replyDto);
	        System.out.println(replyDto);
	    } else {
	        // If not logged in, redirect to login page
	        return "redirect:/user/login";
	    }
		
		
		return "redirect:/board/boardDetail?board_seq=" + board_seq;
	}
}
