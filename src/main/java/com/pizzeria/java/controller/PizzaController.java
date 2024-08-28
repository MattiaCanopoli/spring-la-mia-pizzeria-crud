package com.pizzeria.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pizzeria.java.model.Pizza;
import com.pizzeria.java.repo.PizzaRepository;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaRepo;

	private String lorem = "Lorem ipsum dolor sit amet, consectetur adipisci elit";

	@GetMapping
	public String index(Model model) {
		List<Pizza> pizzas = pizzaRepo.findAll();
		model.addAttribute("pizzas", pizzas);
		return "/pizzas/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("thisPizza", pizzaRepo.findById(id).get());
		model.addAttribute("pizzas", pizzaRepo.findAll());
		model.addAttribute("lorem", lorem);
		return "/pizzas/show";
	}
}
