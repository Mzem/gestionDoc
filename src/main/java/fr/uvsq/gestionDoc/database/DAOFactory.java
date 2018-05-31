package fr.uvsq.gestionDoc.database;

import java.sql.Connection;

/**
 * Classe de fabrique d'instance particulière DAO.
 */
public class DAOFactory 
{
	protected static final Connection cnx = Database.getInstance();   
   
	/**
	* Retourne un objet Fichier interagissant avec la base de données.
	* @return DAO
	*/
	public static FichierDAO getFichierDAO(){
		return new FichierDAO(cnx);
	}
	
	public static RepertoireDAO getRepertoireDAO(){
		return new RepertoireDAO(cnx);
	}

	public static RepertoireFichierDAO getRepertoireFichierDAO(){
		return new RepertoireFichierDAO(cnx);
	}
}
