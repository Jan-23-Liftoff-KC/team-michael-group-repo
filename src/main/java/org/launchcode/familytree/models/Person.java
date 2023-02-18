package org.launchcode.familytree.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50 characters.")
    private String firstName;
    private String middleName;
    @NotBlank
    @Size(min = 3, max = 100, message = "Last name must be between 3 and 100 characters.")
    private String lastName;
    @NotBlank(message = "Biography is required. You may edit it later.")
    private String bio;
//    private Image photo;
    private ArrayList<Person> familyMembers = new ArrayList<>();

    @NotNull(message = "Birthday cannot be left blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date unionDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date graduation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deathDate;

    private int parentId;
    private int spouseId;


    public Person(){}

    public Person(String firstName, String lastName, String bio, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getUnionDate() {
        return unionDate;
    }

    public void setUnionDate(Date unionDate) {
        this.unionDate = unionDate;
    }

    public Date getGraduation() {
        return graduation;
    }

    public void setGraduation(Date graduation) {
        this.graduation = graduation;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSpouseId() {
        return spouseId;
    }

    public void setSpouseId(int spouseId) {
        this.spouseId = spouseId;
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
