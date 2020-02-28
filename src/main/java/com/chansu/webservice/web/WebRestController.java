package com.chansu.webservice.web;

import com.chansu.webservice.dto.posts.PostsModifyRequestDto;
import com.chansu.webservice.dto.posts.PostsSaveRequestDto;
import com.chansu.webservice.service.PostsService;
import com.chansu.webservice.domain.posts.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Arrays;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsRepository postsRepository;
    private PostsService postsService;
    private Environment env;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){

        return postsService.save(dto);
    }

    @GetMapping("/profile")
    public String getProfile () {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }

    @PostMapping("/posts/modify")
    public Long postsModify(@RequestBody PostsModifyRequestDto dto){
        return postsService.modify(dto);
    }

    @PostMapping("/posts/delete")
    public Long postDelete(@RequestBody Long id){
        return postsService.delete(id);
    }
}