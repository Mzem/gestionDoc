package fr.uvsq.gestionDoc.database;

import fr.uvsq.gestionDoc.donnees.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

public class FichierDAO extends DAO<Fichier> 
{
	public FichierDAO(Connection cnx) 
	{
		super(cnx);
	}

	public boolean create(Fichier obj) 
	{/*
		PreparedStatement psInsert = null;
		
		try {
			psInsert = Database.getInstance().prepareStatement("INSERT INTO FichierTexte VALUES (?, ?, ?, ?, ?, ?, ?)");
            psInsert.setInt(1, 45);
            psInsert.setString(2, "Avenue états unis");
            psInsert.executeUpdate();
			System.out.println("Ajout FichierTexte \""+obj.getNom()+"\" à la BD...");
		} catch (SQLException sqle) {
            printSQLException(sqle);
        } finally {
			//Libération ressources
			try {	//Requetes
				if (psInsert != null) {
					psInsert.close();
					psInsert = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}	
		}
		*/
		return false;
	}

	public boolean delete(String nomFichier) {
		System.out.println("Suppression du fichier \""+nomFichier+"\" de la BD...");
		return false;
	}

	public boolean update(Fichier obj) {
		return false;
	}

	public Fichier find(int id) 
	{
		return null;
	}
}
