package com.pizzeria.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pizzeria.java.model.Pizza;
import com.pizzeria.java.repo.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaRepo;

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
		return "/pizzas/show";
	}

	// CREATE
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("pizza", new Pizza());
		return ("/pizzas/create");
	}

	// STORE
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResults, Model model,
			RedirectAttributes redirectMessage) {
		if (bindingResults.hasErrors()) {
			return ("/pizzas/create");
		}

		pizzaRepo.save(pizzaForm);

		redirectMessage.addFlashAttribute("createMessage", pizzaForm.capName() + " è stata aggiunta");
		return ("redirect:/pizzas");
	}

	// EDIT
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("pizza", pizzaRepo.findById(id).get());

		return ("/pizzas/edit");
	}
	// UPDATE

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza pizzaEdit, BindingResult bindingResults, Model model) {
		if (bindingResults.hasErrors()) {
			return ("/pizzas/edit");
		}

		pizzaRepo.save(pizzaEdit);
		return ("redirect:/pizzas/{id}");
	}
	// DELETE

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes deleteMessage) {

		String pizzaName = pizzaRepo.findById(id).get().capName();
		pizzaRepo.deleteById(id);

		deleteMessage.addFlashAttribute("deleteMessage", pizzaName + " è stata rimossa");
		return ("redirect:/pizzas");
	}
}
