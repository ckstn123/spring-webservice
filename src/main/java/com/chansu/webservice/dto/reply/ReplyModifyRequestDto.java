package com.chansu.webservice.dto.reply;

import com.chansu.webservice.domain.reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyModifyRequestDto {
    private Long replyNo;
    private String replyContent;
    private String replyWriter;

    public Reply toEntity(){
        return Reply.builder()
                .replyContent(replyContent)
                .replyWriter(replyWriter)
                .build();
    }
}
