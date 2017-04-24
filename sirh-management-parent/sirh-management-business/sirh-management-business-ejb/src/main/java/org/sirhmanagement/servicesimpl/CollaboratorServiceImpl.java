/**
 * 
 */
package org.sirhmanagement.servicesimpl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.sirhmanagement.manager.EntityManagerService;
import org.sirhmanagement.model.Adress;
import org.sirhmanagement.model.Collaborator;
import org.sirhmanagement.services.CollaboratorService;

/**
 * EJB Service de gestion des collaborateurs
 * @author Aliane
 *
 */
@Stateless(mappedName="CollaboratorService")
@Local(value=CollaboratorService.class)
public class CollaboratorServiceImpl extends EntityManagerService implements CollaboratorService {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sirhmanagement.services.CrudService#create(java.lang.Object)
	 */
	@Override
	public Collaborator create(Collaborator bean) {
		if(bean == null) {
			return null;
		}
		
		// Si le bean a un id, alors il es déjà en base
		if(bean.getCollaboratorId() != null) {
			return bean;
		}
		entityManager.persist(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sirhmanagement.services.CrudService#update(java.lang.Object)
	 */
	@Override
	public Collaborator update(Collaborator bean) {
		return entityManager.merge(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sirhmanagement.services.CrudService#delete(java.lang.Object)
	 */
	@Override
	public void delete(Collaborator bean) {
		entityManager.remove(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sirhmanagement.services.CrudService#findAll()
	 */
	@Override
	public List<Collaborator> findAll() {
		return entityManager.createNamedQuery(Collaborator.FIND_ALL, Collaborator.class).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sirhmanagement.services.CollaboratorService#findByFirstName(java.lang
	 * .String)
	 */
	@Override
	public Collaborator findByFirstName(String firstname) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sirhmanagement.services.CollaboratorService#findByLastName(java.lang.
	 * String)
	 */
	@Override
	public Collaborator findByLastName(String lastname) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sirhmanagement.services.CollaboratorService#findByEmail(java.lang.
	 * String)
	 */
	@Override
	public Collaborator findByEmail(String email) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sirhmanagement.services.CollaboratorService#findByAdress(org.
	 * sirhmanagement.model.Adress)
	 */
	@Override
	public Collaborator findByAdress(Adress adress) {
		return null;
	}
}
