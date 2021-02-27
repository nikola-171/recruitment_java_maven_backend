package com.example.recritment.dao;

import com.example.recritment.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CandidateDao extends JpaRepository<Candidate, Integer>, JpaSpecificationExecutor<Candidate> {


    @Modifying
    @Transactional
    @Query(value = "call search_candidates(:first_name, :last_name, :skill)", nativeQuery = true)
    List<Candidate> search_candidates(String first_name, String last_name, String skill);


    @Modifying
    @Transactional
    @Query(value = "delete from Candidate where id = :id ", nativeQuery = true)
    void deleteCandidate(@Param("id") Integer id);


    @Modifying
    @Transactional
    @Query(value = "Update Candidate c SET c.first_name=:first_name, c.last_name = :last_name," +
            "c.email = :email, c.phone=:phone, " +
            "c.year_of_birth=:year_of_birth,c.day_of_birth=:day_of_birth," +
            "c.month_of_birth=:month_of_birth" +
            " WHERE c.id=:id", nativeQuery = true)
     void updateTitle(@Param("id") Integer id, @Param("first_name") String first_name,
                            @Param("last_name") String last_name, @Param("email") String email,
                            @Param("phone") String phone, @Param("year_of_birth") Integer year_of_birth,
                            @Param("month_of_birth") Integer month_of_birth,@Param("day_of_birth") Integer day_of_birth);
}
