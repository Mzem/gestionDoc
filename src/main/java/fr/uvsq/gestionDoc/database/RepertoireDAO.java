package fr.uvsq.gestionDoc.database;

import fr.uvsq.gestionDoc.donnees.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class RepertoireDAO extends DAO<Repertoire> 
{
	public RepertoireDAO(Connection cnx) 
	{
		super(cnx);
	}

	public boolean create(Repertoire R) 
	{
		PreparedStatement psInsert = null;
		
		try {
			psInsert = Database.getInstance().prepareStatement("INSERT INTO Repertoire VALUES (?, ?, ?, ?)");
            psInsert.setString(1, R.getNom());
            psInsert.setString(2, R.getNomRepParent());
            psInsert.setString(3, R.getAuteur());
            psInsert.setString(4, R.getDateCreation());
            psInsert.executeUpdate();
			System.out.println("Ajout du repertoire \""+R.getNom()+"\" à la BD...");
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
	
	public boolean update(Repertoire obj) {
		return false;
	}

	public Repertoire find(String id) {
		return null;
	}
	
	public boolean delete(String nomRepertoire) {
			
		PreparedStatement psDelete = null;
		
		try {
			psDelete = Database.getInstance().prepareStatement("DELETE FROM Repertoire WHERE nom = ?");
            psDelete.setString(1, nomRepertoire);
            psDelete.executeUpdate();
			System.out.println("Suppression du repertoire \""+nomRepertoire+"\" de la BD...");
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
	
	public boolean existe(String nomRepertoire)
	{
		Statement sSelect = null;
		ResultSet rs = null;
		
		try {
			sSelect = Database.getInstance().createStatement();
			rs = sSelect.executeQuery("SELECT * FROM Repertoire WHERE nom = '"+nomRepertoire+"'");
			
			if(rs.next())
				return true;
			else
				return false;
		} catch (SQLException sqle) {
            Database.printSQLException(sqle);
            return false;
        } finally {
			//Libération ressources requete
			try {
				if (sSelect != null) {
					sSelect.close();
					sSelect = null;
				}
			} catch (SQLException sqle) {
				Database.printSQLException(sqle);
			}
			// ResultSet
            try {
				if (rs != null) {
                    rs.close();
                    rs = null;
                }
            } catch (SQLException sqle) {
                Database.printSQLException(sqle);
            }
		}
	}
}