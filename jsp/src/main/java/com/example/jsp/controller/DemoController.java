package com.example.jsp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("demo")
public class DemoController {
    @RequestMapping("init")
    public ModelAndView init(String message) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("msg", message);
        return new ModelAndView("index", modelMap);
    }
}
