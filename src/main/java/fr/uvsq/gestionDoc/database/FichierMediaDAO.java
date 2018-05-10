package fr.uvsq.gestionDoc.database;

import fr.uvsq.gestionDoc.donnees.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

public class FichierMediaDAO extends DAO<FichierMedia> 
{
	public FichierMediaDAO(Connection cnx) 
	{
		super(cnx);
		//System.out.println("FichierMediaDAO créé.");
	}

	public boolean create(FichierMedia obj) {
		return false;
	}

	public boolean delete(FichierMedia obj) {
		return false;
	}

	public boolean update(FichierMedia obj) {
		return false;
	}

	public FichierMedia find(int id) 
	{
		return null;
	}
}
