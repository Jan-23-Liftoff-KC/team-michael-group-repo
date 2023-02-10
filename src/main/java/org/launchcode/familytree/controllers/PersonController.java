package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "My Family");
        model.addAttribute("persons", personRepository.findAll());
        return "person/index";
    }

    @GetMapping("add")
    public String renderAddPersonForm(Model model){
        model.addAttribute("title", "Add Person");
        model.addAttribute(new Person());
        return "person/add";
    }

    @PostMapping("add")
    public String addPerson(@ModelAttribute Person newPerson){
        personRepository.save(newPerson);
        return "redirect:";
    }

    @GetMapping("view/{personId}")
    public String displayPerson(Model model, @PathVariable int personId) {
        Optional<Person> optPerson = personRepository.findById(personId);
        Person person = optPerson.get();
//        if(optPerson.isPresent()){
//            Person person = optPerson.get();
//            model.addAttribute("person", person);
//            return "person/index";
//        } else {
//            return "redirect:../";
//        }
        model.addAttribute("person", person);
        return "person/view";
    }
}
