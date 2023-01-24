package org.launchcode.familytree.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="person")
public class PersonController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "About Me");
        return "person/index";
    }


}
