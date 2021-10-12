package com.anyvision.test.restapianyvision.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name="person")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Person {
    @Id
    @Column(name="id", nullable=false)
    @SequenceGenerator(name = "person_seq", sequenceName = "student_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    private Long id;
    private String name;

    @ElementCollection
    private List<Integer> features = new Vector<>(Collections.nCopies(256,0));

}
