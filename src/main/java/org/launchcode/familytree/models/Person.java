package org.launchcode.familytree.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Objects;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
    @Size(max = 2000)
    private String bio;
//    private Image photo;
    private ArrayList<Person> familyMembers = new ArrayList<>();

    private String birthday;

    public Person(){}

    public Person(String name, String bio, String birthday) {
        this.name = name;
        this.bio = bio;
        this.birthday = birthday;
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

//    public Image getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(Image photo) {
//        this.photo = photo;
//    }

    public ArrayList<Person> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(ArrayList<Person> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
