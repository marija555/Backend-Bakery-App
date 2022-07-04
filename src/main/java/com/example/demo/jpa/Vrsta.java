package com.example.demo.jpa;

import java.io.Serializable;
import javax.persistence.*;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the "vrsta" database table.
 * 
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="\"vrsta\"")
@NamedQuery(name="Vrsta.findAll", query="SELECT v FROM Vrsta v")

public class Vrsta implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="VRSTA_PROIZVODA_ID_GENERATOR", sequenceName="SEQUENCE_VRSTA",  allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VRSTA_PROIZVODA_ID_GENERATOR")
	@Column(name="\"vrstaproizvodaid\"")
	private int vrstaproizvodaid;

	@Column(name="\"nazivvrsteproizvoda\"")
	private String nazivvrsteproizvoda;
	
	//bi-directional many-to-one association to Tipvrsteproizvoda
	@JsonIgnore
	@OneToMany(mappedBy="vrsta" )
	private List<Proizvod> proizvod;

	public Vrsta() {
	}

	public int getVrstaproizvodaid() {
		return this.vrstaproizvodaid;
	}

	public void setVrstaproizvodaid(int vrstaproizvodaid) {
		this.vrstaproizvodaid = vrstaproizvodaid;
	}

	public String getNazivvrsteproizvoda() {
		return this.nazivvrsteproizvoda;
	}

	public void setNazivvrsteproizvoda(String nazivvrsteproizvoda) {
		this.nazivvrsteproizvoda = nazivvrsteproizvoda;
	}

	public List<Proizvod> getProizvod() {
		return this.proizvod;
	}
	

	public void setProizvod(List<Proizvod> proizvod) {
		this.proizvod = proizvod;
	}

	public Proizvod addProizvod(Proizvod proizvod) {
		getProizvod().add(proizvod);
		proizvod.setVrsta(this);

		return proizvod;
	}

	public Proizvod removeProizvod(Proizvod proizvod) {
		getProizvod().remove(proizvod);
		proizvod.setVrsta(null);

		return proizvod;
	}

}