package com.example.demo.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.PoruceniProizvod;
import com.example.demo.jpa.Proizvod;
import com.example.demo.jpa.User;

public interface PoruceniProizvodRepository extends JpaRepository<PoruceniProizvod, Integer> {
	Boolean existsByUser(User user);
	Collection<PoruceniProizvod>  findByUser(User u);	
	void deleteByUser (User u);
	 PoruceniProizvod getOneByUser(User u);
	
}
