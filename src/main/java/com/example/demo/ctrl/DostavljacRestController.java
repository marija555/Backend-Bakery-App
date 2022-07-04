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
import com.example.demo.jpa.Dostavljac;
import com.example.demo.jpa.Porudzbina;
import com.example.demo.repository.DostavljacRepository;

@RestController
@CrossOrigin(origins="*")
public class DostavljacRestController {

	
	@Autowired
	private DostavljacRepository dostavljacRepository;
	
	@GetMapping("dostavljac")
	public Collection <Dostavljac> getDostavljaci(){
		return dostavljacRepository.findAll();
	}
	
	@GetMapping("dostavljac/{id}")
	public Dostavljac getDostavljacById(@PathVariable("id") Integer id)
	{
		return this.dostavljacRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dostavljac nije pronadjen"));

	}
	@GetMapping("dostavljacNaziv/{naziv}")
	public Collection<Dostavljac> getDostavljacByNazivdostavljaca(@PathVariable("naziv") String naziv){
			return dostavljacRepository.findByNazivdostavljacaContainingIgnoreCase(naziv);
	}
	
	@PostMapping("dostavljac")
	public ResponseEntity<Dostavljac> insertDostavljac(@RequestBody Dostavljac dostavljac) {
		if(!dostavljacRepository.existsById(dostavljac.getDostavljacid())) {
			dostavljacRepository.save(dostavljac);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/*@PutMapping("dostavljac")
	public ResponseEntity<Dostavljac> updateDostavljac(@RequestBody Dostavljac dostavljac) {
		if(!dostavljacRepository.existsById(dostavljac.getDostavljacid()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		dostavljacRepository.save(dostavljac);
		return new ResponseEntity<>(HttpStatus.OK);

		
	}*/
	@PutMapping("dostavljac/{id}")
	public Dostavljac updateDostavljac(@PathVariable Integer id, @RequestBody Dostavljac dostavljacDetails){
			Dostavljac dostavljac = dostavljacRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Nije pronadjen dostavljac"));
			
			dostavljac.setNazivdostavljaca(dostavljacDetails.getNazivdostavljaca());
			dostavljac.setPrevoznosredstvo(dostavljacDetails.getPrevoznosredstvo());
		
			return dostavljacRepository.save(dostavljac);
	}
	
	
	@DeleteMapping("dostavljac/{id}")
	
	public ResponseEntity<Dostavljac> deleteDostavljac(@PathVariable ("id") Integer id) {
		Dostavljac existingDostavljac = this.dostavljacRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dostavljac ne postoji"));
		this.dostavljacRepository.delete(existingDostavljac);
		return ResponseEntity.ok().build();


	}

	}
	
	

