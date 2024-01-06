package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.form.SampleForm;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    @PostMapping("/request_test")
    @ResponseBody
    public String postTest(SampleForm sampleForm) {
        return "名前: " + sampleForm.getName() + "<br>血液型: " + sampleForm.getBloodType();
    }
    
    @GetMapping("/form_test")
    public String formTest(SampleForm sampleForm, Model model) {
        model.addAttribute("sampleForm", sampleForm);
        return "lesson/form_test";
    }
}
