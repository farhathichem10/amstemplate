package tn.sip.ams2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = "Name is mandatory")
	@Column(name = "name")
	//@Size(min = 3, max=6)
	private String name;
	@NotBlank(message = "Address is mandatory")
	@Column(name = "address")
	private String address;
	@NotBlank(message = "Email is mandatory")
	@Column(name = "email")
	private String email;
	
	@Column(name = "logo")
	private String logo;
	public Provider() {
		super();
	}
	
	public Provider(long id, @NotBlank(message = "Name is mandatory") String name,
			@NotBlank(message = "Address is mandatory") String address,
			@NotBlank(message = "Email is mandatory") String email,
			@NotBlank(message = "Address is mandatory") String logo) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.logo = logo;
	}
	

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	

}
