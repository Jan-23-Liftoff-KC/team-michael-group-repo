package org.launchcode.familytree.models;

import org.springframework.lang.Nullable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.awt.*;
import java.util.ArrayList;

public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String bio;
    private Image photo;
    private ArrayList<Person> familyMembers = new ArrayList<>();

    public Person(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public ArrayList<Person> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(ArrayList<Person> familyMembers) {
        this.familyMembers = familyMembers;
    }
}
