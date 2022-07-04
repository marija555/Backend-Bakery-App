package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.Pekara;

public interface PekaraRepository extends JpaRepository<Pekara, Integer> {

	Collection<Pekara> findByNazivpekareContainingIgnoreCase(String naziv );

}
