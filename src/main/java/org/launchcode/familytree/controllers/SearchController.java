package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Person;
import org.launchcode.familytree.models.PersonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import static org.launchcode.familytree.controllers.ListController.columnChoices;

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
        Iterable<Person> person;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            person = personRepository.findAll();
        } else {
            person = PersonData.findByColumnAndValue(searchType, searchTerm, personRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Search Results " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("persons", person);

        return "search";
    }
}