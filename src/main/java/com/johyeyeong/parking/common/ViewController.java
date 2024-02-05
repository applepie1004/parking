package com.johyeyeong.parking.common;

import com.johyeyeong.parking.apply.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ApartmentRepository apartmentRepository;

    @GetMapping("/")
    public String index(){
        return "apt/index";
    }

    @GetMapping("/apt")
    public ModelAndView apt(){
        ModelAndView mav = new ModelAndView("apt/apt");
        mav.addObject("dong", apartmentRepository.getAllDong());

        return mav;
    }



    /* 관리사무소 admin : adm */
    @GetMapping("/adm")
    public String adminIndex(){
        return "adm/index";
    }
}
