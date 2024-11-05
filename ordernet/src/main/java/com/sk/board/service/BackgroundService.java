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
}
