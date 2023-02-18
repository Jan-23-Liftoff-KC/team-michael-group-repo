package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute(new Person());
        return "person/add";
    }

    @PostMapping("add")
    public String addPerson(@ModelAttribute @Valid Person newPerson, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Person");
            return "person/add";
        }
        personRepository.save(newPerson);
        return "redirect:";
    }

    @GetMapping("view/{personId}")
    public String displayPerson(Model model, @PathVariable int personId) {
        Optional<Person> optPerson = personRepository.findById(personId);
        Person person = optPerson.get();
        Optional<Person> optParent = personRepository.findById(person.getParentId());
        Person parent = optParent.get();
//        Optional<Person> optSpouse = personRepository.findById(person.getSpouseId());
//        Person spouse = optSpouse.get();
        model.addAttribute("person", person);
        model.addAttribute("parent", parent);
//        model.addAttribute("spouse", spouse);
        return "person/view";
    }
}
