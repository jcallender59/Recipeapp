package com.qa.recipes.repo;
import org.springframework.data.repository.CrudRepository;
//
        import com.qa.recipes.domain.recipes;

public interface reciperepo extends CrudRepository<recipes, Long>{

}