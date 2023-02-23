package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="tree")
public class TreeController {

    @Autowired
    private PersonRepository personRepository;

//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<Person> getAllPersons() {
//        // This returns a JSON or XML with the users
//
//        return personRepository.findAll();
//    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Person> getAllPersonsForTree() {
        // This returns a JSON or XML with the persons

        ArrayList<Person> treeJSON = new ArrayList<>();
        //Array<Person> treeJSON = null; //???

        for (Person person : personRepository.findAll()) {
            System.out.println("Added " + person.getFirstName() + " to list.");
            person.setFirstName(person.getFirstName() + " (edited)");
            treeJSON.add(person);
        }
        return treeJSON;
    }
}

