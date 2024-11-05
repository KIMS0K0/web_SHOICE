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
}
