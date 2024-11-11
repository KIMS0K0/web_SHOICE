package com.sk.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.sk.board.dtos.BookmarkDto;
import com.sk.board.dtos.CommentDto;
import com.sk.board.dtos.FileBoardDto;
import com.sk.board.dtos.MemberDto;
import com.sk.board.service.BoardService;
import com.sk.board.service.BookmarkService;
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
	@Autowired
	private BookmarkService bookmarkService;
	
	@GetMapping(value = "/boardList")
	public String boardList(HttpServletRequest request, Model model) {
		System.out.println("글목록 보기");
		
		List<BoardDto> list=boardService.getAllList();
		System.out.println("보드 개수: " + list.size());
		model.addAttribute("length", list.size());
		model.addAttribute("list", list);
		model.addAttribute("delBoardCommand", new DelBoardCommand());
		
		return "board/boardList";// forward 기능, "redirect:board/boardList"
	}

	@GetMapping(value = "/boardSearch")
	public String boardSearch(HttpServletRequest request, Model model, @RequestParam(value = "search_input", required = false) String searchInput) {
		System.out.println("검색 보드 보기");
		
		List<BoardDto> list=null;
		
		if (searchInput != null && !searchInput.isEmpty()) {
			list=boardService.searchBoard(searchInput);
		}
		else return "redirect:/board/boardList";
		model.addAttribute("length", list.size());
		model.addAttribute("search_input", searchInput);
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
	    
	    MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
	    if (mdto != null) {
	    	BookmarkDto fdto = new BookmarkDto(0,mdto.getId(),board_seq);
		    System.out.println("id: " + mdto.getId());
		    System.out.println("board_seq: " + board_seq);
		    int bookmark = bookmarkService.getBookmark(fdto);
		    System.out.println("북마크 여부: " + bookmark);
		    model.addAttribute("bookmark",bookmark);
	    }
	    
	    // 해당 게시물의 댓글 가져오기
	    List<CommentDto> comments = commentService.getComments(board_seq);
	    System.out.println("ㅠㅠ" + comments.size());
	    for(int i = 0; i < comments.size(); i ++) {
	    	System.out.println(i+ ": " + comments.get(i).getReplies());
	    }
	    
		model.addAttribute("comments", comments);		
		
	    return "board/boardDetail";
	}

	@GetMapping(value = "/boardUpdate")
	public String boardUpdate(int board_seq, HttpServletRequest request, Model model) {
		BoardDto dto = boardService.getBoard(board_seq);
	    System.out.println("업데이트 " + dto);
	    // 유효값처리용
	    model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
	    // 출력용
	    model.addAttribute("dto", dto);
	    
	    MemberDto bdto = searchService.getUserwithProfile(dto.getId());
	    
	    model.addAttribute("bdto", bdto);
		return "board/boardUpdate";
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
	
	@GetMapping(value = "/boardDelete")
	public String boardDelete(int board_seq, HttpServletRequest request, Model model) {
		System.out.println("글삭제함");
		boardService.deleteBoard(board_seq);
		return "redirect:/board/boardList";
	}
}














