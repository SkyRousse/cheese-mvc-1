package org.launchcode.models.data;

import org.launchcode.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {

}
