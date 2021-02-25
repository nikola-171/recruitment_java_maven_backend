package com.example.recritment.dao;

import com.example.recritment.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SkillDao extends JpaRepository<Skill, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into skill(name, description, candidate)" +
                   "values(:name, :description, :candidate)", nativeQuery = true)
    void insertSkill(@Param("name") String name, @Param("description") String description,
                     @Param("candidate") Integer candidate);
}
