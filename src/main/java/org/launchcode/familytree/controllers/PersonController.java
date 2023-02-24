package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Gender;
import org.launchcode.familytree.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/update/{personId}")
    public String updatePerson(@PathVariable("personId") int personId, @Valid Person person, BindingResult result, Model model){
        if (result.hasErrors()){
            return "person/add";
        }

        Optional<Person> optPerson = personRepository.findById(personId);
        Person currentPerson = optPerson.get();
        model.addAttribute("person", currentPerson);
        personRepository.save(currentPerson);
        return "redirect:";
    }

//    @GetMapping("/update")
//    public ModelAndView updateProfileView(@RequestParam Integer personId){
//        ModelAndView mav = new ModelAndView("update");
//        if(personRepository.findById(personId).isPresent()){
//            mav.addObject(personRepository.findById(personId).get());
//        }
//        return mav;
//    }

//    @PutMapping("/{personId}")
//    public String updatePerson(@ModelAttribute Person person, @PathVariable("personId") int personId) {
//        Optional<Person> optPerson = personRepository.findById(personId);
//        Person aPerson;
//        if (optPerson.isEmpty()) {
//            return "person/update";
//        } else {
//            aPerson = optPerson.get();
//            aPerson.setFirstName();
//        }
////        personRepository.save(person);
//        return "redirect:";
//    }

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
