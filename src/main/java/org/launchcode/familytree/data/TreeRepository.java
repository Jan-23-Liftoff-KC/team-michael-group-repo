package org.launchcode.familytree.data;


import org.launchcode.familytree.models.Tree;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface TreeRepository extends CrudRepository<Tree, Integer> {
}
