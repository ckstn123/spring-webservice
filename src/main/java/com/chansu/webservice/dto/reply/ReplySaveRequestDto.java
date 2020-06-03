package com.chansu.webservice.dto.reply;

import com.chansu.webservice.domain.reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplySaveRequestDto {
    private Long postNo;
    private String replyContent;
    private String replyWriter;

    @Builder
    ReplySaveRequestDto(Long postNo, String replyContent, String replyWriter){
        this.postNo = postNo;
        this.replyContent = replyContent;
        this.replyWriter = replyWriter;
    }

    public Reply toEntity(){
        return Reply.builder()
                .postNo(postNo)
                .replyContent(replyContent)
                .replyWriter(replyWriter)
                .build();
    }
}
