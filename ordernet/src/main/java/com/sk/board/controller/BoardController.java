package com.sk.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.sk.board.command.DelBoardCommand;
import com.sk.board.command.InsertBoardCommand;
import com.sk.board.command.UpdateBoardCommand;
import com.sk.board.dtos.BoardDto;
import com.sk.board.dtos.CommentDto;
import com.sk.board.dtos.FileBoardDto;
import com.sk.board.dtos.MemberDto;
import com.sk.board.service.BoardService;
import com.sk.board.service.CommentService;
import com.sk.board.service.FileService;
import com.sk.board.service.MemberService;
import com.sk.board.service.SearchService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
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
	
	@GetMapping(value = "/boardList")
	public String boardList(Model model) {
		System.out.println("글목록 보기");
		
		List<BoardDto> list=boardService.getAllList();
		model.addAttribute("list", list);
		model.addAttribute("delBoardCommand", new DelBoardCommand());
		return "board/boardList";// forward 기능, "redirect:board/boardList"
	}
	
	@GetMapping(value = "/boardInsert")
	public String boardInsertForm(Model model) {
		model.addAttribute("insertBoardCommand", new InsertBoardCommand());
		return "board/boardInsertForm";
	}
	
	@PostMapping(value = "/boardInsert")
	public String boardInsert(@Validated InsertBoardCommand insertBoardCommand 
			                ,BindingResult result
			                ,MultipartRequest multipartRequest //multipart data를 처리할때 사용
							,HttpServletRequest request
			                ,Model model) throws IllegalStateException, IOException {
		if(result.hasErrors()) {
			System.out.println("글을 모두 입력하세요");
			return "board/boardInsertForm";
		}
		
		boardService.insertBoard(insertBoardCommand,multipartRequest
				                ,request);
		
		return "redirect:/board/boardList";
	}
	
	@PostMapping(value = "/addComment")
	public String commentInsert(@RequestParam("board_seq") int board_seq,
	        @RequestParam("commentContent") String commentContent,
	        HttpServletRequest request, Model model) {
		
		System.out.println(board_seq);
		System.out.println(request.getSession().getAttribute("mdto"));
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
	
	// 상세보기
	@GetMapping(value = "/boardDetail")
	public String boardDetail(int board_seq, HttpServletRequest request, Model model) {
	    BoardDto dto = boardService.getBoard(board_seq);
	    
	    System.out.println(dto);
	    // 유효값처리용
	    model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
	    // 출력용
	    model.addAttribute("dto", dto);
	    
	    MemberDto bdto = searchService.getUserwithProfile(dto.getId());
	    
	    model.addAttribute("bdto", bdto);
	    
	    // 해당 게시물의 댓글 가져오기
	    List<CommentDto> comments = commentService.getComments(board_seq);
	    for (CommentDto comment : comments) {
	        System.out.println(comment);
	    }

		
		 model.addAttribute("comments", comments);		 
	    
	    return "board/boardDetail";
	}

	
	//수정하기
	@PostMapping(value = "/boardUpdate")
	public String boardUpdate(
				@Validated UpdateBoardCommand updateBoardCommand
				,BindingResult result
				,Model model) {
		
		if(result.hasErrors()) {
			System.out.println("수정내용을 모두 입력하세요");
			//코드 추가--------------------------------------------
			BoardDto dto=boardService.getBoard(updateBoardCommand.getBoard_seq());
			model.addAttribute("dto", dto);
			//--------------------------------------------------
			return "board/boardDetail";
		}
		
		boardService.updateBoard(updateBoardCommand);
		
		return "redirect:/board/boardDetail?board_seq="
				+ updateBoardCommand.getBoard_seq();
	}
	
	@GetMapping(value = "/download")
	public void download(int file_seq, HttpServletRequest request
			                         , HttpServletResponse response) throws UnsupportedEncodingException {
		
		FileBoardDto fdto=fileService.getFileInfo(file_seq);//파일정보가져오기
		
		fileService.fileDownload(fdto.getOrigin_name()
				                ,fdto.getStored_name()
				                ,request,response);
	}
	
	@RequestMapping(value="mulDel",method = {RequestMethod.POST,RequestMethod.GET})
	public String mulDel(@Validated DelBoardCommand delBoardCommand
						 ,BindingResult result
			             , Model model) {
		if(result.hasErrors()) {
			System.out.println("최소하나 체크하기");
			List<BoardDto> list=boardService.getAllList();
			model.addAttribute("list", list);
			return "board/boardlist";
		}
		boardService.mulDel(delBoardCommand.getSeq());
		System.out.println("글삭제함");
		return "redirect:/board/boardList";
	}
}














