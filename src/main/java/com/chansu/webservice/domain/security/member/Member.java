package com.chansu.webservice.domain.security.member;

import com.chansu.webservice.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@EqualsAndHashCode(of = "uid", callSuper = false)
@ToString
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length=50)
    private String uid;

    @Column(nullable = false, length=200)
    private String upw;

    @Column(nullable = false, unique = true, length=50)
    private String uemail;

//    @CreationTimestamp
//    private Date regdate;
//
//    @UpdateTimestamp
//    private Date updatedate;

    @Builder
    public Member(String uid, String upw, String uemail) {
        this.uid = uid;
        this.upw = upw;
        this.uemail = uemail;
    }

}