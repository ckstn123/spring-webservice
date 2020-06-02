package com.chansu.webservice.dto.reply;

import com.chansu.webservice.domain.posts.Posts;
import com.chansu.webservice.domain.reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplySaveRequestDto {

    private String replyContent;
    private String replyWriter;

    @Builder
    ReplySaveRequestDto(String replyContent, String replyWriter){
        this.replyContent = replyContent;
        this.replyWriter = replyWriter;
    }

    public Reply toEntity(){
        return Reply.builder()
                .replyContent(replyContent)
                .replyWriter(replyWriter)
                .build();
    }
}
