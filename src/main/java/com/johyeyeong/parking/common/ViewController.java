package com.johyeyeong.parking.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index(){
        return "index";
    }



    /* 관리사무소 admin : adm */
    @GetMapping("/adm")
    public String adminIndex(){
        return "adm/index";
    }
}
