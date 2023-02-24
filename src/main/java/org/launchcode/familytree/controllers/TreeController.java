package org.launchcode.familytree.controllers;

import org.json.simple.JSONArray;
import org.launchcode.familytree.data.PersonRepository;
import org.launchcode.familytree.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.simple.JSONObject;

@Controller
@RequestMapping(value="tree")
public class TreeController {

    @Autowired
    private PersonRepository personRepository;

    // Returns JSON with Persons from personRepository formatted for d3.stratify()
    @GetMapping(path="/data")
    public @ResponseBody Iterable<Person> getAllPersonsForTree() {

        JSONArray treeJSON = new JSONArray();
        boolean isRootFound = false;

        for (Person person : personRepository.findAll()) {
            JSONObject treePerson = new JSONObject();

            treePerson.put("id", person.getId());
            treePerson.put("firstName", person.getFirstName());
            treePerson.put("lastName", person.getLastName());

            // Only one root can exist in the tree (parentId = "")
            // After one root is found, all other potential root Persons are ignored in the tree
            if (person.getParentId() == 0) {
                if(!isRootFound) {
                    // Root found
                    treePerson.put("parentId", "");
                    isRootFound = true;
                } else {
                    // Ignore root, not added to treeJSON
                    continue;
                }
            } else {
                treePerson.put("parentId", person.getParentId());
            }

            treePerson.put("icon", "https://github.com/Jan-23-Liftoff-KC/team-michael-group-repo/blob/main/src/main/resources/test-tree-data/person-icon.png?raw=true"); //person.getIcon());
            treePerson.put("spouse", person.getSpouseId());

            treeJSON.add(treePerson);

        }
        return (Iterable<Person>) treeJSON;
    }
}

