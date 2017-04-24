package org.sirhmanagement.services;

import org.sirhmanagement.model.Adress;
import org.sirhmanagement.model.Collaborator;

public interface CollaboratorService extends CrudService<Collaborator>{
	
	/**
	 * Find collaborator by first name
	 * @param name
	 * @return
	 */
	Collaborator findByFirstName(String firstname);
	
	/**
	 * Find collaborator by last name
	 * @param lastname
	 * @return
	 */
	Collaborator findByLastName(String lastname);
	
	/**
	 * Find collaborator by email
	 * @param email
	 * @return
	 */
	Collaborator findByEmail(String email);
	
	/**
	 * Find collaborator by adress
	 * @param adress
	 * @return
	 */
	Collaborator findByAdress(Adress adress);
}
