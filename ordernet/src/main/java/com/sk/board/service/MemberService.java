package com.sk.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.sk.board.command.AddUserCommand;
import com.sk.board.command.LoginCommand;
import com.sk.board.dtos.MemberDto;
import com.sk.board.mapper.BackgroundMapper;
import com.sk.board.mapper.MemberMapper;
import com.sk.board.mapper.ProfileMapper;
import com.sk.board.status.RoleStatus;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor ;lombok 기능: final 필드를 초기화 - Autowired 생략가능
@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
    private BackgroundMapper backgroundMapper;
    @Autowired
    private ProfileMapper profileMapper;
    
	@Autowired
	private PasswordEncoder passwordEncoder;// <-- @Bean으로 등록:SecurityConfig클래스에서..
	
	public boolean addUser(AddUserCommand addUserCommand) {
		
		MemberDto mdto=new MemberDto();
		mdto.setId(addUserCommand.getId());
		mdto.setName(addUserCommand.getName());
		
		//password암호화하여 저장하자
		mdto.setPassword(passwordEncoder.encode(addUserCommand.getPassword()));
		
		mdto.setEmail(addUserCommand.getEmail());
		mdto.setAddress(addUserCommand.getAddress());
		mdto.setRole(RoleStatus.USER+"");//등급추가
		mdto.setJob("");
		boolean isUserAdded = memberMapper.addUser(mdto);
		
		if (isUserAdded) {
			System.out.println(mdto);
			String id = mdto.getId();
            backgroundMapper.insertBackground(id);
            profileMapper.insertProfile(id);
        }
		
		return isUserAdded;
	}
	
	public void updateUserProfile(MemberDto dto, MultipartFile backgroundFile, MultipartFile profilePhotoFile) {
        // 기본 정보 업데이트
        memberMapper.updateUser(dto);

    }
	
	public String idChk(String id) {
		return memberMapper.idChk(id);
	}
	
	public String login(LoginCommand loginCommand, HttpServletRequest request, Model model) {
	    MemberDto dto = memberMapper.loginUser(loginCommand.getId());
	    // 기본 경로를 home으로 설정
	    String path = "redirect:/home";

	    if (dto != null) {
	        if (passwordEncoder.matches(loginCommand.getPassword(), dto.getPassword())) {
	            System.out.println("패스워드 같음: 회원이 맞음");
	            request.getSession().setAttribute("mdto", dto);

	            // 세션에서 이전 페이지 가져와 리디렉션 경로 설정
	            String prevPage = (String) request.getSession().getAttribute("prevPage");
	            if (prevPage != null) {
	                path = "redirect:" + prevPage;
	                request.getSession().removeAttribute("prevPage"); // 이후 참조되지 않도록 세션에서 제거
	            }
	        } else {
	            System.out.println("패스워드 틀림");
	            model.addAttribute("msg", "패스워드를 확인하세요");
	            path = "member/login";
	        }
	    } else {
	        System.out.println("회원이 아닙니다.");
	        model.addAttribute("msg", "아이디를 확인하세요");
	        path = "member/login";
	    }

	    return path;
	}

	public MemberDto getAllInfo(int yourid) {
		return memberMapper.getUser(yourid);
	}

	public MemberDto getMemberId(String id) {
		return memberMapper.getMemberId(id);
	}

}














