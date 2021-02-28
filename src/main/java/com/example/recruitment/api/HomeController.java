package com.example.recruitment.api;

import com.example.recruitment.exception.ApiRequestException;
import com.example.recruitment.model.Candidate;
import com.example.recruitment.model.Skill;
import com.example.recruitment.service.CandidateService;
import com.example.recruitment.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Candidate>> homePage(){
        /*here we will return the list of all candidates*/
        return this.candidateService.getAllCandidates();
    }

    @DeleteMapping("/candidate/{candidateId}/deleteSkill/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable("candidateId") Integer candidateId, @PathVariable("skillId") Integer skillId){
        return this.skillService.deleteSkill(candidateId, skillId);
    }

    @GetMapping("/candidates/search")
    public ResponseEntity<List<Candidate>> search_candidates(@RequestParam(name="first_name", value="", required = false) String first_name,
                                  @RequestParam(name="last_name", value="", required = false) String last_name,
                                  @RequestParam(name="skill", value="", required = false) String skill ){

        if(first_name.equals("") && last_name.equals("") && skill.equals("")){
            throw new ApiRequestException("empty field are not allowed");
        }

        return this.candidateService.search_candidates(first_name, last_name, skill);
    }

    @PutMapping("/candidate/{id}/edit")
    public ResponseEntity<Void> editCandidate(@PathVariable("id") Integer id, @RequestBody Candidate candidate) {
        return this.candidateService.updateCandidate(candidate);
    }

    @PostMapping("/candidate/{id}/addSkill")
    public ResponseEntity<Void> addSkill(@PathVariable("id") Integer id, @RequestBody Skill skill){
        if(skill.getName().equals("") || skill.getDescription().equals("")){
            throw new ApiRequestException("Skill name and description cannot be empty.");
        }

        return this.skillService.addSkill(id, skill.getName(), skill.getDescription());
    }

    @PostMapping("/candidate/addCandidate")
    public ResponseEntity<Void> saveCandidate(@RequestBody Candidate candidate){
        if(candidate.getFirst_name().equals("") || candidate.getLast_name().equals("") ||
           candidate.getEmail().equals("") || candidate.getPhone().equals("") ||
           candidate.getYear_of_birth().equals(null) || candidate.getMonth_of_birth().equals(null) ||
           candidate.getDay_of_birth().equals(null)){
            throw new ApiRequestException("Empty fields are not allowed");
        }

        if(!candidate.getPhone().chars().allMatch(Character::isDigit)){
            throw new ApiRequestException("Phone number must be a numeric value.");
        }

        return this.candidateService.saveCandidate(candidate);
    }

    @DeleteMapping("/candidate/{id}/delete")
    public ResponseEntity<Void> deleteCandidate(@PathVariable("id") Integer id){
        return this.candidateService.deleteCandidate(id);
    }

}
