package com.example.demo.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the "pekara" database table.
 * 
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="\"pekara\"")
@NamedQuery(name="Pekara.findAll", query="SELECT pe FROM Pekara pe")
public class Pekara implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PEKARA_ID_GENERATOR", sequenceName="SEQUENCE_PEKARA",  allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PEKARA_ID_GENERATOR")
	@Column(name="\"pekaraid\"")
	private int pekaraid;

	@Column(name="\"grad\"")
	private String grad;

	@Column(name="\"kontaktpekare\"")
	private String kontaktpekare;

	@Column(name="\"nazivpekare\"")
	private String nazivpekare;

	@Column(name="\"ulica\"")
	private String ulica;
	
	@JsonIgnore
	@OneToMany(mappedBy="pekaraid")
	private List<PoruceniProizvod> poruceniproizvods;

	//bi-directional many-to-one association to PoruceniProizvod
	
	public Pekara() {
	}

	public int getPekaraid() {
		return this.pekaraid;
	}

	public void setPekaraid(int pekaraid) {
		this.pekaraid = pekaraid;
	}

	public String getGrad() {
		return this.grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getKontaktpekare() {
		return this.kontaktpekare;
	}

	public void setKontaktpekare(String kontaktpekare) {
		this.kontaktpekare = kontaktpekare;
	}

	public String getNazivpekare() {
		return this.nazivpekare;
	}

	public void setNazivpekare(String nazivpekare) {
		this.nazivpekare = nazivpekare;
	}

	public String getUlica() {
		return this.ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public List<PoruceniProizvod> getporuceniproizvods() {
		return this.poruceniproizvods;
	}

	public void setporuceniproizvods(List<PoruceniProizvod> poruceniproizvods) {
		this.poruceniproizvods = poruceniproizvods;
	}

	public PoruceniProizvod addPPoruceniProizvod(PoruceniProizvod poruceniproizvods) {
		getporuceniproizvods().add(poruceniproizvods);
		poruceniproizvods.setPekara(this);

		return poruceniproizvods;
	}

	public PoruceniProizvod removePPoruceniProizvod(PoruceniProizvod poruceniproizvods) {
		getporuceniproizvods().remove(poruceniproizvods);
		poruceniproizvods.setPekara(null);

		return poruceniproizvods;
	}



}