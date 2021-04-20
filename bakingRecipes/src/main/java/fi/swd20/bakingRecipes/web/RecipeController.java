package fi.swd20.bakingRecipes.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.swd20.bakingRecipes.domain.CategoryRepository;
import fi.swd20.bakingRecipes.domain.Recipe;
import fi.swd20.bakingRecipes.domain.RecipeRepository;
import fi.swd20.bakingRecipes.domain.SpecialDietRepository;

@Controller
public class RecipeController {
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	SpecialDietRepository specialDietRepository;
	
	// REST palvelu, joka palauttaa kaikki kirjat
    @RequestMapping(value="/recipes", method = RequestMethod.GET)
    public @ResponseBody List<Recipe> getRecipesRest() {	
        return (List<Recipe>) recipeRepository.findAll();
    }
    
    // REST palvelu, joka palauttaa yhden reseptin id:n perusteella
    @RequestMapping(value="/recipes/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Recipe> findRecipeRest(@PathVariable("id") Long rId) {	
    	return recipeRepository.findById(rId);
    }
    
    // REST metodi, joka lisää tietokantaan JSON:na tulleen uuden reseptin tiedot
    @PostMapping("/recipes")
    public @ResponseBody Recipe saveRecipeRest(@RequestBody Recipe recipe) {
 	   recipeRepository.save(recipe);
 	   return recipe;
    }
	
	// reseptilistaus
	@RequestMapping(value = "/recipelist", method = RequestMethod.GET)
	public String getRecipes(Model model) {
		List<Recipe> recipes = (List<Recipe>) recipeRepository.findAll();
		model.addAttribute("recipes", recipes);
		return "recipelist";
	}
	
	// tyhjän reseptilomakkeen muodostaminen
	@RequestMapping(value = "/newrecipe", method = RequestMethod.GET)
	public String getNewRecipeForm(Model model) {
		model.addAttribute("recipe", new Recipe()); // tyhjä resepti olio
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("specialDiets", specialDietRepository.findAll());
		return "addrecipe";
	}
	
	// resepti lomakkeelle syötettyjen tietojen vastaanottaminen sekä tallenus
	@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String saveRecipe(@ModelAttribute Recipe recipe) {
		recipeRepository.save(recipe);
		return "redirect:/recipelist";
	}
	
	// reseptin poistaminen
	@RequestMapping(value = "/deleterecipe/{id}", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public String deleteRecipe(@PathVariable("id") Long recipeId) {
		recipeRepository.deleteById(recipeId);
		return "redirect:../recipelist";
	}
	
	// reseptin muokkaus lomake
	@RequestMapping(value = "/editrecipe/{id}")
	@PreAuthorize("isAuthenticated()")
	public String showUpdateForm(@PathVariable("id") Long recipeId, Model model) {
		model.addAttribute("recipe", recipeRepository.findById(recipeId));
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("specialDiets", specialDietRepository.findAll());
		return "editrecipe";
			}
	
}
