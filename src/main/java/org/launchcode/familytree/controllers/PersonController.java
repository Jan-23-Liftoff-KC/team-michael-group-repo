package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Person");
        model.addAttribute("persons", personRepository.findAll());
        return "person/index";
    }

    @GetMapping("add")
    public String renderAddPersonForm(Model model){
        model.addAttribute("title", "Add Person");
        return "person/add";
    }

    @PostMapping("add")
    public String addPerson(@ModelAttribute Person newPerson){
        personRepository.save(newPerson);
        return "redirect:";
    }
}
