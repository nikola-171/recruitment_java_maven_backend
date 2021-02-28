package com.example.recritment.service;

import com.example.recritment.dao.CandidateDao;
import com.example.recritment.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateDao candidateDao;

    public ResponseEntity<List<Candidate>> search_candidates(String first_name, String last_name, String skill){
        List<Candidate> ret = null;

        try{
            ret = this.candidateDao.search_candidates(first_name, last_name, skill);

            return new ResponseEntity<>(ret, HttpStatus.OK);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(ret, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<List<Candidate>> getAllCandidates(){
        List<Candidate> ret = null;

        try{
            ret = this.candidateDao.findAll();
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(ret, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<Void> saveCandidate(Candidate candidate){
        candidate.setFirst_name(HtmlUtils.htmlEscape(candidate.getFirst_name()));
        candidate.setLast_name(HtmlUtils.htmlEscape(candidate.getLast_name()));
        candidate.setEmail(HtmlUtils.htmlEscape(candidate.getEmail()));

        try{
            this.candidateDao.save(candidate);
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteCandidate(Integer id){

        try{
            this.candidateDao.deleteCandidate(id);
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> updateCandidate(Candidate candidate){

        try{
            this.candidateDao.updateTitle(candidate.getId(), candidate.getFirst_name(),
                    candidate.getLast_name(), candidate.getEmail(), candidate.getPhone(),
                    candidate.getYear_of_birth(), candidate.getMonth_of_birth(), candidate.getDay_of_birth());
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
