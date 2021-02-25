package com.example.recritment.service;

import com.example.recritment.dao.CandidateDao;
import com.example.recritment.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateDao candidateDao;

    public List<Candidate> getAllCandidates(){
        return this.candidateDao.findAll();
    }

    public void saveCandidate(Candidate candidate){
        this.candidateDao.save(candidate);
    }
}
