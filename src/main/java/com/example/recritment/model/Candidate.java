package com.example.recritment.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name="candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String first_name;
    private String last_name;
    private String email;
    private String phone;

    @OneToMany(targetEntity = Skill.class, cascade = CascadeType.ALL)
    @JoinColumn(name="candidate", referencedColumnName = "id", nullable = true)

    private List<Skill> skills;

    private Integer year_of_birth;
    private Integer month_of_birth;
    private Integer day_of_birth;




}
