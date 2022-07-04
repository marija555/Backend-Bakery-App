package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Dostavljac;

public interface DostavljacRepository extends JpaRepository<Dostavljac, Integer> {

	Collection<Dostavljac> findByNazivdostavljacaContainingIgnoreCase(String naziv );
	
}
