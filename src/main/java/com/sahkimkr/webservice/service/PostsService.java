package com.sahkimkr.webservice.service;

import com.sahkimkr.webservice.domain.posts.PostsMainResponseDto;
import com.sahkimkr.webservice.domain.posts.PostsRepository;
import com.sahkimkr.webservice.domain.posts.PostsSaveRequestDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }
}