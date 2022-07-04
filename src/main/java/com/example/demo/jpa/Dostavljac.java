package com.example.demo.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the "dostavljac" database table.
 * 
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="\"dostavljac\"")
@NamedQuery(name="Dostavljac.findAll", query="SELECT d FROM Dostavljac d")
public class Dostavljac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOSTAVLJAC_ID_GENERATOR", sequenceName="SEQUENCE_DOSTAVLJAC",  allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOSTAVLJAC_ID_GENERATOR")
	@Column(name="\"dostavljacid\"")
	private int dostavljacid;

	@Column(name="\"nazivdostavljaca\"")
	private String nazivdostavljaca;

	@Column(name="\"prevoznosredstvo\"")
	private String prevoznosredstvo;

	//bi-directional many-to-one association to Porudzbina
	@JsonIgnore
	@OneToMany(mappedBy="dostavljac")
	private List<Porudzbina> porudzbinas;

	public Dostavljac() {
	}

	public int getDostavljacid() {
		return this.dostavljacid;
	}

	public void setDostavljacid(int dostavljacid) {
		this.dostavljacid = dostavljacid;
	}

	public String getNazivdostavljaca() {
		return this.nazivdostavljaca;
	}

	public void setNazivdostavljaca(String nazivdostavljaca) {
		this.nazivdostavljaca = nazivdostavljaca;
	}

	public String getPrevoznosredstvo() {
		return this.prevoznosredstvo;
	}

	public void setPrevoznosredstvo(String prevoznosredstvo) {
		this.prevoznosredstvo = prevoznosredstvo;
	}

	public List<Porudzbina> getPorudzbinas() {
		return this.porudzbinas;
	}

	public void setPorudzbinas(List<Porudzbina> porudzbinas) {
		this.porudzbinas = porudzbinas;
	}

	public Porudzbina addPorudzbina(Porudzbina porudzbina) {
		getPorudzbinas().add(porudzbina);
		porudzbina.setDostavljac(this);

		return porudzbina;
	}

	public Porudzbina removePorudzbina(Porudzbina porudzbina) {
		getPorudzbinas().remove(porudzbina);
		porudzbina.setDostavljac(null);

		return porudzbina;
	}

}