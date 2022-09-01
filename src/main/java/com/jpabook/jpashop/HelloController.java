package com.jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){ //Spring Ui의 model에 data를 담아서 view 넘길 수 있음
        model.addAttribute("data", "hello!!!");
        return "hello"; //화면 이름을 return, 관례 : 이름 뒤에 '.html' 자동으로 붙음 ex)hello.html
        //spring boot의 thymeleaf viewName 맵핑 => 'resources:templates/' + {ViewName} + '.html'
    }
}
