package fi.swd20.bakingRecipes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Recipe {

	// attribuutit
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 5, max = 40)
	private String name;

	private String description;
	private String difficultyLevel;
	private String ingredient;

	@ManyToOne
	@JsonIgnoreProperties("recipes")
	@JoinColumn(name = "categoryId")
	private Category category;

	@ManyToOne
	@JsonIgnoreProperties("recipes")
	@JoinColumn(name = "specialDietId")
	private SpecialDiet specialDiet;

	// konstruktorit
	public Recipe(String name, String description, String difficultyLevel, String ingredient, Category category,
			SpecialDiet specialDiet) {
		super();
		this.name = name;
		this.description = description;
		this.difficultyLevel = difficultyLevel;
		this.ingredient = ingredient;
		this.category = category;
		this.specialDiet = specialDiet;
	}

	public Recipe() {
		super();
		this.id = 0L;
		this.name = null;
		this.description = null;
		this.difficultyLevel = null;
		this.ingredient = null;
	}

	public Recipe(String name, String description, String difficultyLevel, String ingredient) {
		super();
		this.name = name;
		this.description = description;
		this.difficultyLevel = difficultyLevel;
		this.ingredient = ingredient;
	}

	// getterit ja setterit
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SpecialDiet getSpecialDiet() {
		return specialDiet;
	}

	public void setSpecialDiet(SpecialDiet specialDiet) {
		this.specialDiet = specialDiet;
	}

	// toString
	@Override
	public String toString() {
		if (this.category != null && this.specialDiet != null)
			return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", difficultyLevel="
					+ difficultyLevel + ", ingredient=" + ingredient + " category =" + this.getCategory()
					+ " specialDiet =" + this.getSpecialDiet() + "]";
		else
			return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", difficultyLevel="
					+ difficultyLevel + ", ingredient=" + ingredient + "]";
	}
}
