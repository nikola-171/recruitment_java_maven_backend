package com.example.recritment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name="candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String first_name;
    private String last_name;
    private String email;
    private String phone;

    private Integer year_of_birth;
    private Integer month_of_birth;
    private Integer day_of_birth;




}