package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.beans.Burger;
import at.kaindorf.springburger.beans.Ingredient;
import at.kaindorf.springburger.repo.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("designBurger")
public class SpringBurgerController {

    private List<Ingredient> ingredients;
    @Autowired
    private IngredientRepository ingredientRepository;


    public Ingredient getIngredientByName(String name){
        return ingredients.stream().filter(i -> i.getName().equals(name)).findFirst().get();
    }

    @ModelAttribute
    public void addAttributes(Model model){
        ingredients = ingredientRepository.findAll();
        Map<String, List<Ingredient>> ingredientMap = new HashMap<>();
        for (Ingredient.Type type: Ingredient.Type.values()) {
            ingredientMap.put(type.name().toLowerCase(),filterByType(type));
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
    public String processBurger(@Valid @ModelAttribute("designBurger") Burger burger, Errors errors){
        log.info(burger.toString());
        if(errors.hasErrors()){
            log.info(errors.getObjectName()+ " " + errors.getAllErrors());
            return "designForm";
        }
        return "redirect:/orders/current";
    }
}
