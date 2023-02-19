package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Person;
import org.launchcode.familytree.models.PersonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("search")

public class SearchController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Person> persons;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            persons = personRepository.findAll();
        } else {
//persons?
            person = PersonData.findByColumnAndValue(searchType, searchTerm, personRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Persons with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("person", person);
//persons?
        return "search";
    }


}