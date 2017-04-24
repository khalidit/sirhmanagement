package org.sirhmanagement.manager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Gestionnaire d'entités
 * @author Aliane
 *
 */
public abstract class EntityManagerService {
	
	public static final String PERSISTENCE_UNIT = "sirhmanagementPU";
	
	@PersistenceContext(unitName = PERSISTENCE_UNIT)
    protected EntityManager entityManager;

}
