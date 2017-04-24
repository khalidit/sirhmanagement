/**
 * 
 */
package org.sirhmanagement.services;

import java.util.List;

/**
 * Service CRUD pour toutes les entitées
 * @author Aliane
 *
 */
public interface CrudService<B> {
	
	/**
	 * Create a new bean in database
	 * @param bean
	 * @return
	 */
	B create(B bean);
	
	/**
	 * Update bean in database
	 * @param bean
	 * @return
	 */
	B update(B bean);
	
	/**
	 * Delete bean from database
	 * @param bean
	 * @return
	 */
	void delete(B bean);
	
	/**
	 * Find all beans
	 * @return
	 */
	List<B> findAll();
}
