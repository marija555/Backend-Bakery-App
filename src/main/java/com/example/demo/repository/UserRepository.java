package com.example.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.jpa.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

	Collection<User> findByImeContainingIgnoreCase(String ime );
	Collection<User> findByPrezimeContainingIgnoreCase(String prezime );
	Collection<User> findByUsernameContainingIgnoreCase(String username );
	
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

}
