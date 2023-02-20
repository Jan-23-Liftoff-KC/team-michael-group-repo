package org.launchcode.familytree.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Branch extends AbstractEntity {

    @NotBlank
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters long")
    private String location;

    @OneToMany
    @JoinColumn(name = "branch_id")
    private final List<Tree> trees = new ArrayList<>();
    public Branch() {

    }

    public List<Tree> getTrees() {
        return trees;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
