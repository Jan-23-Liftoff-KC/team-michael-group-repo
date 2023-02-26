package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {



    @Autowired
    private PersonRepository personRepository;
    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "First or Last Name");
//        columnChoices.put("person", "Person");


    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("person", personRepository.findAll());

        return "list";
    }

    @RequestMapping(value = "person")
    public String listPersonByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Person> person;
        if (column.toLowerCase().equals("all")){
            person = personRepository.findAll();
            model.addAttribute("title", "All Persons");
        } else {
            person = PersonData.findByColumnAndValue(column, value, personRepository.findAll());
            model.addAttribute("title", "Persons with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("persons", person);


        return "list-person";
    }


}