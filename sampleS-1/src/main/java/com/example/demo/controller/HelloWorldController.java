package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!!");
        return "index";
    }
    
    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public String Test(Model model) {
        model.addAttribute("message", "Test!!");
        return "index";
    }   
    
    @RequestMapping(value = "/Test2", method = RequestMethod.GET)
    public String Test2(Model model) {
        model.addAttribute("TestMes", "springbootのテストです。");
        model.addAttribute("TestMes2", "こんにちは。");
        return "test2";
    }    
    
    @RequestMapping(value = "/Test3", method = RequestMethod.GET)
    public String Test3(Model model) {
        model.addAttribute("TestMes", "springbootのテスト2です。");
        model.addAttribute("TestMes2", "あいうえおかきくけこ。");
        return "test2";
    }  
}

