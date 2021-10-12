package com.anyvision.test.restapianyvision.controller;

import com.anyvision.test.restapianyvision.exception.LimitNumOfPersonsException;
import com.anyvision.test.restapianyvision.exception.ResourceNotFoundException;
import com.anyvision.test.restapianyvision.model.Person;
import com.anyvision.test.restapianyvision.repository.PersonRepository;
import com.anyvision.test.restapianyvision.service.ComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/api/")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    private ComparisonService comparisonService;
    private static final int maxPersons = 10000;

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@Valid @RequestBody Person person) throws Exception {
        Person person1 = personRepository.save(person);
        if(personRepository.count() > maxPersons)
            throw new LimitNumOfPersonsException("The total num of persons reached 10K");
        return person1;
    }
    @GetMapping("/person")
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public Person getById(@PathVariable Long id){
        Person person = personRepository.getById(id);
        return person;
    }
    @GetMapping("/person/find")
    public List<Person> findPerson(@RequestBody Vector<Integer> features){
        Map<Long,Integer> dotProdMap = new HashMap<>();
        Map<Long,Integer> sortedDotProdMap = new LinkedHashMap<>();
        ArrayList<Long> ids = new ArrayList<>();
        int numOfPersons = (int) personRepository.count();
        int dotProduct;
        //put all in a map
        for (int i = 0; i < numOfPersons; i++) {
            //calculate dotProd for each person
            dotProduct = comparisonService.dotProd(features, getAllPersons().get(i).getFeatures());
            //Key->{id}, Value->{dotProd}
            dotProdMap.put(getAllPersons().get(i).getId(), dotProduct);
        }
        //sort Map by values
        dotProdMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x-> sortedDotProdMap.put(x.getKey(), x.getValue()));

        //get array of id's
        Long[] arr = sortedDotProdMap.keySet().toArray(new Long[sortedDotProdMap.size()]);
        List topThreeList = new ArrayList(3);

        topThreeList.add(getById(arr[0]));
        topThreeList.add(getById(arr[1]));
        topThreeList.add(getById(arr[2]));

        return topThreeList;
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        return  personRepository.findById(id).
                map(person -> {
                personRepository.delete(person);
                return  ResponseEntity.ok().build();}).orElseThrow(()-> new ResourceNotFoundException("Person not found with id" + id));
    }
}
