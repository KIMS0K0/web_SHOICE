package com.sk.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sk.board.dtos.MemberDto;
import com.sk.board.mapper.SearchMapper;

@Service
public class SearchService {

    @Autowired
    private SearchMapper searchMapper;

    public List<MemberDto> getUserList(String search_input) {
        return searchMapper.getUserList(search_input);
    }
    
    public MemberDto getUserwithProfile(String id) {
        return searchMapper.getUserwithProfile(id);
    }
}
