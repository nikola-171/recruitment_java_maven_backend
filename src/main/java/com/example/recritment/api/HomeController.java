package com.example.recritment.api;

import com.example.recritment.exception.ApiRequestException;
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

    @DeleteMapping("/candidate/{candidateId}/deleteSkill/{skillId}")
    public void deleteSkill(@PathVariable("candidateId") Integer candidateId, @PathVariable("skillId") Integer skillId){
        this.skillService.deleteSkill(candidateId, skillId);
    }

    @GetMapping("/candidates/search")
    public List<Candidate> search_candidates(@RequestParam(name="first_name", required = false) String first_name,
                                  @RequestParam(name="last_name", required = false) String last_name,
                                  @RequestParam(name="skill", required = false) String skill ){

        if(first_name.equals("") && last_name.equals("") && skill.equals("")){
            throw new ApiRequestException("empty field are not allowed");
        }

        return this.candidateService.search_candidates(first_name, last_name, skill);
    }

    @PutMapping("/candidate/{id}/edit")
    public void editCandidate(@PathVariable("id") Integer id, @RequestBody Candidate candidate) {
        this.candidateService.updateCandidate(candidate);
    }

    @PostMapping("/candidate/{id}/addSkill")
    public void addSkill(@PathVariable("id") Integer id, @RequestBody Skill skill){
        if(skill.getName().equals("") || skill.getDescription().equals("")){
            throw new ApiRequestException("Skill name and description cannot be empty.");
        }
        this.skillService.addSkill(id, skill.getName(), skill.getDescription());
    }

    @PostMapping("/candidate/addCandidate")
    public void saveCandidate(@RequestBody Candidate candidate){
        if(candidate.getFirst_name().equals("") || candidate.getLast_name().equals("") ||
           candidate.getEmail().equals("") || candidate.getPhone().equals("")){
            throw new ApiRequestException("Empty fields are not allowed");
        }

        if(!candidate.getPhone().chars().allMatch(Character::isDigit)){
            throw new ApiRequestException("Phone number must be a numeric value.");
        }

        this.candidateService.saveCandidate(candidate);
    }

    @DeleteMapping("/candidate/{id}/delete")
    public void deleteCandidate(@PathVariable("id") Integer id){
        this.candidateService.deleteCandidate(id);
    }

}
