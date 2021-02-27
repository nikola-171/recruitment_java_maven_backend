package com.example.recritment.service;

import com.example.recritment.dao.CandidateDao;
import com.example.recritment.dao.SkillDao;
import com.example.recritment.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillDao skillDao;

    public void deleteSkill(Integer candidateId, Integer skillId){
        this.skillDao.deleteSkill(candidateId, skillId);
    }

    public void addSkill(Integer candidate, String name, String description){
        this.skillDao.insertSkill(name, description, candidate);
    }
}
