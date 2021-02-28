package com.example.recritment.api;

import com.example.recritment.model.Candidate;
import com.example.recritment.model.Skill;
import org.hibernate.Hibernate;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeControllerTest {

    @Autowired
    HomeController homeController;

    void saveCandidate(){
        Candidate candidate = new Candidate();
        candidate.setFirst_name("pera");
        candidate.setLast_name("perić");
        candidate.setEmail("pera.peric@gmail.com");
        candidate.setPhone("06589658");
        candidate.setYear_of_birth(1998);
        candidate.setMonth_of_birth(4);
        candidate.setDay_of_birth(3);

        ResponseEntity<Void> response = this.homeController.saveCandidate(candidate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    void editCandidate(){

        ResponseEntity<List<Candidate>> response = this.homeController.search_candidates("pera", "perić", "");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());

        Candidate candidate = response.getBody().get(0);

        candidate.setFirst_name("mika");
        candidate.setLast_name("mikić");

        ResponseEntity<Void> save = this.homeController.editCandidate(candidate.getId(), candidate);

        assertEquals(HttpStatus.OK, save.getStatusCode());

    }

    void addSkill(){

        ResponseEntity<List<Candidate>> response = this.homeController.search_candidates("mika", "mikić", "");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());

        Skill skill = new Skill();
        skill.setName("programiranje");
        skill.setDescription("Programiranje u jeziku c++.");

        ResponseEntity<Void> edit = this.homeController.addSkill(response.getBody().get(0).getId(), skill);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    void homePage(){
        ResponseEntity<List<Candidate>> r = homeController.homePage();

        assertEquals(HttpStatus.OK, r.getStatusCode());
    }

    void search_candidates(){
        ResponseEntity<List<Candidate>> s_result = this.homeController.search_candidates("","","c++");

        assertEquals(1, s_result.getBody().size());
    }

    void deleteSkill(){

        ResponseEntity<List<Candidate>> response = this.homeController.search_candidates("mika", "mikić", "");
        Hibernate.initialize(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(1, response.getBody().get(0).getSkills().size());

        ResponseEntity<Void> delete = this.homeController.deleteSkill(response.getBody().get(0).getId(), response.getBody().get(0).getSkills().get(0).getId());

        assertEquals(HttpStatus.OK, delete.getStatusCode());
    }

    void deleteCandidate(){

        ResponseEntity<List<Candidate>> response = this.homeController.search_candidates("mika", "mikić", "");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());

        ResponseEntity<Void> delete = this.homeController.deleteCandidate(response.getBody().get(0).getId());

        assertEquals(HttpStatus.OK, delete.getStatusCode());

    }


    @Test
    void test() {
        saveCandidate();
        editCandidate();
        addSkill();
        homePage();
        search_candidates();
        deleteSkill();
        deleteCandidate();
    }

}