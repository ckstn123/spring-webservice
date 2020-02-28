package com.chansu.webservice.dto;

import com.chansu.webservice.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostInfoResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostInfoResponseDto(Posts entity){
        id = entity.getId();
        title = entity.getTitle();
        content = entity.getContent();
        author = entity.getAuthor();
    }
}
