package com.sk.board.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.sk.board.dtos.MemberDto;

@Mapper
public interface SearchMapper {    
    List<MemberDto> getUserList(String search_input);

	MemberDto getUserwithProfile(String id);
}
