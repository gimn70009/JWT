package com.example.springjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller// 스프링에 컨트롤러로 등록해서 관리해야 하므로
@ResponseBody // 웹 페이지를 리턴해주는 것이 아닌, 특정한 객체 데이터나 스트링 데이터를 응답해줄 것이기 때문
public class MainController {

    @GetMapping("/")
    public String mainP() {

        return "Main Controller";
    }
}
