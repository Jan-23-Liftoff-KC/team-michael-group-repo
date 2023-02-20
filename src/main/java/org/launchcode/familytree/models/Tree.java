package org.launchcode.familytree.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Tree extends AbstractEntity{
    @ManyToOne
    private Branch branch;
    public Tree() {
    }

    public Tree(Branch branch) {
        super();
        this.branch = branch;
    }

    // Getters and setters.


    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }


}
