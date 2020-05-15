package com.chansu.webservice.web;

import com.chansu.webservice.domain.security.member.Member;
import com.chansu.webservice.domain.security.member.MemberRepository;
import com.chansu.webservice.domain.security.member.MemberRole;
import com.chansu.webservice.service.MemberService;
import com.chansu.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;
    private MemberService memberService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        Member user = (Member) httpSession.getAttribute("uid");
        return "main";
    }

    @GetMapping("/posts")
    public String posts(@RequestParam(name="id", defaultValue="0") Long id, Model model){
        model.addAttribute("post", postsService.findPosts(id));
        return "post";
    }

    @GetMapping("/login")
    public String loginForm(HttpServletRequest req, Model model) {
        String referer = req.getHeader("Referer");
        req.getSession().setAttribute("prevPage", referer);

        return "login";
    }

}