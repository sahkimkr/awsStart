package com.sahkimkr.webservice.service;

import com.sahkimkr.webservice.domain.posts.Posts;
import com.sahkimkr.webservice.domain.posts.PostsRepository;
import com.sahkimkr.webservice.domain.posts.PostsSaveRequestDto;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup () {
        postsRepository.deleteAll();
    }

    @Test
    public void Dto데이터가_posts테이블에_저장된다 () {
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("sahkimkr@gmail.com")
                .content("테스트 게시글")
                .title("테스트 타이틀")
                .build();

        //when
        postsService.save(dto);

        //then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor(),is(equalTo(dto.getAuthor())));
        assertThat(posts.getContent(),is(equalTo(dto.getContent())));
        assertThat(posts.getTitle(),is(equalTo(dto.getTitle())));
    }
}