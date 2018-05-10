package fr.uvsq.gestionDoc.database;

import fr.uvsq.gestionDoc.donnees.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

public class FichierTexteDAO extends DAO<FichierTexte> 
{
	public FichierTexteDAO(Connection cnx) 
	{
		super(cnx);
		//System.out.println("FichierTexteDAO créé.");
	}

	public boolean create(FichierTexte obj) {
		System.out.println("Ajout FichierTexte \""+obj.getNom()+"\" à la BD...");
		return false;
	}

	public boolean delete(FichierTexte obj) {
		return false;
	}

	public boolean update(FichierTexte obj) {
		return false;
	}

	public FichierTexte find(int id) 
	{
		return null;
	}
}
