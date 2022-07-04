package com.example.demo.ctrl;

import java.util.Collection;

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
import com.example.demo.jpa.Pekara;
import com.example.demo.jpa.Porudzbina;
import com.example.demo.repository.PekaraRepository;


@RestController
@CrossOrigin(origins="*")
public class PekaraRestController {


	@Autowired
	private PekaraRepository pekaraRepository;
	
	@GetMapping("pekara")
	public Collection <Pekara> getPekare(){
		return pekaraRepository.findAll();
	}
	
	@GetMapping("pekara/{id}")
	public Pekara getPekara(@PathVariable("id") Integer id)
	{
		return this.pekaraRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pekara nije pronadjena"));
	}
	
	@GetMapping("pekaraNaziv/{naziv}")
	public Collection<Pekara> getPekaraByNazivpekare(@PathVariable("naziv") String naziv){
			return pekaraRepository.findByNazivpekareContainingIgnoreCase(naziv);
	}
	
	@PostMapping("pekara")
	public ResponseEntity<Pekara> insertPekara(@RequestBody Pekara pekara) {
		if(!pekaraRepository.existsById(pekara.getPekaraid())) {
			pekaraRepository.save(pekara);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/*@PutMapping("pekara")
	public ResponseEntity<Pekara> updatePekara(@RequestBody Pekara pekara) {
		if(!pekaraRepository.existsById(pekara.getPekaraid()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		pekaraRepository.save(pekara);
		return new ResponseEntity<>(HttpStatus.OK);

		
	}*/
	
	@PutMapping("pekara/{id}")
	public Pekara updatePekara(@PathVariable Integer id, @RequestBody Pekara pekaraDetails){
			Pekara pekara = pekaraRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Nije pronadjena pekara"));
			
			pekara.setNazivpekare(pekaraDetails.getNazivpekare());
			pekara.setGrad(pekaraDetails.getGrad());
			pekara.setUlica(pekaraDetails.getUlica());
			pekara.setKontaktpekare(pekaraDetails.getKontaktpekare());
		
			return this.pekaraRepository.save(pekara);
	}
	
	
	@DeleteMapping("pekara/{id}")
	public ResponseEntity<Pekara> deletePekara(@PathVariable ("id") Integer id) {
		Pekara existingPekara = this.pekaraRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pekara ne postoji"));
		this.pekaraRepository.delete(existingPekara);
		return ResponseEntity.ok().build();

	}
	
	
}
