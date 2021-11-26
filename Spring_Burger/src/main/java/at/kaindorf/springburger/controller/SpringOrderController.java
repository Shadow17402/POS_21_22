package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.beans.Burger;
import at.kaindorf.springburger.beans.Order;
import at.kaindorf.springburger.repo.BurgerRepository;
import at.kaindorf.springburger.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/orders")
public class SpringOrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BurgerRepository burgerRepository;

    @GetMapping("/current")
    public String requestOrder(Model model, @SessionAttribute Burger designBurger){
        log.info("Your Burger: " + designBurger);
        model.addAttribute("designBurger", designBurger);
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping()
    public String performOrder(@Valid @ModelAttribute Order order,@SessionAttribute Burger designBurger , Errors errors){
        if(errors.hasErrors()){
            log.info(errors.getObjectName()+ " " + errors.getAllErrors());
            return "orderForm";
        }
        log.info("Your order: " + order);
        order.addBurger(designBurger);
        orderRepository.save(order);
        burgerRepository.saveAndFlush(designBurger);
        return "redirect:/design";
    }

}
