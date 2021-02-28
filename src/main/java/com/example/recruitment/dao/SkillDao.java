package com.example.recruitment.dao;

import com.example.recruitment.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SkillDao extends JpaRepository<Skill, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from skill where id = :skillId and candidate = :candidateId",
           nativeQuery = true)
    void deleteSkill(Integer candidateId, Integer skillId);

    @Transactional
    @Modifying
    @Query(value = "insert into skill(name, description, candidate)" +
                   "values(:name, :description, :candidate)", nativeQuery = true)
    void insertSkill(@Param("name") String name, @Param("description") String description,
                     @Param("candidate") Integer candidate);
}
