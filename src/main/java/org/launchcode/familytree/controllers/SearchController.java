package org.launchcode.familytree.controllers;

import org.launchcode.familytree.models.Tree;
import org.launchcode.familytree.models.TreeData;
import org.launchcode.familytree.data.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import static org.launchcode.familytree.controllers.ListController.columnChoices;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private TreeRepository treeRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Tree> trees;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            trees = treeRepository.findAll();
        } else {
            trees = TreeData.findByColumnAndValue(searchType, searchTerm, treeRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Trees with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("trees", trees);

        return "search";
    }
}
