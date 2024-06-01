package com.kimseungjin.block_file_extensions.member;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("register")
    public String register(@RequestBody @Valid final RegisterRequest registerRequest) {
        memberService.register(registerRequest);
        return "redirect:/";
    }
}
