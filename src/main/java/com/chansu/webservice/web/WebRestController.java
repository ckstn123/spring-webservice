package com.chansu.webservice.web;

import com.chansu.webservice.dto.member.MemberSaveRequestDto;
import com.chansu.webservice.dto.posts.PostsModifyRequestDto;
import com.chansu.webservice.dto.posts.PostsSaveRequestDto;
import com.chansu.webservice.dto.reply.ReplyModifyRequestDto;
import com.chansu.webservice.dto.reply.ReplySaveRequestDto;
import com.chansu.webservice.service.MemberService;
import com.chansu.webservice.service.PostsService;
import com.chansu.webservice.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsService postsService;
    private Environment env;
    private MemberService memberService;
    private ReplyService replyService;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @GetMapping("/profile")
    public String getProfile () {
        return Arrays.stream(env.getActiveProfiles())
                .filter(e -> "set1".equals(e) || "set2".equals(e) || "local".equals(e))
                        .findFirst()
                        .orElse("");
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){

        return postsService.save(dto);
    }

    @PutMapping("/posts")
    public Long postsModify(@RequestBody PostsModifyRequestDto dto){
        return postsService.modify(dto);
    }

    @DeleteMapping("/posts")
    public Long postDelete(@RequestBody Long id){
        return postsService.delete(id);
    }

    @PostMapping("/member")
    public Long create(@RequestBody MemberSaveRequestDto dto) {
        return memberService.create(dto);
    }

    @PostMapping("/replies")
    public Long saveReply(@RequestBody ReplySaveRequestDto dto){

        return replyService.save(dto);
    }

    @PutMapping("/replies")
    public Long replyModify(@RequestBody ReplyModifyRequestDto dto){

        return replyService.modify(dto);
    }

    @DeleteMapping("/replies")
    public Long replyDelete(@RequestBody Long id){

        return replyService.delete(id);
    }

}