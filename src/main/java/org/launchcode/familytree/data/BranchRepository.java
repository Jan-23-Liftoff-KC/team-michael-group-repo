package org.launchcode.familytree.data;

import org.launchcode.familytree.models.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BranchRepository extends CrudRepository<Branch, Integer> {
}