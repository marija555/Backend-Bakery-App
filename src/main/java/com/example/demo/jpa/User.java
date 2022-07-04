package com.example.demo.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
//2 11 4
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@Entity
	@Table(name="\"users\"")
	@NamedQuery(name="User.findAll", query="SELECT u FROM Vrsta u")

	public class User implements Serializable {
		private static final long serialVersionUID = 1L;
		@Id
		@SequenceGenerator(name="USER_ID_GENERATOR", sequenceName="SEQUENCE_USERS",  allocationSize=1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ID_GENERATOR")
		@Column(name="\"userid\"")
		private int userid;
		
		@Column(name="\"adresa\"")
		private String adresa;

		@Column(name="\"brkreditnekartice\"")
		private String brkreditnekartice;

		@Column(name="\"ime\"")
		private String ime;

		@Column(name="\"jmbg\"")
		private String jmbg;

		@Column(name="\"prezime\"")
		private String prezime;
		
		@Column(name="\"username\"")
		private String username;	
		
		@Column(name="\"password\"")
		private String password; 		
		
		@JsonIgnore
		@OneToMany(mappedBy ="user")
		private List<Proizvod> proizvod;
		
		@JsonIgnore
		@OneToMany(mappedBy="user")
		private List<PoruceniProizvod> poruceniproizvod;

		@JsonIgnore
		@ManyToMany
		@JoinTable(	
				name = "user_roles", 
					joinColumns = {
							@JoinColumn(name = "user_id")
					},
					inverseJoinColumns = {
							
					@JoinColumn(name = "role_id")
					})
		private Set<Role> roles = new HashSet<>();

		public User() {
		}

		public User(String username, String password,String adresa, String ime, String prezime, String brKreditneKartice, String JMBG) {
			this.username = username;
			this.password = password;
			this.adresa = adresa;
			this.ime = ime;
			this.prezime = prezime;
			this.brkreditnekartice = brKreditneKartice;
			this.jmbg = JMBG;

		
		}
		
		
		public int getId() {
			return this.userid;
		}

		public void setId(int userid) {
			this.userid = userid;
		}

		public String getAdresa() {
			return this.adresa;
		}

		public void setAdresa(String adresa) {
			this.adresa = adresa;
		}

		
		public String getBrkreditnekartice() {
			return this.brkreditnekartice;
		}

		public void setBrkreditnekartice(String brkreditnekartice) {
			this.brkreditnekartice = brkreditnekartice;
		}

		public String getIme() {
			return this.ime;
		}

		public void setIme(String ime) {
			this.ime = ime;
		}

		public String getJmbg() {
			return this.jmbg;
		}

		public void setJmbg(String jmbg) {
			this.jmbg = jmbg;
		}

		public String getPrezime() {
			return this.prezime;
		}

		public void setPrezime(String prezime) {
			this.prezime = prezime;
		}

		public List<PoruceniProizvod> getPoruceniproizvod() {
			return this.poruceniproizvod;
		}

		public void setPoruceniroizvod(List<PoruceniProizvod> poruceniproizvod) {
			this.poruceniproizvod = poruceniproizvod;
		}

		
		public PoruceniProizvod addPoruceniroizvod(PoruceniProizvod poruceniproizvod) {
			getPoruceniproizvod().add(poruceniproizvod);
			poruceniproizvod.setUser(this);

			return poruceniproizvod;
		}

		public PoruceniProizvod removePoruceniroizvod(PoruceniProizvod poruceniproizvod) {
			getPoruceniproizvod().remove(poruceniproizvod);
			poruceniproizvod.setUser(null);

			return poruceniproizvod;
		}
		
		public List<Proizvod> getproizvod() {
			return this.proizvod;
		}

		public void setproizvod(List<Proizvod> proizvod) {
			this.proizvod = proizvod;
		}

		public Proizvod addProizvod(Proizvod proizvod) {
			getproizvod().add(proizvod);
			proizvod.setUser(this);

			return proizvod;
		}

		public Proizvod removeProizvod(Proizvod proizvod) {
			getproizvod().remove(proizvod);
			proizvod.setUser(null);

			return proizvod;
		}
		
		public String getUsername() {
			return this.username;
		}

		public void seUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return this.password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}
	}
