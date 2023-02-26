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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("update/{id}")
    public String renderUpdatePersonForm(Model model, @PathVariable int id, HttpServletRequest request){
        model.addAttribute("title", "Update Person");
        model.addAttribute("actionUrl", request.getRequestURI());
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("genders", Gender.values());
        Optional<Person> currentPerson = personRepository.findById(id);
        if (currentPerson.isEmpty()) {
            model.addAttribute("title", "Invalid Person");
        } else {
            Person person = currentPerson.get();
            model.addAttribute("person", person);
        }
        return "person/update";
    }

    @PostMapping(value = "update/{id}")
    public String processUpdatePersonForm(@Valid @ModelAttribute Person person, RedirectAttributes model, Errors errors,
                                          @RequestParam(name = "id") String id, @RequestParam(name = "firstName", required = false) String firstName,
                                          @RequestParam(name = "middleName", required = false) String middleName, @RequestParam(name = "lastName", required = false) String lastName,
                                          @RequestParam(name = "gender", required = false) Gender gender, @RequestParam(name = "bio", required = false) String bio,
                                          @RequestParam(name = "graduation", required = false) String graduation, @RequestParam(name = "unionDate", required = false) String unionDate,
                                          @RequestParam(name = "deathDate", required = false) String deathDate, @RequestParam(name = "parentId", required = false) String parentId,
                                          @RequestParam(name = "parentIdTwo", required = false) String parentIdTwo, @RequestParam(name = "spouseId", required = false) String spouseId){
        if (errors.hasErrors()) {
            return "person/update";
        }

        int i = Integer.parseInt(id);
        int parId = Integer.parseInt(parentId);
        int parIdTwo = Integer.parseInt(parentIdTwo);
        int spId = Integer.parseInt(spouseId);
//        Date grad = new SimpleDateFormat("MM/dd/yyyy").parse(graduation);
        Optional<Person> optPerson = personRepository.findById(i);
        Person currentPerson = optPerson.get();
        model.addAttribute(currentPerson);
        currentPerson.setFirstName(firstName);
        currentPerson.setMiddleName(middleName);
        currentPerson.setLastName(lastName);
        currentPerson.setGender(gender);
        currentPerson.setParentId(parId);
        currentPerson.setParentIdTwo(parIdTwo);
        currentPerson.setSpouseId(spId);
//        currentPerson.setGraduation(graduation);
//        currentPerson.setUnionDate(unionDate);
//        currentPerson.setDeathDate(deathDate);
        currentPerson.setBio(bio);
        personRepository.save(currentPerson);
        return "redirect:/person/view/" + currentPerson.getId();
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
            Person parentTwo = optParentTwo.get();
            model.addAttribute("parentTwo", parentTwo);
        }

        Optional<Person> optSpouse = personRepository.findById(person.getSpouseId());
        if (optSpouse.isEmpty()) {
            model.addAttribute("title", "View Person");
        } else {
            Person spouse = optSpouse.get();
            model.addAttribute("spouse", spouse);
        }
        return "person/view";
    }
}
