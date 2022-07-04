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
public class ProizvodRestController {
	@Autowired
	private ProizvodRepository proizvodRepository;
	
	@Autowired
	private VrstaRepository vrstaRepository ;
	
	@GetMapping("proizvod")
	public Collection <Proizvod> getProizvodi(){
		return proizvodRepository.findAll();
	}
	
	@GetMapping("proizvod/{id}")
	public Proizvod getProizvod(@PathVariable("id") Integer id)
	{
		return this.proizvodRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Proizvod nije pronadjen"));
	}
	
	@GetMapping("proizvodNaziv/{naziv}")
	public Collection<Proizvod> getProizvodByNaziv(@PathVariable("naziv") String naziv){
			return proizvodRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@GetMapping("proizvodByVrsta/{id}")
	public Collection<Proizvod> getProizvodByVrsta(@PathVariable("id") Integer id){
		Vrsta v= vrstaRepository.getOne(id);
		return proizvodRepository.findByVrsta(v);
	}
	
	@PostMapping("proizvod")
	public ResponseEntity<Proizvod> insertProizvod(@RequestBody Proizvod proizvod) {
		if(!proizvodRepository.existsById(proizvod.getId())) {
			proizvodRepository.save(proizvod);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/*@PutMapping("proizvod")
	public ResponseEntity<Proizvod> updateProizvod(@RequestBody Proizvod proizvod) {
		if(!proizvodRepository.existsById(proizvod.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		proizvodRepository.save(proizvod);
		return new ResponseEntity<>(HttpStatus.OK);

		
	}*/
	
	@PutMapping("proizvod/{id}")
	public Proizvod updateProizvod(@PathVariable Integer id, @RequestBody Proizvod proizvodDetails){
			Proizvod proizvod = proizvodRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Nije pronadjen proizvod"));
			
			proizvod.setNaziv(proizvodDetails.getNaziv());
			proizvod.setVrsta(proizvodDetails.getVrsta());
			proizvod.setCena(proizvodDetails.getCena());
			proizvod.setUser(proizvodDetails.getUser());
			proizvod.setKolicina(proizvodDetails.getKolicina());
			proizvod.setSlika(proizvodDetails.getSlika());

			return proizvodRepository.save(proizvod);
	}
	
	@DeleteMapping("proizvod/{id}")
	public ResponseEntity<Proizvod> deleteProizvod(@PathVariable ("id") Integer id) {
		Proizvod existingProizvod = this.proizvodRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Proizvod ne postoji"));
		this.proizvodRepository.delete(existingProizvod);
		return ResponseEntity.ok().build();

	}
	
	
}
