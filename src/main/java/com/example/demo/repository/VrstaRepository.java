package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Proizvod;
import com.example.demo.jpa.Vrsta;

public interface VrstaRepository extends JpaRepository<Vrsta, Integer> {

	Collection<Vrsta> findByNazivvrsteproizvodaContainingIgnoreCase(String naziv );
	
}
