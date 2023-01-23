package org.launchcode.familytree.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="tree")
public class HomeController {

    @RequestMapping (value = "")
    public String index(Model model){
        model.addAttribute("title", "My Family Tree");
        return "tree/index";
    }

}
