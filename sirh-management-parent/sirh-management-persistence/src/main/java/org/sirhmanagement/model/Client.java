package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int clientId;

	private String clientName;

	private String clientSiret;

	//bi-directional many-to-one association to Adress
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="adressId")
	private Adress adress;

	//bi-directional many-to-one association to ClientProject
	@OneToMany(mappedBy="client")
	private List<ClientProject> clientProjects;

	public int getClientId() {
		return this.clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientSiret() {
		return this.clientSiret;
	}

	public void setClientSiret(String clientSiret) {
		this.clientSiret = clientSiret;
	}

	public Adress getAdress() {
		return this.adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public List<ClientProject> getClientProjects() {
		return this.clientProjects;
	}

	public void setClientProjects(List<ClientProject> clientProjects) {
		this.clientProjects = clientProjects;
	}

	public ClientProject addClientProject(ClientProject clientProject) {
		getClientProjects().add(clientProject);
		clientProject.setClient(this);

		return clientProject;
	}

	public ClientProject removeClientProject(ClientProject clientProject) {
		getClientProjects().remove(clientProject);
		clientProject.setClient(null);

		return clientProject;
	}

}