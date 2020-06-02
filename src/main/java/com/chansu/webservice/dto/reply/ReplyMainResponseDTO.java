package com.chansu.webservice.dto.reply;

import com.chansu.webservice.domain.reply.Reply;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class ReplyMainResponseDTO {
    private Long replyNo;
    private Long postNo;
    private String replyContent;
    private String replyWriter;
    private String modifiedDate;

    public ReplyMainResponseDTO(Reply entity){
        replyNo = entity.getReplyNo();
        postNo = entity.getPostNo();
        replyContent = entity.getReplyContent();
        replyWriter = entity.getReplyWriter();
        modifiedDate = toStringDateTime(entity.getModifiedDate());
    }

    //view에서 인식할 수 있도록 문자열로 날짜형식 변환
    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
