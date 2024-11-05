package com.sk.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sk.board.dtos.MemberDto;
import com.sk.board.dtos.ProfileDto;
import com.sk.board.service.ProfileService;
import com.sk.board.service.SearchService;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping(value = "/findJob")
	public String findJob(@RequestParam(value = "search_input", required = false) String searchInput, Model model) {
	    List<MemberDto> list = null;
	    
	    if (searchInput != null && !searchInput.isEmpty()) {
	        // 검색어가 있을 때만 쿼리를 실행
	        list = searchService.getUserList(searchInput);
	        System.out.println("Stored Name: " + list.get(0));
	    }
	    
	    
	    
	    model.addAttribute("list", list); // 검색 결과가 없으면 null이 넘어감
	    return "search/findJob"; // 검색 페이지로 이동
	}


	 
}
