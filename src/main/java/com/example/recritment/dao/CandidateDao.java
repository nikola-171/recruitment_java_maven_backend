package com.example.recritment.dao;

import com.example.recritment.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {

}
