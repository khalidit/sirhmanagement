package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the client_project database table.
 * 
 */
@Entity
@Table(name="client_project")
@NamedQuery(name="ClientProject.findAll", query="SELECT c FROM ClientProject c")
public class ClientProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int clientProjectId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private int salesRevenue;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to Client
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clientId")
	private Client client;

	//bi-directional many-to-many association to Collaborator
	@ManyToMany
	@JoinTable(
		name="collaborator_project"
		, joinColumns={
			@JoinColumn(name="clientProjectId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="collaboratorId")
			}
		)
	private List<Collaborator> collaborators1;

	//bi-directional many-to-one association to CollaboratorProject
	@OneToMany(mappedBy="clientProject")
	private List<CollaboratorProject> collaboratorProjects;

	//bi-directional many-to-many association to Collaborator
	@ManyToMany
	@JoinTable(
		name="collaborator_project"
		, joinColumns={
			@JoinColumn(name="clientProjectId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="collaboratorId")
			}
		)
	private List<Collaborator> collaborators2;

	public int getClientProjectId() {
		return this.clientProjectId;
	}

	public void setClientProjectId(int clientProjectId) {
		this.clientProjectId = clientProjectId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getSalesRevenue() {
		return this.salesRevenue;
	}

	public void setSalesRevenue(int salesRevenue) {
		this.salesRevenue = salesRevenue;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Collaborator> getCollaborators1() {
		return this.collaborators1;
	}

	public void setCollaborators1(List<Collaborator> collaborators1) {
		this.collaborators1 = collaborators1;
	}

	public List<CollaboratorProject> getCollaboratorProjects() {
		return this.collaboratorProjects;
	}

	public void setCollaboratorProjects(List<CollaboratorProject> collaboratorProjects) {
		this.collaboratorProjects = collaboratorProjects;
	}

	public CollaboratorProject addCollaboratorProject(CollaboratorProject collaboratorProject) {
		getCollaboratorProjects().add(collaboratorProject);
		collaboratorProject.setClientProject(this);

		return collaboratorProject;
	}

	public CollaboratorProject removeCollaboratorProject(CollaboratorProject collaboratorProject) {
		getCollaboratorProjects().remove(collaboratorProject);
		collaboratorProject.setClientProject(null);

		return collaboratorProject;
	}

	public List<Collaborator> getCollaborators2() {
		return this.collaborators2;
	}

	public void setCollaborators2(List<Collaborator> collaborators2) {
		this.collaborators2 = collaborators2;
	}

}