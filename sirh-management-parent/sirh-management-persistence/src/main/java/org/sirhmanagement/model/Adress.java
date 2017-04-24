package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adress database table.
 * 
 */
@Entity
@NamedQuery(name="Adress.findAll", query="SELECT a FROM Adress a")
public class Adress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int adressId;

	private String street;

	//bi-directional many-to-one association to City
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cityId")
	private City city;

	//bi-directional many-to-one association to Client
	@OneToMany(mappedBy="adress")
	private List<Client> clients;

	//bi-directional many-to-one association to Collaborator
	@OneToMany(mappedBy="adress")
	private List<Collaborator> collaborators;

	public Adress() {
	}

	public int getAdressId() {
		return this.adressId;
	}

	public void setAdressId(int adressId) {
		this.adressId = adressId;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Client> getClients() {
		return this.clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Client addClient(Client client) {
		getClients().add(client);
		client.setAdress(this);

		return client;
	}

	public Client removeClient(Client client) {
		getClients().remove(client);
		client.setAdress(null);

		return client;
	}

	public List<Collaborator> getCollaborators() {
		return this.collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}

	public Collaborator addCollaborator(Collaborator collaborator) {
		getCollaborators().add(collaborator);
		collaborator.setAdress(this);

		return collaborator;
	}

	public Collaborator removeCollaborator(Collaborator collaborator) {
		getCollaborators().remove(collaborator);
		collaborator.setAdress(null);

		return collaborator;
	}

}