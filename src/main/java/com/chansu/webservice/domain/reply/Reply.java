package com.chansu.webservice.domain.reply;

import com.chansu.webservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNo;

    private Long postNo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String replyContent;

    private String replyWriter;

    @Builder
    public Reply(String replyContent, String replyWriter){
        this.replyContent = replyContent;
        this.replyWriter = replyWriter;
    }
}
