package org.launchcode.familytree.controllers;

import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Family;
import org.launchcode.familytree.models.FamilyData;
import org.launchcode.familytree.data.FamilyRepository;
import org.launchcode.familytree.models.FamilyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private PersonRepository personRepository;
    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("person", "Person");


    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("person", personRepository.findAll());

        return "list";
    }

    @RequestMapping(value = "families")
    public String listFamiliesByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Family> families;
        if (column.toLowerCase().equals("all")){
            families = familyRepository.findAll();
            model.addAttribute("title", "All Families");
        } else {
            families = FamilyData.findByColumnAndValue(column, value, familyRepository.findAll());
            model.addAttribute("title", "Families with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("families", families);


        return "list-families";
    }


}