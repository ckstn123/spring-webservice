package com.chansu.webservice.web;

import com.chansu.webservice.service.MemberService;
import com.chansu.webservice.service.PostsService;
import com.chansu.webservice.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;
    private ReplyService replyService;
    private MemberService memberService;

    @GetMapping("/")
    public String main(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        //model.addAttribute("posts", postsService.findAllDesc());
        model.addAttribute("posts", postsService.getPostslist(pageNum));
        model.addAttribute("pageList", postsService.getPageList(pageNum));

        return "main";
    }

    @GetMapping("/posts")
    public String posts(@RequestParam(name="id", defaultValue="0") Long id, Model model){
        model.addAttribute("post", postsService.findPosts(id));
        model.addAttribute("replies", replyService.getReplylist(id));
        return "post";
    }

    @GetMapping("/login")
    public String loginForm(HttpServletRequest req) {
        String referer = req.getHeader("Referer");
        req.getSession().setAttribute("prevPage", referer);
        return "login";
    }
    @GetMapping("/logout")
    public String disLogout() {
        return "main";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        model.addAttribute("posts", postsService.searchPosts(keyword));
        return "main";
    }

}