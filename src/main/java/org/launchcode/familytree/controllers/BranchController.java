package org.launchcode.familytree.controllers;

import org.launchcode.familytree.models.Branch;
import org.launchcode.familytree.data.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("branch")
public class BranchController {


    @Autowired
    private BranchRepository branchRepository;

    @GetMapping("")
    public String index(Model model) {

        model.addAttribute("branches", branchRepository.findAll());
        return "branch/index";
    }

    @GetMapping("add")
    public String displayAddBranchForm(Model model) {
        model.addAttribute(new Branch());
        return "branch/add";
    }

    @PostMapping("add")
    public String processAddBranchForm(@ModelAttribute @Valid Branch newBranch,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute(branchRepository.findAll());
            return "branch/add";
        }
// refer to Branch.java for Intellij addition
        branchRepository.save(newBranch);
        return "redirect:";
    }

    @GetMapping("view/{branchId}")
    public String displayViewBranch(Model model, @PathVariable int branchId) {

        Optional optBranch = branchRepository.findById(branchId);
        if (optBranch.isPresent()) {
            Branch branch = (Branch) optBranch.get();
            model.addAttribute("branch", branch);
            return "branch/view";
        } else {
            return "redirect:../";
        }
    }
}