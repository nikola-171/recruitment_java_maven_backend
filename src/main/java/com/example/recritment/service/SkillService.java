package com.example.recritment.service;

import com.example.recritment.dao.SkillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;


@Service
public class SkillService {

    @Autowired
    private SkillDao skillDao;

    public ResponseEntity<Void> deleteSkill(Integer candidateId, Integer skillId){

        try{
            this.skillDao.deleteSkill(candidateId, skillId);
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<Void> addSkill(Integer candidate, String name, String description){

        try{
            this.skillDao.insertSkill(name, description, candidate);
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
