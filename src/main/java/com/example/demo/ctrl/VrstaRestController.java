package com.example.demo.ctrl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.jpa.Porudzbina;
import com.example.demo.jpa.Proizvod;
import com.example.demo.jpa.Vrsta;
import com.example.demo.repository.ProizvodRepository;
import com.example.demo.repository.VrstaRepository;

@RestController
@CrossOrigin(origins="*")
@Transactional

public class VrstaRestController {


	@Autowired
	private VrstaRepository vrstaproizvodaRepository;
	
	@Autowired
	private ProizvodRepository proizvodRepository;
	
	@GetMapping("vrstaproizvoda")
	public Collection <Vrsta> getVrste(){
		return vrstaproizvodaRepository.findAll();
	}
		
	
	@GetMapping("vrstaproizvoda/{id}")
	public Vrsta getVrsta(@PathVariable("id") Integer id)
	{
		return this.vrstaproizvodaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vrsta proizvoda nije pronadjena"));
	}
	@GetMapping("vrstaproizvodaNaziv/{naziv}")
	public Collection<Vrsta> getVrstaByNazivvrste(@PathVariable("naziv") String naziv){
			return vrstaproizvodaRepository.findByNazivvrsteproizvodaContainingIgnoreCase(naziv);
	}
	
	
	@PostMapping("vrstaproizvoda")
	public ResponseEntity<Vrsta> insertVrsta(@RequestBody Vrsta vrstaproizvoda) {
		if(!vrstaproizvodaRepository.existsById(vrstaproizvoda.getVrstaproizvodaid())) {
			vrstaproizvodaRepository.save(vrstaproizvoda);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/*@PutMapping("vrstaproizvoda")
	public ResponseEntity<Vrsta> updateVrsta(@RequestBody Vrsta vrstaproizvoda) {
		if(!vrstaproizvodaRepository.existsById(vrstaproizvoda.getVrstaproizvodaid()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		vrstaproizvodaRepository.save(vrstaproizvoda);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}*/
	
	@PutMapping("vrstaproizvoda/{id}")
	public Vrsta updateVrsta(@PathVariable Integer id, @RequestBody Vrsta vrstaproizvodaDetails){
			Vrsta vrstaproizvoda = vrstaproizvodaRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Nije pronadjena vrstaproizvoda"));
			
			vrstaproizvoda.setNazivvrsteproizvoda(vrstaproizvodaDetails.getNazivvrsteproizvoda());
	
			return vrstaproizvodaRepository.save(vrstaproizvoda);
	}
	
	@DeleteMapping("vrstaproizvoda/{id}")
	public ResponseEntity<Vrsta> deleteVrsta(@PathVariable ("id") Integer id) {
		Vrsta existingVrsta = this.vrstaproizvodaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vrsta ne postoji"));
		this.vrstaproizvodaRepository.delete(existingVrsta);
		return ResponseEntity.ok().build();


	}
	
	
}
