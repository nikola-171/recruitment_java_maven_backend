package com.example.recritment.api;

import com.example.recritment.model.Candidate;
import com.example.recritment.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/")
    public ModelAndView homePage(Model model){
        /*here we will return the list of all candidates */
        List<Candidate> list = this.candidateService.getAllCandidates();

        ModelAndView mav = new ModelAndView("HomePage");
        mav.addObject("candidates",list);
        return mav;

    }
}
