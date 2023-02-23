package org.launchcode.familytree.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @GetMapping(value = "", produces = "application/json")
//    public String showPersonList(Model model) {
//
//        model.addAttribute("persons", personRepository.findAll());
//        return "tree/index";
//    }



    @GetMapping(path="/all")
    public @ResponseBody Iterable<Person> getAllPersons() {
        // This returns a JSON or XML with the users
        return personRepository.findAll();
    }

//    @GetMapping(value = "", produces = "application/json")
//    @ResponseBody
//    public List<Person> showPersonList() {
//        return personRepository.findAll();
//    }

}


// I am currently returning a PersonRepository
// I need to make an array list with the values
// Add those to the model
