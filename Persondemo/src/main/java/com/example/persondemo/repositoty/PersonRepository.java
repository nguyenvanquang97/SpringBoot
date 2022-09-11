package com.example.persondemo.repositoty;

import com.example.persondemo.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class PersonRepository {
    private ArrayList<Person> allPerson = new ArrayList<>();

    public PersonRepository() {
        try {
            File file = ResourceUtils.getFile("classpath:static/person.json");
            ObjectMapper mapper = new ObjectMapper();
            allPerson.addAll(mapper.readValue(file, new TypeReference<List<Person>>() {
            }));
            allPerson.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Person> getAllPerson() {
        return allPerson;
    }
    public HashMap<String ,Long> getAllNationality(ArrayList<Person> allPerson){
        HashMap<String ,Long> hashMap=new HashMap<>();
        for (Person p:allPerson){
            if (hashMap.containsKey(p.getNationality())){
                hashMap.put(p.getNationality(), hashMap.get(p.getNationality())+1);
            }else {
                hashMap.put(p.getNationality(), 1L);
            }
        }
        return hashMap;
    }
}
