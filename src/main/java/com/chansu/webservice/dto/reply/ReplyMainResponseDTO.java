package com.chansu.webservice.dto.reply;

import com.chansu.webservice.domain.reply.Reply;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class ReplyMainResponseDTO {
    private Long reply_no;
    private Long post_no;
    private String reply_content;
    private String reply_writer;
    private String modifiedDate;

    public ReplyMainResponseDTO(Reply entity){
        reply_no = entity.getReply_no();
        post_no = entity.getPost_no();
        reply_content = entity.getReply_content();
        reply_writer = entity.getReply_writer();
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
