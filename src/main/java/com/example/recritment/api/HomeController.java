package com.example.recritment.api;

import com.example.recritment.model.Candidate;
import com.example.recritment.model.Skill;
import com.example.recritment.service.CandidateService;
import com.example.recritment.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private SkillService skillService;

    @GetMapping("/candidates")
    public List<Candidate> homePage(Model model){
        /*here we will return the list of all candidates*/
        return this.candidateService.getAllCandidates();
    }

    @PutMapping("/candidate/{id}/edit")
    public void editCandidate(@PathVariable("id") Integer id, @RequestBody Candidate candidate) {
        this.candidateService.updateCandidate(candidate);
    }

    @PostMapping("/candidate/{id}/addSkill")
    public void addSkill(@PathVariable("id") Integer id, @RequestBody Skill skill){
        this.skillService.addSkill(id, skill.getName(), skill.getDescription());
    }

    @PostMapping("/candidate/addCandidate")
    public void saveCandidate(@RequestBody Candidate candidate){
        this.candidateService.saveCandidate(candidate);
    }

    @DeleteMapping("/candidate/{id}/delete")
    public void deleteCandidate(@PathVariable("id") Integer id){
        this.candidateService.deleteCandidate(id);
    }

}
