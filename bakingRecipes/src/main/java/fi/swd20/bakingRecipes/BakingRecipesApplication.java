package fi.swd20.bakingRecipes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.swd20.bakingRecipes.domain.Category;
import fi.swd20.bakingRecipes.domain.CategoryRepository;
import fi.swd20.bakingRecipes.domain.Recipe;
import fi.swd20.bakingRecipes.domain.RecipeRepository;
import fi.swd20.bakingRecipes.domain.SpecialDiet;
import fi.swd20.bakingRecipes.domain.SpecialDietRepository;
import fi.swd20.bakingRecipes.domain.User;
import fi.swd20.bakingRecipes.domain.UserRepository;

@SpringBootApplication
public class BakingRecipesApplication {
	private static final Logger log = LoggerFactory.getLogger(BakingRecipesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BakingRecipesApplication.class, args);
	}

	// testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner RecipeDemo(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			SpecialDietRepository specialDietRepository, UserRepository userRepository) {
		return (args) -> {

			specialDietRepository.save(new SpecialDiet("Gluteeniton"));
			specialDietRepository.save(new SpecialDiet("Pähkinätön"));
			specialDietRepository.save(new SpecialDiet("Maidoton"));
			specialDietRepository.save(new SpecialDiet("Liivatteeton"));

			categoryRepository.save(new Category("Makeat leivonnaiset"));
			categoryRepository.save(new Category("Kakut"));
			categoryRepository.save(new Category("Pullat"));

			// reseptit
			log.info("save recipes");
			recipeRepository.save(new Recipe("Lettutaikina", "Lettu, lätty, ohukainen, räiskäle, kreppi.", "Helppo",
					"Kananmunia, maito, vehnäjauho, sokeri ja suola.",
					categoryRepository.findByName("Makeat leivonnaiset").get(0),
					specialDietRepository.findByName("Gluteeniton").get(0)));
			recipeRepository.save(new Recipe("Trio-kakku", "Kesäinen trio suklaa kakku maistuu varmasti kaikille!",
					"Helppo",
					"Oreo-keksejä, voita, kermaa, tuorejuustoa, tumma-, valko- ja maitosuklaa, liivatelehti, sokeri ja mansikoita.",
					categoryRepository.findByName("Kakut").get(0),
					specialDietRepository.findByName("Pähkinätön").get(0)));
			recipeRepository.save(new Recipe("Prinsessapullat", "Viehättävä, värikäs sekä herkullinen yhdistelmä.",
					"Vaativampi", "Voi, maito, hiiva, muna, vehnäjauho, marsipaani, hillo ja kerma.",
					categoryRepository.findByName("Pullat").get(0),
					specialDietRepository.findByName("Liivatteeton").get(0)));
			recipeRepository.save(
					new Recipe("Vaahtokarkkimuffinsit", "Resepti vie nuotiolla grillattujen vaahtokarkkien makuun.",
							"Keskitaso", "Kerma, vaahtokarkki, leivinjauhe, jauho, kaakao, muna ja suklaa.",
							categoryRepository.findByName("Makeat leivonnaiset").get(0),
							specialDietRepository.findByName("Pähkinätön").get(0)));
			recipeRepository.save(new Recipe("Omenapiirakka", "Klassinen, mutta herkullinen resepti.", "Helppo",
					"Omena, voi, sokeri, jauho, muna, vesi ja sitruunan raaste.",
					categoryRepository.findByName("Makeat leivonnaiset").get(0),
					specialDietRepository.findByName("Maidoton").get(0)));

			// käyttäjät
			User user1 = new User("user", "$2a$10$2DxQgGDhrCNFvzp8N.RbhuMj7QdnrOSg8sd2v.KUpT1fwDuHPoDsW", "USER");
			User user2 = new User("admin", "$2a$10$XvadCvITo0MLCu54zoxfyOiZVSAutMXcKpUo5qs4FM2oJj83VYeE.", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

			log.info("fetch all recipes");
			for (Recipe recipe : recipeRepository.findAll()) {
				log.info(recipe.toString());
			}
		};
	}
}
