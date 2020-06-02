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
    private Long reply_no;

    private Long post_no;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String reply_content;

    private String reply_writer;

    @Builder
    public Reply(String reply_content, String reply_writer){
        this.reply_content = reply_content;
        this.reply_writer = reply_writer;
    }
}
