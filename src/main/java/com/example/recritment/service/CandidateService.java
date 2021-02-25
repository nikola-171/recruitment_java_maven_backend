package com.example.recritment.service;

import com.example.recritment.dao.CandidateDao;
import com.example.recritment.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteCandidate(Integer id){
        this.candidateDao.deleteById(id);
    }

    public void updateCandidate(Candidate candidate){
        this.candidateDao.updateTitle(candidate.getId(), candidate.getFirst_name(),
                candidate.getLast_name(), candidate.getEmail(), candidate.getPhone(),
                candidate.getYear_of_birth(), candidate.getMonth_of_birth(), candidate.getDay_of_birth());
    }
}
