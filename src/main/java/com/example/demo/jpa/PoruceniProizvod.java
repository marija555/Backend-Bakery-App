package com.example.demo.jpa;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * Entity implementation class for Entity: Proizvod
 *
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="\"poruceniproizvod\"")
@NamedQuery(name="PoruceniProizvod.findAll", query="SELECT pro FROM PoruceniProizvod pro")
public class PoruceniProizvod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PORUCENI_PROIZVOD_ID_GENERATOR", sequenceName="SEQUENCE_PORUCENIPROIZVOD",  allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PORUCENI_PROIZVOD_ID_GENERATOR")
	
	@Column(name="\"poruceniproizvodid\"")
	private int poruceniproizvodid;

	@ManyToOne
	@JoinColumn(name="\"proizvodid\"")
	private Proizvod proizvodid;

	@ManyToOne
	@JoinColumn(name="\"porudzbinaid\"")
	private Porudzbina porudzbinaid;
	
	@ManyToOne
	@JoinColumn(name="\"pekaraid\"")
	private Pekara pekaraid;
	
	@ManyToOne
	@JoinColumn(name="\"userid\"")
	private User user;
	
	
	public PoruceniProizvod() {
	}
   
	public Integer getId() {
		return this.poruceniproizvodid;
	}

	public void setId(Integer poruceniproizvodid) {
		this.poruceniproizvodid = poruceniproizvodid;
	}
	
	
	public Proizvod getProizvod() {
		return this.proizvodid;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvodid = proizvod;
	}

	public Porudzbina getPorudzbina() {
		return this.porudzbinaid;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbinaid = porudzbina;
	}
	
	public Pekara getPekara() {
		return this.pekaraid;
	}

	public void setPekara(Pekara pekara) {
		this.pekaraid = pekara;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User userid) {
		this.user = userid;
	}
	
	}
