package com.sk.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sk.board.dtos.FollowDto;
import com.sk.board.dtos.MemberDto;
import com.sk.board.service.FollowService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/follow")
public class FollowController {
	
	@Autowired private FollowService followService;
	
	
	@GetMapping(value = "/followlistGet")
	public String followlistGet(HttpServletRequest request, Model model) {
		MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
		List<MemberDto> list = null;
		list = followService.getFollowList(mdto.getId());
		model.addAttribute("list", list);
		return "/follow/followList";
	}
	
	@GetMapping(value = "/followInsert")
	public String followInsert(String yourid, int memberid, HttpServletRequest request) {

		MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
		FollowDto fdto = new FollowDto(0, mdto.getId(), yourid);
		
		if (followService.insertFollow(fdto)) {
			System.out.println("삽입 성공");
		}
		
		return "redirect:/user/yourPage?yourid="+memberid;
	}
	
	@GetMapping(value = "/followDelete")
	public String followDelete(String yourid, int memberid, HttpServletRequest request) {

		MemberDto mdto = (MemberDto) request.getSession().getAttribute("mdto");
		FollowDto fdto = new FollowDto(0, mdto.getId(), yourid);
		
		if (followService.deleteFollow(fdto)) {
			System.out.println("삭제 성공");
		}
		
		return "redirect:/user/yourPage?yourid="+memberid;
	}

}
