package com.chansu.webservice.web;

import com.chansu.webservice.dto.posts.PostsModifyRequestDto;
import com.chansu.webservice.dto.posts.PostsSaveRequestDto;
import com.chansu.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsService postsService;
    private Environment env;

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
}