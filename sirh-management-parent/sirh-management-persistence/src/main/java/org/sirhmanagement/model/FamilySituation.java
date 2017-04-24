package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the family_situation database table.
 * 
 */
@Entity
@Table(name="family_situation")
@NamedQuery(name="FamilySituation.findAll", query="SELECT f FROM FamilySituation f")
public class FamilySituation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int familySituationId;

	private String situation;

	//bi-directional many-to-one association to Collaborator
	@OneToMany(mappedBy="familySituation")
	private List<Collaborator> collaborators;

	public int getFamilySituationId() {
		return this.familySituationId;
	}

	public void setFamilySituationId(int familySituationId) {
		this.familySituationId = familySituationId;
	}

	public String getSituation() {
		return this.situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public List<Collaborator> getCollaborators() {
		return this.collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}

	public Collaborator addCollaborator(Collaborator collaborator) {
		getCollaborators().add(collaborator);
		collaborator.setFamilySituation(this);

		return collaborator;
	}

	public Collaborator removeCollaborator(Collaborator collaborator) {
		getCollaborators().remove(collaborator);
		collaborator.setFamilySituation(null);

		return collaborator;
	}

}