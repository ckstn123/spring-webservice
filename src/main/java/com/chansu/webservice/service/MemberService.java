package com.chansu.webservice.service;

import com.chansu.webservice.domain.security.member.Member;
import com.chansu.webservice.domain.security.member.MemberRepository;
import com.chansu.webservice.domain.security.member.MemberRole;
import com.chansu.webservice.dto.member.MemberSaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(MemberSaveRequestDto dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        dto.setUpw(passwordEncoder.encode(dto.getUpw()));
//        role.setRoleName("BASIC");
//        dto.setRoles(Arrays.asList(role));

        return memberRepository.save(dto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {

        Optional<Member> userEntityWrapper = memberRepository.findByUid(uid);
        Member userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin").equals(uid)) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.MEMBER.getValue()));
        }

        return new User(userEntity.getUid(), userEntity.getUpw(), authorities);
    }
}
