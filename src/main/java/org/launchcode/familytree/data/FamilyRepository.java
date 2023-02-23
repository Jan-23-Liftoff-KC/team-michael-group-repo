package org.launchcode.familytree.data;


import org.launchcode.familytree.models.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface FamilyRepository extends CrudRepository<Family, Integer> {
}
