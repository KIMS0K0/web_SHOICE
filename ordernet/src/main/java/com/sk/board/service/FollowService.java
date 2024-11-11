package com.sk.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.board.dtos.FollowDto;
import com.sk.board.dtos.MemberDto;
import com.sk.board.mapper.FollowMapper;

@Service
public class FollowService {
    @Autowired
    private FollowMapper followMapper;

	public int getFollow(FollowDto fdto) {
		return followMapper.getFollow(fdto);
	}

	public boolean insertFollow(FollowDto fdto) {
		return followMapper.insertFollow(fdto);
	}

	public boolean deleteFollow(FollowDto fdto) {
		return followMapper.deleteFollow(fdto);
	}

	public List<MemberDto> getFollowList(String id) {
		return followMapper.getFollowList(id);
	}
}
