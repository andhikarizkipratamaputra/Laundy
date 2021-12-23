package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView main(ModelAndView mView) {
        mView.setViewName("pages/home/index");
        return mView;
    }
    @RequestMapping("/dika")
    public ModelAndView dika(ModelAndView mView) {
        mView.setViewName("pages/home/dika");
        return mView;
    }

}