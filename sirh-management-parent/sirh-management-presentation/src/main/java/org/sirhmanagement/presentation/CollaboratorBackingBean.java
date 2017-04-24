package org.sirhmanagement.presentation;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.sirhmanagement.model.Collaborator;
import org.sirhmanagement.services.CollaboratorService;

@ManagedBean(name="collaboratorBean")
public class CollaboratorBackingBean {
	
	@EJB
	private CollaboratorService collaboratorService;
	
	private List<Collaborator> collaborators;
	
	public CollaboratorBackingBean() {
		collaborators = collaboratorService.findAll();
	}

	public final List<Collaborator> getCollaborators() {
		return collaborators;
	}
}
