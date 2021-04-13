package fi.swd20.bakingRecipes.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.swd20.bakingRecipes.domain.SpecialDiet;
import fi.swd20.bakingRecipes.domain.SpecialDietRepository;

@Controller
public class SpecialDietController {

	@Autowired
	SpecialDietRepository specialDietRepository;
	
	// erikoisruokavalioiden listaus
		@RequestMapping(value = "/specialdietlist", method = RequestMethod.GET)
		public String getSpecialDiets(Model model) {
			List<SpecialDiet> specialDiets =  (List<SpecialDiet>) specialDietRepository.findAll();
			model.addAttribute("specialDiets", specialDiets);
			return "specialdietlist";
		}
}
