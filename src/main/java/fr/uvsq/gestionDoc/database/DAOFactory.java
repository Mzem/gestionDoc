package fr.uvsq.gestionDoc.database;

import java.sql.Connection;

public class DAOFactory 
{
	protected static final Connection cnx = Database.getInstance();   
   
	/**
	* Retourne un objet FichierTexte interagissant avec la base de données.
	* @return DAO
	*/
	public static DAO getFichierTexteDAO(){
		return new FichierTexteDAO(cnx);
	}
   
	/**
	* Retourne un objet FichierMedia interagissant avec la base de données.
	* @return DAO
	*/
	public static DAO getFichierMediaDAO(){
		return new FichierMediaDAO(cnx);
	}

}
