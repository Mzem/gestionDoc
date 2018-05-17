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

public class RepertoireFichierDAO extends DAO<RepertoireFichier> 
{
	public RepertoireFichierDAO(Connection cnx) 
	{
		super(cnx);
	}

	public boolean create(RepertoireFichier RF) 
	{
		PreparedStatement psInsert = null;
		
		try {
			psInsert = Database.getInstance().prepareStatement("INSERT INTO RepertoireFichier VALUES (?, ?)");
            psInsert.setString(1, RF.getNomRepertoire());
            psInsert.setString(2, RF.getNomFichier());
            psInsert.executeUpdate();
			System.out.println("Ajout du fichier \""+RF.getNomFichier()+"\" dans le repertoire \""+RF.getNomRepertoire()+"\" à la BD...");
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

	public boolean delete(String nomRepertoireFichier) 
	{	
		return true;
	}

	public boolean update(RepertoireFichier obj) {
		return false;
	}

	public RepertoireFichier find(String id) {
		return null;
	}

	public boolean existe(String nomRepertoireFichier){
			return true;
	}
}
