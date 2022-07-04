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
import com.example.demo.jpa.Porudzbina;
import com.example.demo.repository.PorudzbinaRepository;

@RestController
@CrossOrigin(origins="*")
public class PorudzbinaRestController {


	@Autowired
	private PorudzbinaRepository porudzbinaRepository;
	
	@GetMapping("porudzbina")
	public Collection <Porudzbina> getPorudzbine(){
		return porudzbinaRepository.findAll();
	}
	
	@GetMapping("porudzbina/{id}")
	public Porudzbina getPorudzbinaById(@PathVariable("id") Integer id)
	{
		return this.porudzbinaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Porudzbina nije pronadjena"));
	}
	
	@GetMapping("nacinPlacanja/{naziv}")
	public Collection<Porudzbina> getPorudzbinaByNacinPlacanja(@PathVariable("naziv") String naziv){
			return porudzbinaRepository.findByNacinplacanjaContainingIgnoreCase(naziv);
	}
	
	@PostMapping("porudzbina")
	public ResponseEntity<Porudzbina> insertPorudzbina(@RequestBody Porudzbina porudzbina) {
		if(!porudzbinaRepository.existsById(porudzbina.getId())) {
			porudzbinaRepository.save(porudzbina);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/*@PutMapping("porudzbina")
	public ResponseEntity<Porudzbina> updatePorudzbina(@RequestBody Porudzbina porudzbina) {
		if(!porudzbinaRepository.existsById(porudzbina.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		porudzbinaRepository.save(porudzbina);
		return new ResponseEntity<>(HttpStatus.OK);

		
	}*/
	
	/*@PutMapping("porudzbina/{id}")
	public ResponseEntity<Porudzbina> updatePorudzbina(@PathVariable Integer id, @RequestBody Porudzbina porudzbinaDetails){
			Porudzbina porudzbina = porudzbinaRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Nije pronadjena porudzbina"));
			
			porudzbina.setIznos(porudzbinaDetails.getIznos());
			porudzbina.setDostavljac(porudzbinaDetails.getDostavljac());
			porudzbina.setDatum(porudzbinaDetails.getDatum());
			porudzbina.setNacinPlacanja(porudzbinaDetails.getnacinPlacanja());
			Porudzbina updatedPorudzbina = porudzbinaRepository.save(porudzbina);
			return ResponseEntity.ok(updatedPorudzbina);
	}*/
	@PutMapping("porudzbina/{id}")
	public Porudzbina updatePorudzbina(@PathVariable Integer id, @RequestBody Porudzbina porudzbinaDetails){
			Porudzbina existingPorudzbina = this.porudzbinaRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Nije pronadjena porudzbina"));
			
			existingPorudzbina.setIznos(porudzbinaDetails.getIznos());
			existingPorudzbina.setDostavljac(porudzbinaDetails.getDostavljac());
			existingPorudzbina.setDatum(porudzbinaDetails.getDatum());
			existingPorudzbina.setNacinPlacanja(porudzbinaDetails.getnacinPlacanja());
			return this.porudzbinaRepository.save(existingPorudzbina);
	}
	
	@DeleteMapping("porudzbina/{id}")
	public ResponseEntity<Porudzbina> deletePorudzbina(@PathVariable ("id") Integer id) {
		Porudzbina existingPorudzbina = this.porudzbinaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Porudzbina ne postoji"));
		this.porudzbinaRepository.delete(existingPorudzbina);
		return ResponseEntity.ok().build();


	}
	
	
}
