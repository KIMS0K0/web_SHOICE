package com.sk.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sk.board.command.AddUserCommand;
import com.sk.board.command.LoginCommand;
import com.sk.board.dtos.BackgroundDto;
import com.sk.board.dtos.BoardDto;
import com.sk.board.dtos.FollowDto;
import com.sk.board.dtos.MemberDto;
import com.sk.board.dtos.ProfileDto;
import com.sk.board.service.BackgroundService;
import com.sk.board.service.BoardService;
import com.sk.board.service.FollowService;
import com.sk.board.service.MemberService;
import com.sk.board.service.ProfileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BackgroundService backgroundService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired private FollowService followService;
	
	@GetMapping(value = "/addUser")
	public String addUserForm(Model model) {
		System.out.println("회원가입폼 이동");
		
		//회원가입폼에서 addUserCommand객체를 사용하는 코드가 작성되어 있어서 
		//null일경우 오류가 발생하기때문에 보내줘야 함
		model.addAttribute("addUserCommand", new AddUserCommand());
		
		return "member/addUserForm";
	}
	
	@PostMapping(value = "/addUser")
	public String addUser(@Validated AddUserCommand addUserCommand
			             ,BindingResult result,Model model) {
		System.out.println("회원가입하기");
		
		if(result.hasErrors()) {
			System.out.println("회원가입 유효값 오류");
			return "member/addUserForm";
		}
		
		try {
			memberService.addUser(addUserCommand);
			System.out.println("회원가입 성공");
			return "redirect:/";
		} catch (Exception e) {
			System.out.println("회원가입실패");
			e.printStackTrace();
			return "redirect:addUser";
		}

	}
	
	@ResponseBody
	@GetMapping(value = "/idChk")
	public Map<String,String> idChk(String id){
		System.out.println("ID중복체크");
		
		String resultId=memberService.idChk(id);
		// json객체로 보내기 위해 Map에 담아서 응답
		// text라면 그냥 String으로 보내도 됨
		Map<String,String>map=new HashMap<>();
		map.put("id", resultId);
		return map;
	}
	
	//로그인 폼 이동
	@GetMapping(value = "/login")
	public String loginForm(HttpServletRequest request, Model model) {
	    String referer = request.getHeader("Referer");
	    // 이전 페이지가 null이 아니고 로그인 페이지가 아닐 때만 세션에 저장
	    if (referer != null && !referer.contains("/login")) { 
	        request.getSession().setAttribute("prevPage", referer);
	    }
	    model.addAttribute("loginCommand", new LoginCommand());
	    return "member/login";
	}

	//로그인 실행
	@PostMapping(value = "/login")
	public String login(@Validated LoginCommand loginCommand
			           ,BindingResult result
			           ,Model model
			           ,HttpServletRequest request) {
		if(result.hasErrors()) {
			System.out.println("로그인 유효값 오류");
			return "member/login";
		}
		
		String path=memberService.login(loginCommand, request, model);
		
		return path;
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("로그아웃");
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	// 나의 정보 조회
	@GetMapping(value="/myPage")
	public String getMyPage(HttpServletRequest request, Model model) {
	    System.out.println("마이페이지 이동");

	    // 세션에서 로그인 정보를 가져옴
	    MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
	    
	    if (mdto == null) {
	        // 로그인 정보가 없으면 로그인 페이지로 리디렉션
	        return "redirect:/user/login";
	    }
		System.out.println(mdto.getMemberId());
		
		// Background와 Profile 정보 가져오기
	    BackgroundDto backgroundDto = backgroundService.getFileInfo(mdto.getId());
	    ProfileDto profileDto = profileService.getFileInfo(mdto.getId());

	    // 모델에 추가
	    model.addAttribute("backgroundDto", backgroundDto);
	    model.addAttribute("profileDto", profileDto);
	    
	    // 로그인된 사용자의 게시글만 조회
	    List<BoardDto> list = boardService.getAllMyList(mdto);
	    model.addAttribute("list", list);

	    
	    // 마이페이지로 이동
	    return "member/myPage";
	}
	
	// 나의 정보 조회
	@GetMapping(value="/yourPage")
	public String getYourPage(int yourid, HttpServletRequest request, Model model) {
	    // 세션에서 로그인 정보를 가져옴
	    MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
	    
	    if (mdto == null) {
	        // 로그인 정보가 없으면 로그인 페이지로 리디렉션
	        return "redirect:/user/login";
	    }
	    System.out.println(mdto.getMemberId());
	    System.out.println(yourid);
		
		if (mdto.getMemberId() == yourid) return "redirect:/user/myPage";
		
		MemberDto ydto = memberService.getAllInfo(yourid);
	    System.out.println(ydto);
	    model.addAttribute("ydto", ydto);
	    System.out.println(model.getAttribute("ydto"));
		
		// Background와 Profile 정보 가져오기
	    BackgroundDto backgroundDto = backgroundService.getFileInfo(ydto.getId());
	    ProfileDto profileDto = profileService.getFileInfo(ydto.getId());

	    // 모델에 추가
	    model.addAttribute("backgroundDto", backgroundDto);
	    model.addAttribute("profileDto", profileDto);
	    
	    System.out.println("나: " + mdto.getId());
	    System.out.println("너: " + ydto.getId());
	    
	    FollowDto fdto = new FollowDto(0, mdto.getId(),ydto.getId());
	    int follow = followService.getFollow(fdto);
	    System.out.println("팔로우 여부: " + follow);
	    model.addAttribute("follow",follow);
	    
	    // 로그인된 사용자의 게시글만 조회
	    List<BoardDto> list = boardService.getAllMyList(ydto);
	    model.addAttribute("list", list);

	    // 마이페이지로 이동
	    return "member/yourPage";
	}
	
	@GetMapping(value="/update")
	public String updateMyPage(HttpServletRequest request, Model model) {
	    System.out.println("업데이트 페이지 이동");

	    // 세션에서 로그인 정보를 가져옴
	    MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
	    
	    if (mdto == null) {
	        // 로그인 정보가 없으면 로그인 페이지로 리디렉션
	        return "redirect:/user/login";
	    }

	    // 로그인된 사용자의 게시글만 조회
	    List<BoardDto> list = boardService.getAllMyList(mdto);
	    model.addAttribute("list", list);

	    // 업데이트 페이지로 이동
	    return "member/updateMyPage";
	}
	
	//나의 정보 수정
	@PostMapping("/updateProfile")
	public String updateProfile(
	        @RequestParam("background") MultipartFile backgroundphoto,
	        @RequestParam("profilephoto") MultipartFile profilephoto,
	        @RequestParam("name") String name,
	        @RequestParam("job") String job,
	        HttpSession session, HttpServletRequest request, Model model) throws IllegalStateException, IOException {

	    // 로그인한 사용자 정보 가져오기
	    MemberDto mdto = (MemberDto) session.getAttribute("mdto");

	    // 사용자 정보 업데이트
	    mdto.setName(name);
	    mdto.setJob(job);
	    memberService.updateUser(mdto);

	    System.out.println("사진->" + profilephoto);
	    // 프로필 사진 업데이트
	    profileService.updateProfile(profilephoto, request, mdto.getId());
	    backgroundService.updateBackground(backgroundphoto, request, mdto.getId());

	    return "redirect:/user/myPage";
	}


	
	
}










