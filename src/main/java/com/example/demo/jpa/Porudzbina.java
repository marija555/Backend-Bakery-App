package com.example.demo.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * Entity implementation class for Entity: Porudzbina
 *
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name="\"porudzbina\"")
@NamedQuery(name="Porudzbina.findAll", query="SELECT po FROM Porudzbina po")
public class Porudzbina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PORUDZBINA_ID_GENERATOR", sequenceName="SEQUENCE_PORUDZBINA",  allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PORUDZBINA_ID_GENERATOR")
	
	@Column(name="\"porudzbinaid\"")
	private int porudzbinaid;

	@Column(name="\"iznosporudzbine\"")
	private int iznosporudzbine;

	@ManyToOne
	@JoinColumn(name="\"dostavljacid\"")
	private Dostavljac dostavljac;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name= "\"datum\"")
	private Date datum;
	
	@Column(name= "\"nacinplacanja\"")
	private String nacinplacanja;
	
	@JsonIgnore
	@OneToMany(mappedBy="porudzbinaid" )
	private List<PoruceniProizvod> poruceniproizvod;

	
	public Porudzbina() {
		
	}
	
	public Integer getId() {
		return this.porudzbinaid;
	}
	public void setId(Integer id) {
		this.porudzbinaid = id;
	}
	
	public void setIznos(Integer iznos) {
		this.iznosporudzbine = iznos;
	}

	public Integer getIznos() {
		return this.iznosporudzbine;
	}
	public Dostavljac getDostavljac() {
		return this.dostavljac;
	}

	public void setDostavljac(Dostavljac dostavljac) {
		this.dostavljac = dostavljac;
	}
	
	
	public Date getDatum() {
		return this.datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	public String getnacinPlacanja() {
		return this.nacinplacanja;
	}

	public void setNacinPlacanja(String nacinplacanja) {
		this.nacinplacanja = nacinplacanja;
	}
	
	public List<PoruceniProizvod> getPoruceniproizvod() {
		return this.poruceniproizvod;
	}
	

	public void setPoruceniproizvod(List<PoruceniProizvod> poruceniproizvod) {
		this.poruceniproizvod = poruceniproizvod;
	}

	public PoruceniProizvod addPoruceniproizvod(PoruceniProizvod poruceniproizvod) {
		getPoruceniproizvod().add(poruceniproizvod);
		poruceniproizvod.setPorudzbina(this);
		return poruceniproizvod;
	}

	public PoruceniProizvod removePoruceniproizvod(PoruceniProizvod poruceniproizvod) {
		getPoruceniproizvod().remove(poruceniproizvod);
		poruceniproizvod.setPorudzbina(null);

		return poruceniproizvod;
	}



}
