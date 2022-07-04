package com.example.demo.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




/**
 * Entity implementation class for Entity: TipVrsteProizvoda
 *
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="\"proizvod\"")
@NamedQuery(name="Proizvod.findAll", query="SELECT pr FROM Proizvod pr")

public class Proizvod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROIZVOD_ID_GENERATOR", sequenceName="SEQUENCE_PROIZVOD",  allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROIZVOD_ID_GENERATOR")
	@Column(name="\"proizvodid\"")
	private int id;

	@Column(name="\"nazivproizvoda\"")
	private String naziv;
	
	@ManyToOne
	@JoinColumn(name="\"vrstaproizvodaid\"")
	private Vrsta vrsta;
	
	@ManyToOne
	@JoinColumn(name="\"userid\"")
	private User user;
	
	@Column(name="\"kolicinaproizvoda\"")
	private int kolicina;
	
	@Column(name="\"cenaproizvoda\"")
	private int cena;
	
	@Column(name="\"slika\"")
	private String slika;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="proizvodid" )
	private List<PoruceniProizvod> poruceniproizvod;
	
	public Proizvod() {
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Vrsta getVrsta() {
		return this.vrsta;
	}
	public void setVrsta(Vrsta vrsta) {
		this.vrsta = vrsta;
	}
	
	public Integer getCena() {
		return this.cena;
	}

	public void setCena(Integer cena) {
		this.cena = cena;
	}
	public Integer getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User userid) {
		this.user = userid;
	}

	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}
	
	public List<PoruceniProizvod> getPoruceniproizvod() {
		return this.poruceniproizvod;
	}
	

	public void setPoruceniproizvod(List<PoruceniProizvod> poruceniproizvod) {
		this.poruceniproizvod = poruceniproizvod;
	}

	public PoruceniProizvod addPoruceniproizvod(PoruceniProizvod poruceniproizvod) {
		getPoruceniproizvod().add(poruceniproizvod);
		poruceniproizvod.setProizvod(this);
		return poruceniproizvod;
	}

	public PoruceniProizvod removePoruceniproizvod(PoruceniProizvod poruceniproizvod) {
		getPoruceniproizvod().remove(poruceniproizvod);
		poruceniproizvod.setProizvod(null);

		return poruceniproizvod;
	}


	
	
}
