package fr.uvsq.gestionDoc.database;

import java.sql.Connection;

public class DAOFactory 
{
	protected static final Connection cnx = Database.getInstance();   
   
	/**
	* Retourne un objet Fichier interagissant avec la base de données.
	* @return DAO
	*/
	public static DAO getFichierDAO(){
		return new FichierDAO(cnx);
	}

}
