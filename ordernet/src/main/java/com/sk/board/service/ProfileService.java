package com.sk.board.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sk.board.dtos.BackgroundDto;
import com.sk.board.dtos.FileBoardDto;
import com.sk.board.dtos.ProfileDto;
import com.sk.board.mapper.BackgroundMapper;
import com.sk.board.mapper.ProfileMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProfileService {

    @Autowired
    private ProfileMapper profileMapper;

	//파일정보 가져오기
	public ProfileDto getFileInfo(String id) {
		return profileMapper.getProfile(id);
	}
	
    // 파일 저장 및 DB 업데이트 메서드
	public void updateProfile(MultipartFile profilephoto, HttpServletRequest request, String id) throws IllegalStateException, IOException {

        System.out.println("파일 첨부 여부: " + profilephoto.getOriginalFilename());

		if (profilephoto == null || profilephoto.isEmpty()) {
	        System.out.println("첨부된 파일이 없습니다.");
	        return;
	    }
		
        // 파일 저장 경로 설정
        String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
        System.out.println("파일 저장 경로: " + uploadPath);
        
        String origin_name = profilephoto.getOriginalFilename();
        String stored_name = UUID.randomUUID() + origin_name.substring(origin_name.indexOf("."));
        String profileuploadUrl = uploadPath+"/"+stored_name;
        profilephoto.transferTo(new File(profileuploadUrl));
        
        ProfileDto pdto = new ProfileDto(0,id, origin_name,stored_name);
        
        System.out.println("피디티오 " + pdto);
        profileMapper.updateProfile(pdto);
    }
}