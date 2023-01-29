package org.launchcode.familytree.controllers;

import org.launchcode.familytree.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="person")
public class PersonController {

    public static List<Person> persons = new ArrayList<>();
    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Person");
        model.addAttribute("persons", persons);
        return "person/index";
    }

    @GetMapping("add")
    public String renderAddPersonForm(Model model){
        model.addAttribute("title", "Add Person");
        return "person/add";
    }

    @PostMapping("add")
    public String addPerson(@RequestParam String personName, @RequestParam String personBio){
        persons.add(new Person(personName, personBio));
        return "redirect:";
    }

//    @PostMapping("add")
//    public String addPerson(@ModelAttribute Person newPerson){
//        persons.add(new Person);
//        return "redirect:";
//    }
}
