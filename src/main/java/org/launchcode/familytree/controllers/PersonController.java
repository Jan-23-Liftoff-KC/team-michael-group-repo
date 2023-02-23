package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Gender;
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
        model.addAttribute("genders", Gender.values());
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

    @GetMapping("update/{personId}")
    public String renderUpdatePersonForm(Model model, @PathVariable int personId){
        model.addAttribute("title", "Update Person");
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("genders", Gender.values());
        Optional<Person> currentPerson = personRepository.findById(personId);
        if (currentPerson.isEmpty()) {
            model.addAttribute("title", "Update Person");
        } else {
            Person person = currentPerson.get();
            model.addAttribute("person", person);
        }
        return "person/update";
    }

    @PostMapping("update")
    public String updatePerson(@ModelAttribute Person person, @RequestParam String personId) {
        int id = Integer.parseInt(personId);
        Optional<Person> optPerson = personRepository.findById(id);
        Person aPerson;
        if (optPerson.isEmpty()) {
            return "person/update";
        } else {
            aPerson = optPerson.get();
        }
        personRepository.save(aPerson);
//        personRepository.save(person);
        return "redirect:/person";
    }

    @GetMapping("view/{personId}")
    public String displayPerson(Model model, @PathVariable Integer personId) {
        model.addAttribute("title", "View Person");

        Optional<Person> optPerson = personRepository.findById(personId);
        Person person = optPerson.get();
        model.addAttribute("person", person);

        Optional<Person> optParent = personRepository.findById(person.getParentId());
        if (optParent.isEmpty()) {
            model.addAttribute("title", "View Person");
        } else {
            Person parent = optParent.get();
            model.addAttribute("parent", parent);
        }

        Optional<Person> optParentTwo = personRepository.findById(person.getParentIdTwo());
        if (optParentTwo.isEmpty()) {
            model.addAttribute("title", "View Person");
        } else {
            Person parentTwo = optParent.get();
            model.addAttribute("parentTwo", parentTwo);
        }

        Optional<Person> optSpouse = personRepository.findById(person.getSpouseId());
        if (optSpouse.isEmpty()) {
            model.addAttribute("title", "View Person");
        } else {
            Person spouse = optParent.get();
            model.addAttribute("spouse", spouse);
        }
        return "person/view";
    }
}
