package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cityId;

	private String name;

	//bi-directional many-to-one association to Adress
	@OneToMany(mappedBy="city")
	private List<Adress> adresses;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="countryId")
	private Country country;

	//bi-directional many-to-one association to Zipcode
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="zipCodeId")
	private Zipcode zipcode;

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Adress> getAdresses() {
		return this.adresses;
	}

	public void setAdresses(List<Adress> adresses) {
		this.adresses = adresses;
	}

	public Adress addAdress(Adress adress) {
		getAdresses().add(adress);
		adress.setCity(this);

		return adress;
	}

	public Adress removeAdress(Adress adress) {
		getAdresses().remove(adress);
		adress.setCity(null);

		return adress;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Zipcode getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

}