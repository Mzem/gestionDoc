package fr.uvsq.gestionDoc.database;

import java.sql.Connection;

/**
 * Classe abstraite qui gère l'interaction entre les données et la BD.
 */
public abstract class DAO<T> 
{
	protected Connection cnx = null;
   
	public DAO(Connection cnx) {
		this.cnx = cnx;
	}

	/**
	* Méthode de création
	* @param obj
	* @return boolean 
	*/
	public abstract boolean create(T obj);

	/**
	* Méthode pour effacer
	* @param id
	* @return boolean 
	*/
	public abstract boolean delete(String id);

	/**
	* Méthode de mise à jour
	* @param obj
	* @return boolean
	*/
	public abstract boolean update(T obj);

	/**
	* Méthode de recherche des informations
	* @param id
	* @return T
	*/
	public abstract T find(String id);

}
