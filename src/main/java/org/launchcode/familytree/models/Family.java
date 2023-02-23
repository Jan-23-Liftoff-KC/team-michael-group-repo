package org.launchcode.familytree.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Family extends AbstractEntity{
    @ManyToOne
    private Person person;
    public Family() {
    }

    public Family(Person person) {
        super();
        this.person = person;
    }

    // Getters and setters.


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
