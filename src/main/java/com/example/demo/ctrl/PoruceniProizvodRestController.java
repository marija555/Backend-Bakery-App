package com.example.demo.ctrl;

import java.util.Collection;
import java.util.List;

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
import com.example.demo.jpa.PoruceniProizvod;
import com.example.demo.jpa.Porudzbina;
import com.example.demo.jpa.Proizvod;
import com.example.demo.jpa.User;
import com.example.demo.jpa.Vrsta;
import com.example.demo.repository.PekaraRepository;
import com.example.demo.repository.PoruceniProizvodRepository;
import com.example.demo.repository.ProizvodRepository;
import com.example.demo.repository.UserRepository;


@RestController
@CrossOrigin(origins="*")
public class PoruceniProizvodRestController {

	@Autowired
	private PoruceniProizvodRepository poruceniproizvodRepository;
	
	private List<Integer>  ids;

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("poruceniproizvod")
	public Collection <PoruceniProizvod> getPoruceniProizvodi(){
		return poruceniproizvodRepository.findAll();
	}
	
	@GetMapping("poruceniproizvod/{id}")
	public PoruceniProizvod getPoruceniProizvod(@PathVariable("id") Integer id)
	{
		return this.poruceniproizvodRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Poruceni proizvod nije pronadjen"));
	}
	
	 @GetMapping("poruceniproizvodUser/{id}") 
	 public Collection<PoruceniProizvod> getPProizvodByUser(@PathVariable("id") Integer id) {
			User u= userRepository.getOne(id);
			return poruceniproizvodRepository.findByUser(u);
	 
	 }
	 
	 @GetMapping("poruceniproizvodOneUser/{userid}")
	 public PoruceniProizvod getPoruceniProizvodOneUser(@PathVariable("userid") Integer id)
	
	 {
			User u= userRepository.getOne(id);
			return poruceniproizvodRepository.getOneByUser(u);
		}
	 
	 
	@DeleteMapping("poruceniproizvodUser/{userid}")
		public ResponseEntity<PoruceniProizvod> deletePoruceniProizvodByUserID(@PathVariable ("userid") Integer userid) {
	
		 Collection<PoruceniProizvod> pp=getPProizvodByUser(userid);
		 
		 for( PoruceniProizvod pproizvod : pp) {
			Integer id = pproizvod.getId();
			
			 poruceniproizvodRepository.deleteById(id);
		 }
		 return new ResponseEntity<>(HttpStatus.OK);
			
	 }
	 
	/*@DeleteMapping("poruceniproizvodUser/{id}")
	public ResponseEntity<PoruceniProizvod> deletePoruceniProizvodByUser(@PathVariable ("id") Integer id) {
		User u= userRepository.getOne(id);
		if(poruceniproizvodRepository.existsByUser(u))
		{
			poruceniproizvodRepository.deleteByUser(u);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	 return new ResponseEntity<>(HttpStatus.OK);
		*/
		
	 
	@PostMapping("poruceniproizvod")
	public ResponseEntity<PoruceniProizvod> insertPoruceniProizvod(@RequestBody PoruceniProizvod poruceniproizvod) {
		if(!poruceniproizvodRepository.existsById(poruceniproizvod.getId())) {
			poruceniproizvodRepository.save(poruceniproizvod);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("poruceniproizvod") 
	public ResponseEntity<PoruceniProizvod> updatePoruceniProizvod(@RequestBody PoruceniProizvod poruceniproizvod) {
		if(!poruceniproizvodRepository.existsById(poruceniproizvod.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		poruceniproizvodRepository.save(poruceniproizvod);
		return new ResponseEntity<>(HttpStatus.OK);

		
	}
	
	@PutMapping("poruceniproizvodAll") 
	public ResponseEntity<PoruceniProizvod> updatePoruceniProizvodAll(@RequestBody  Collection<PoruceniProizvod> pp) {
		 for( PoruceniProizvod pproizvod : pp) {
			 if(!poruceniproizvodRepository.existsById(pproizvod.getId()))
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				poruceniproizvodRepository.save(pproizvod);
				return new ResponseEntity<>(HttpStatus.OK);

		 }
			 return new ResponseEntity<>(HttpStatus.OK);

	}
	@DeleteMapping("poruceniproizvod/{id}")
	public ResponseEntity<PoruceniProizvod> deletePoruceniProizvod(@PathVariable ("id") Integer id) {
		PoruceniProizvod existingPoruceniProizvod = this.poruceniproizvodRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Poruceni proizvod ne postoji"));
		this.poruceniproizvodRepository.delete(existingPoruceniProizvod);
		return ResponseEntity.ok().build();
	}
	
	/*@DeleteMapping("poruceniproizvod/{list}/{username}")
	public ResponseEntity<Void>deletePoruceniProizvodUser(@PathVariable ("list") <PoruceniProizvod>list, @PathVariable String username) {
		if(poruceniproizvodRepository.existsByUser(username)) {
			poruceniproizvodRepository.deleteAllInBatch(list);
		}
		 return new ResponseEntity<>(HttpStatus.OK);


	}*/
}
