package fi.swd20.bakingRecipes.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class SpecialDiet {
	// attribuutit
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long specialDietId;

	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "specialDiet")
	@JsonIgnoreProperties("specialDiet")
	private List<Recipe> recipes;

	// konstruktorit
	public SpecialDiet(String name) {
		super();
		this.name = name;
	}

	public SpecialDiet() {
		super();
		this.name = null;
	}

	// getterit ja setterit
	public Long getSpecialDietId() {
		return specialDietId;
	}

	public void setSpecialDietId(Long specialDietId) {
		this.specialDietId = specialDietId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	@Override
	public String toString() {
		return "SpecialDiet [specialDietId=" + specialDietId + ", name=" + name + "]";
	}
}
