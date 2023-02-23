package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
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

    // Returns JSON with persons from personRepository formatted for d3.stratify()
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Person> getAllPersonsForTree() {
        // This returns a JSON or XML with the persons

        ArrayList<Person> treeJSON = new ArrayList<>();
        //Array<Person> treeJSON = null; //???

        // Data format
//        {
//            "id":"1",
//            "child": "John",
//            "parent": "",
//            "icon": "https://github.com/Jan-23-Liftoff-KC/team-michael-group-repo/blob/main/src/main/resources/test-tree-data/person-icon.png?raw=true",
//            "spouse": "Isabella"
//        }

        for (Person person : personRepository.findAll()) {
            System.out.println("Added " + person.getFirstName() + " to list.");
            person.setFirstName(person.getFirstName() + " (edited)");
            treeJSON.add(person);
        }
        return treeJSON;
    }
}

