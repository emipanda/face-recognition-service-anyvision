package com.anyvision.test.restapianyvision.repository;

import com.anyvision.test.restapianyvision.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
