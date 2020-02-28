package com.chansu.webservice.web;

import com.chansu.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "main";
    }

    @GetMapping("/posts")
    public String posts(@RequestParam(name="id", defaultValue="0") Long id, Model model){
        model.addAttribute("post", postsService.findPosts(id));
        return "post";
    }

}