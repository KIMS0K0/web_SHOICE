package com.sk.board.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sk.board.dtos.BackgroundDto;
import com.sk.board.dtos.FileBoardDto;
import com.sk.board.mapper.BackgroundMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BackgroundService {

    @Autowired
    private BackgroundMapper backgroundMapper;

	//파일정보 가져오기
	public BackgroundDto getFileInfo(String id) {
		return backgroundMapper.getBackground(id);
	}
	
	public void updateBackground(MultipartFile backgrounphoto,  HttpServletRequest request, String id) throws IllegalStateException, IOException {
		System.out.println("파일 첨부 여부:" + backgrounphoto.getOriginalFilename());
		
		if (backgrounphoto == null || backgrounphoto.isEmpty()) {
	        System.out.println("첨부된 파일이 없습니다.");
	        return;
	    }
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
        System.out.println("파일 저장 경로: " + uploadPath);
		
        String origin_name = backgrounphoto.getOriginalFilename();
        String stored_name = UUID.randomUUID() + origin_name.substring(origin_name.indexOf("."));
        String backgrounduploadUrl = uploadPath+"/"+stored_name;
        backgrounphoto.transferTo(new File(backgrounduploadUrl));
        
        BackgroundDto bdto = new BackgroundDto(0,id,origin_name,stored_name);
        
        System.out.println("비디티오 " + bdto);
        backgroundMapper.updateBackground(bdto);
	}
}
