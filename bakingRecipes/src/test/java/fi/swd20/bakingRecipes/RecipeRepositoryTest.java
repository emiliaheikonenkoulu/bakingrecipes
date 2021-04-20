package fi.swd20.bakingRecipes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.swd20.bakingRecipes.domain.Recipe;
import fi.swd20.bakingRecipes.domain.RecipeRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RecipeRepositoryTest {

	@Autowired
	private RecipeRepository reciperepository;

	@Test // testataan RecipeRepositoryn findByName() -metodin toimivuutta
	public void finByNameShouldReturnRecipe() {
		List<Recipe> recipes = reciperepository.findByName("Lettutaikina");
		assertThat(recipes).hasSize(1);
		assertThat(recipes.get(0).getDescription()).isEqualTo("Lettu, lätty, ohukainen, räiskäle, kreppi.");
	}

	@Test // testataan uuden reseptin lisäämistä
	public void createNewRecipe() {
		Recipe recipe = new Recipe("Pannukakku", "Nopeasti makean nälkään.", "Helppo",
				"Muna, maito, jauho, sokeri ja rasva.", null, null);
		reciperepository.save(recipe);
		assertThat(recipe.getId()).isNotNull();
	}

	@Test // testataan reseptin poistamista
	@Rollback(false)
	public void deleteRecipe() {
		Recipe recipe = reciperepository.findById(Long.valueOf(9)).get();
		reciperepository.delete(recipe);
		Optional<Recipe> deleteRecipe = reciperepository.findById(Long.valueOf(9));
		assertThat(deleteRecipe).isEmpty();
	}

	@Test // testataan kaikkien reseptien hakemista
	public void searchRecipes() {
		List<Recipe> recipes = (List<Recipe>) reciperepository.findAll();
		assertThat(recipes).isNotNull();
	}

	@Test // testataan yhden reseptin hakemista
	public void searchRecipe() {
		List<Recipe> recipes = reciperepository.findByName("Lettutaikina");
		assertThat(recipes).isNotNull();
	}
}
