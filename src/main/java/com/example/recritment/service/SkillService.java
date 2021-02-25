package com.example.recritment.service;

import com.example.recritment.dao.CandidateDao;
import com.example.recritment.dao.SkillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    private SkillDao skillDao;

    public void addSkill(Integer candidate, String name, String description){
        this.skillDao.insertSkill(name, description, candidate);
    }
}
