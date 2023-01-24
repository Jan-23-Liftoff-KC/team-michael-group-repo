package org.launchcode.familytree.models;

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
    private ArrayList<String> familyMembers = new ArrayList<>();

    public Person(String name, String bio, Image photo, ArrayList<String> familyMembers) {
        this.name = name;
        this.bio = bio;
        this.photo = photo;
        this.familyMembers = familyMembers;
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

    public ArrayList<String> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(ArrayList<String> familyMembers) {
        this.familyMembers = familyMembers;
    }
}
