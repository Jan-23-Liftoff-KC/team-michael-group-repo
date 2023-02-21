package org.launchcode.familytree.controllers;

import org.launchcode.familytree.models.Tree;
import org.launchcode.familytree.models.TreeData;
import org.launchcode.familytree.data.BranchRepository;
import org.launchcode.familytree.data.TreeRepository;
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
    private TreeRepository treeRepository;

    @Autowired
    private BranchRepository branchRepository;
    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("branch", "Branch");

    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("branches", branchRepository.findAll());

        return "list";
    }

    @RequestMapping(value = "trees")
    public String listTreesByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Tree> trees;
        if (column.toLowerCase().equals("all")){
            trees = treeRepository.findAll();
            model.addAttribute("title", "All Trees");
        } else {
            trees = TreeData.findByColumnAndValue(column, value, treeRepository.findAll());
            model.addAttribute("title", "Trees with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("trees", trees);


        return "list-trees";
    }


}