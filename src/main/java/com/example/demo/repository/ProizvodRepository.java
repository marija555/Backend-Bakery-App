package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Proizvod;
import com.example.demo.jpa.Vrsta;

public interface ProizvodRepository extends JpaRepository<Proizvod, Integer> {

	Collection<Proizvod> findByNazivContainingIgnoreCase(String naziv );
	Collection<Proizvod>  findByVrsta(Vrsta v);
} 
