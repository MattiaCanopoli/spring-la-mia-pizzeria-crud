package com.pizzeria.java.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzeria.java.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
