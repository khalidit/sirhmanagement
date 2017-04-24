package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zipcode database table.
 * 
 */
@Entity
@NamedQuery(name="Zipcode.findAll", query="SELECT z FROM Zipcode z")
public class Zipcode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int zipCodeId;

	@Column(name="zipcode")
	private String zip;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="zipcode")
	private List<City> cities;

	public int getZipCodeId() {
		return this.zipCodeId;
	}

	public void setZipCodeId(int zipCodeId) {
		this.zipCodeId = zipCodeId;
	}

	public String getZipcode() {
		return this.zip;
	}

	public void setZipcode(String zipcode) {
		this.zip = zipcode;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setZipcode(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setZipcode(null);

		return city;
	}

}