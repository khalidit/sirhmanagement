package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the civility database table.
 * 
 */
@Entity
@NamedQuery(name="Civility.findAll", query="SELECT c FROM Civility c")
public class Civility implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int civilityId;

	private String name;

	//bi-directional many-to-one association to Collaborator
	@OneToMany(mappedBy="civility")
	private List<Collaborator> collaborators;

	public int getCivilityId() {
		return this.civilityId;
	}

	public void setCivilityId(int civilityId) {
		this.civilityId = civilityId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Collaborator> getCollaborators() {
		return this.collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}

	public Collaborator addCollaborator(Collaborator collaborator) {
		getCollaborators().add(collaborator);
		collaborator.setCivility(this);

		return collaborator;
	}

	public Collaborator removeCollaborator(Collaborator collaborator) {
		getCollaborators().remove(collaborator);
		collaborator.setCivility(null);

		return collaborator;
	}

}