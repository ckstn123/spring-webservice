package com.chansu.webservice.dto.member;

import com.chansu.webservice.domain.posts.Posts;
import com.chansu.webservice.domain.security.member.Member;
import com.chansu.webservice.domain.security.member.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private Long id;
    private String uid;
    private String upw;
    private String uemail;

    @Builder
    public MemberSaveRequestDto(String uid, String upw, String uemail) {
        this.uid = uid;
        this.upw = upw;
        this.uemail = uemail;
    }

    public Member toEntity(){
        return Member.builder()
                .uid(uid)
                .upw(upw)
                .uemail(uemail)
                .build();
    }
}
