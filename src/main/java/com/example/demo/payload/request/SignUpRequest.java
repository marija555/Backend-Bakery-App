package com.example.demo.payload.request;
import java.util.Set;

import javax.validation.constraints.*;
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String ime;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String prezime;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String adresa;
 
    @NotBlank
    @Size(min = 3, max = 20)
    private String brkreditnekartice;
 
    @NotBlank
    @Size(min = 3, max = 20)
    private String jmbg;

    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 200)
    private String password;   ////////
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
   /* public int getId() {
		return this.id;
	}

	public void setId(int userid) {
		this.id = userid;
	}*/

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
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
