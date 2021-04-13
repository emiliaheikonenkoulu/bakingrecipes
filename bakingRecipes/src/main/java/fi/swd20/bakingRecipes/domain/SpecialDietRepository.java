package fi.swd20.bakingRecipes.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SpecialDietRepository extends CrudRepository<SpecialDiet, Long> {

	List<SpecialDiet> findByName(String name);
}
