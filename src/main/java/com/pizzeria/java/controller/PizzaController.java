package com.pizzeria.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizzeria.java.model.Pizza;
import com.pizzeria.java.repo.PizzaRepository;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaRepo;

	@GetMapping
	public String index(Model model) {
		List<Pizza> pizzas = pizzaRepo.findAll();
		model.addAttribute("pizzas", pizzas);
		return "/pizzas/index";
	}
}
