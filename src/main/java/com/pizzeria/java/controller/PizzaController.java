package com.pizzeria.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pizzeria.java.model.Pizza;
import com.pizzeria.java.repo.PizzaRepository;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaRepo;

	private String lorem = "Lorem ipsum dolor sit amet, consectetur adipisci elit";

	// READ
	@GetMapping
	public String index(Model model, @RequestParam(name = "name", required = false) String name) {

		List<Pizza> pizzas;

		if (name != null && !name.isEmpty()) {

			pizzas = pizzaRepo.findByNameContains(name);
		} else {
			pizzas = pizzaRepo.findAll(Sort.by("name"));
		}
		model.addAttribute("pizzas", pizzas);
		return "/pizzas/index";
	}

	// SHOW
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("thisPizza", pizzaRepo.findById(id).get());
		model.addAttribute("pizzas", pizzaRepo.findAll());
		model.addAttribute("lorem", lorem);
		return "/pizzas/show";
	}

	// CREATE
	@GetMapping("/create")
	public String create(Model model) {
		return ("/pizzas/create");
	}

	// STORE
	@PostMapping("/create")
	public String store(Model model) {
		return ("redirect:/pizzas/create");
	}
	// EDIT
	// UPDATE
	// DELETE
}
