package at.kaindorf.springburger.repo;

import at.kaindorf.springburger.beans.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}