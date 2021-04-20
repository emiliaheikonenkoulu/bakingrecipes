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

import fi.swd20.bakingRecipes.domain.Category;
import fi.swd20.bakingRecipes.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryrepository;

	@Test // testataan CategoryRepositoryn findByName() -metodin toimivuus
	public void findByNameShouldReturnCategory() {
		List<Category> categories = categoryrepository.findByName("Kakut");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getCategoryId()).isEqualTo(6);
	}

	@Test // testataan uuden kategorian lisäämistä
	public void createNewCategory() {
		Category category = new Category("Suolaiset leivonnaiset");
		categoryrepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}

	@Test // testataan kategorian poistamista
	@Rollback(false)
	public void deleteCategory() {
		Category category = categoryrepository.findById(Long.valueOf(7)).get();
		categoryrepository.delete(category);
		Optional<Category> deleteCategory = categoryrepository.findById(Long.valueOf(9));
		assertThat(deleteCategory).isEmpty();
	}

	@Test // testataan kaikkien kategorioiden hakemista
	public void searchCcategories() {
		List<Category> categories = (List<Category>) categoryrepository.findAll();
		assertThat(categories).isNotNull();
	}

	@Test // testataan yhden kategorian hakemista
	public void searchCategory() {
		List<Category> categories = categoryrepository.findByName("Pullat");
		assertThat(categories).isNotNull();
	}
}
