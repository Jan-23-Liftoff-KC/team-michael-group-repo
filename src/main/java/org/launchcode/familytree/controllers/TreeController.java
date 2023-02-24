package org.launchcode.familytree.controllers;

import org.json.simple.JSONArray;
import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="tree")
public class TreeController {

    @Autowired
    private PersonRepository personRepository;

    // Returns JSON with Persons from personRepository formatted for d3.stratify()
    @GetMapping(path="/data")
    public @ResponseBody Iterable<Person> getAllPersonsForTree() {

        JSONArray treeJSON = new JSONArray();

        for (Person person : personRepository.findAll()) {
            JSONObject treePerson = new JSONObject();

            treePerson.put("id", person.getId());
            treePerson.put("firstName", person.getFirstName());
            treePerson.put("lastName", person.getLastName());
            treePerson.put("parentId", person.getParentId());
            treePerson.put("icon", "https://github.com/Jan-23-Liftoff-KC/team-michael-group-repo/blob/main/src/main/resources/test-tree-data/person-icon.png?raw=true"); //person.getIcon());
            treePerson.put("spouse", person.getSpouseId());

            treeJSON.add(treePerson);

        }
        return (Iterable<Person>) treeJSON;
    }
}

