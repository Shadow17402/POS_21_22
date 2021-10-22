package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.beans.Burger;
import at.kaindorf.springburger.beans.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
public class SpringBurgerController {

    private List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("120B","120g Ground Beef", Ingredient.Type.PATTY),
            new Ingredient("160B","160g Ground Beef", Ingredient.Type.PATTY),
            new Ingredient("140T","140g Turkey", Ingredient.Type.PATTY),
            new Ingredient("120T","120g Turkey", Ingredient.Type.PATTY),
            new Ingredient("TOMA","Tomatoe", Ingredient.Type.VEGGIE),
            new Ingredient("SALA","Salad", Ingredient.Type.VEGGIE),
            new Ingredient("ONIO","Onions", Ingredient.Type.VEGGIE),
            new Ingredient("CHED","Cheddar", Ingredient.Type.CHEESE),
            new Ingredient("GAUD","Gauda", Ingredient.Type.CHEESE)
    );

    /*public Ingredient getIngredientByName(String name){
        return ingredients.stream().filter(i -> i.getName().equals(name)).findFirst().get();
    }*/

    @ModelAttribute
    public void addAttributes(Model model){
        Map<String, List<Ingredient>> ingredientMap = new HashMap<>();
        for(Ingredient.Type ingredientType : Ingredient.Type.values()){
            ingredientMap.put(ingredientType.name().toLowerCase(),filterByType(ingredientType));
        }
        model.addAttribute("ingredients",ingredientMap);
        model.addAttribute("designBurger", new Burger());
    }

    private List<Ingredient> filterByType(Ingredient.Type type){
        return ingredients.stream()
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping
    public String showDesign() {
        return "designForm";
    }

    @PostMapping
    public String processBurger(@ModelAttribute("designBurger") Burger burger){
        log.info(burger.toString());
        return "designForm";
    }

}
