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

	public boolean create(Fichier F) 
	{
		PreparedStatement psInsert = null;
		
		try {
			psInsert = Database.getInstance().prepareStatement("INSERT INTO Fichier VALUES (?, ?, ?, ?, ?, ?, ?)");
            psInsert.setString(1, F.getNom());
            psInsert.setString(2, F.getNomPropre());
            psInsert.setString(3, F.getExtension());
            psInsert.setDouble(4, F.getTaille());
            psInsert.setString(5, F.getAuteur());
            psInsert.setString(6, F.getDateAjout());
            psInsert.setString(7, F.getType());
            psInsert.executeUpdate();
			System.out.println("Ajout du fichier \""+F.getNom()+"\" à la BD...");
		} catch (SQLException sqle) {
            Database.printSQLException(sqle);
            return false;
        } finally {
			//Libération ressources
			try {	//Requetes
				if (psInsert != null) {
					psInsert.close();
					psInsert = null;
				}
			} catch (SQLException sqle) {
				Database.printSQLException(sqle);
				return false;
			}	
		}
		return true;
	}

	public boolean delete(String nomFichier) 
	{	
		PreparedStatement psDelete = null;
		
		try {
			psDelete = Database.getInstance().prepareStatement("DELETE FROM Fichier WHERE nom = ?");
            psDelete.setString(1, nomFichier);
            psDelete.executeUpdate();
			System.out.println("Suppression du fichier \""+nomFichier+"\" de la BD...");
		} catch (SQLException sqle) {
            Database.printSQLException(sqle);
            return false;
        } finally {
			//Libération ressources
			try {	//Requetes
				if (psDelete != null) {
					psDelete.close();
					psDelete = null;
				}
			} catch (SQLException sqle) {
				Database.printSQLException(sqle);
				return false;
			}	
		}
		return true;
	}

	public boolean update(Fichier obj) {
		return false;
	}

	public Fichier find(String nomFichier) 
	{
		return null;
	}
}
