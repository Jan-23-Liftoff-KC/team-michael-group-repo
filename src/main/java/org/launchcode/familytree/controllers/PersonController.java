package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Gender;
import org.launchcode.familytree.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
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
    public String processUpdatePersonForm(@ModelAttribute Person person, RedirectAttributes model, Errors errors,
                                          @RequestParam(name = "id") String id, @RequestParam(name = "firstName", required = false) String firstName,
                                          @RequestParam(name = "middleName", required = false) String middleName, @RequestParam(name = "lastName", required = false) String lastName,
                                          @RequestParam(name = "gender", required = false) Gender gender, @RequestParam(name = "bio", required = false) String bio,
                                          @RequestParam(name = "memories", required = false) String memories,
                                          @RequestParam(name = "graduation", required = false) String graduation, @RequestParam(name = "unionDate", required = false) String unionDate,
                                          @RequestParam(name = "deathDate", required = false) String deathDate, @RequestParam(name = "parentId", required = false) String parentId,
                                          @RequestParam(name = "parentIdTwo", required = false) String parentIdTwo, @RequestParam(name = "spouseId", required = false) String spouseId) throws ParseException {
        if (errors.hasErrors()) {
            return "person/update";
        }

        int i = Integer.parseInt(id);
        int parId = Integer.parseInt(parentId);
        int parIdTwo = Integer.parseInt(parentIdTwo);
        int spId = Integer.parseInt(spouseId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Optional<Person> optPerson = personRepository.findById(i);
        Person currentPerson = optPerson.get();
        model.addAttribute(currentPerson);
        if(graduation != null && !graduation.isEmpty()){
            Date grad = formatter.parse(graduation);
            currentPerson.setGraduation(grad);
        }
        if(unionDate != null && !unionDate.isEmpty()){
            Date union = formatter.parse(unionDate);
            currentPerson.setUnionDate(union);
        }
        if(deathDate != null && !deathDate.isEmpty()){
            Date death = formatter.parse(deathDate);
            currentPerson.setDeathDate(death);
        }
        currentPerson.setFirstName(firstName);
        currentPerson.setMiddleName(middleName);
        currentPerson.setLastName(lastName);
        currentPerson.setGender(gender);
        currentPerson.setParentId(parId);
        currentPerson.setParentIdTwo(parIdTwo);
        currentPerson.setSpouseId(spId);
        currentPerson.setBio(bio);
        currentPerson.setMemories(memories);
        personRepository.save(currentPerson);
        return "redirect:/person/view/" + currentPerson.getId();
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
