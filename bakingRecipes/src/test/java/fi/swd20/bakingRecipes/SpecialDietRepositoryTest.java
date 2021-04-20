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

import fi.swd20.bakingRecipes.domain.SpecialDiet;
import fi.swd20.bakingRecipes.domain.SpecialDietRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SpecialDietRepositoryTest {

	@Autowired
	private SpecialDietRepository specialdietrepository;

	@Test // testataan SpecialDietRepositoryn findByName() -metodin toimivuutta
	public void finByNameShouldReturnSpecialDiet() {
		List<SpecialDiet> specialdiets = specialdietrepository.findByName("Pähkinätön");
		assertThat(specialdiets).hasSize(1);
		assertThat(specialdiets.get(0).getSpecialDietId()).isEqualTo(2);
	}

	@Test // testataan uuden erityisruokavalion lisäämistä
	public void createNewSpecialDiet() {
		SpecialDiet specialdiet = new SpecialDiet("Laktoositon");
		specialdietrepository.save(specialdiet);
		assertThat(specialdiet.getSpecialDietId()).isNotNull();
	}

	@Test // testataan erityisruokavalion poistamista
	@Rollback(false)
	public void deleteRecipe() {
		SpecialDiet specialdiet = specialdietrepository.findById(Long.valueOf(4)).get();
		specialdietrepository.delete(specialdiet);
		Optional<SpecialDiet> deleteSpecialDiet = specialdietrepository.findById(Long.valueOf(4));
		assertThat(deleteSpecialDiet).isEmpty();
	}

	@Test // testataan kaikkien erityisruokavalioiden hakemista
	public void searchSpecialDiets() {
		List<SpecialDiet> specialdiets = (List<SpecialDiet>) specialdietrepository.findAll();
		assertThat(specialdiets).isNotNull();
	}

	@Test // testataan yhden erityisruokavalion hakemista
	public void searchSpecialDiet() {
		List<SpecialDiet> specialdiets = specialdietrepository.findByName("Liivatteeton");
		assertThat(specialdiets).isNotNull();
	}

}
