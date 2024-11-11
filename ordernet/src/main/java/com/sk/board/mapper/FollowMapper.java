package com.sk.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sk.board.dtos.FollowDto;
import com.sk.board.dtos.MemberDto;

@Mapper
public interface FollowMapper {

	public int getFollow(FollowDto fdto);

	public boolean insertFollow(FollowDto fdto);

	public boolean deleteFollow(FollowDto fdto);

	public List<MemberDto> getFollowList(String id);

}
