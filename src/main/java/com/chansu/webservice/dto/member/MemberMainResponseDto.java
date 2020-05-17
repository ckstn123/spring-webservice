package com.chansu.webservice.dto.member;

import com.chansu.webservice.domain.security.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberMainResponseDto {
    private Long id;
    private String uid;
    private String upw;
    private String uemail;

    @Builder
    public MemberMainResponseDto(String uid, String upw, String uemail) {
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
