package fi.swd20.bakingRecipes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.swd20.bakingRecipes.web.CategoryController;
import fi.swd20.bakingRecipes.web.RecipeController;
import fi.swd20.bakingRecipes.web.SpecialDietController;

@ExtendWith(SpringExtension.class) //JUnit5
@SpringBootTest
class BakingRecipesApplicationTests {
	
	@Autowired
	private RecipeController recipecontroller;
	
	@Autowired
	private CategoryController categorycontroller;
	
	@Autowired
	private SpecialDietController specialdietcontroller;

	@Test
	void contextLoads() {
		assertThat(recipecontroller).isNotNull();
	}
	
	@Test
	void contextLoad() {
		assertThat(categorycontroller).isNotNull();
	}
	
	@Test
	void contextLoading() {
		assertThat(specialdietcontroller).isNotNull();
	}

}
