package com.chansu.webservice.dto.posts;

import com.chansu.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostInfoResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    @Builder
    public PostInfoResponseDto(Posts entity){
        id = entity.getId();
        title = entity.getTitle();
        author = entity.getAuthor();
        content = entity.getContent();
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
