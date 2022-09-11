package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {
    ArrayList<Person> allPerson = new ArrayList<>();
    PersonRepository repo;
    Service service=new Service();
    HashMap<String, Long> hashMap=new HashMap<>();

    @Autowired
    public Controller(ArrayList<Person> allPerson, PersonRepository repo) {
        this.allPerson = allPerson;
        this.repo = repo;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Person> getAllPerson() {
        allPerson = repo.getAllPerson();
        return allPerson;
    }

    @GetMapping("/people")
    public ArrayList<Person> sortPersonList(@RequestParam String sort, @RequestParam String direction){
           if (sort.equals("name")){
               service.sortByName(allPerson,direction);
           }
        if (sort.equals("nationality")){
            service.sortByNationality(allPerson,direction);
        }  if (sort.equals("age")){
            service.sortByAge(allPerson,direction);
        }
        return allPerson;
    }
    @GetMapping("/nationality")
    public HashMap<String,Long> getAllNationality(){
        hashMap=repo.getAllNationality(allPerson);
        return hashMap;
    }
    @GetMapping("/sortListNationality")
    public List<Map.Entry<String,Long>> showListNationality(@RequestParam String direction){
        return service.sortNationalityList(hashMap,direction);
    }
    @GetMapping("/sortMapNationality")
    public HashMap<String,Long> showMapNationality(@RequestParam String direction){
            HashMap<String,Long> sortMap=new HashMap<>();
            List<Map.Entry<String,Long>> list= service.sortNationalityList(hashMap,direction);
            for(Map.Entry<String,Long> m:list){
                sortMap.put(m.getKey(), m.getValue());
            }
        return sortMap;
    }

}