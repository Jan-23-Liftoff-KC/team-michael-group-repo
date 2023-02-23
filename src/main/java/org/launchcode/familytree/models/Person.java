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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@DynamicInsert
@DynamicUpdate
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

//    @NotBlank(message = "Please select at least one parent.")
    private int parentId;
    private int parentIdTwo;
    private int spouseId;

    private Gender gender;

    public Person(){}

    public Person(String firstName, String middleName, String lastName, String bio, ArrayList<Person> familyMembers, Date birthday, Date unionDate, Date graduation, Date deathDate, int parentId, int parentIdTwo, int spouseId, Gender gender) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.bio = bio;
        this.familyMembers = familyMembers;
        this.birthday = birthday;
        this.unionDate = unionDate;
        this.graduation = graduation;
        this.deathDate = deathDate;
        this.parentId = parentId;
        this.parentIdTwo = parentIdTwo;
        this.spouseId = spouseId;
        this.gender = gender;
    }

//    public Person(String firstName, String lastName, String bio, Date birthday) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.bio = bio;
//        this.birthday = birthday;
//    }

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

    public int getParentIdTwo() {
        return parentIdTwo;
    }

    public void setParentIdTwo(int parentIdTwo) {
        this.parentIdTwo = parentIdTwo;
    }

    public int getSpouseId() {
        return spouseId;
    }

    public void setSpouseId(int spouseId) {
        this.spouseId = spouseId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
