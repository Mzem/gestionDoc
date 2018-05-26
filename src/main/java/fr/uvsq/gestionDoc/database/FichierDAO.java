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

	public Fichier find(String id) {
		return null;
	}

	public void show(String extension, String auteur, String dateAjout, String type, String rep) 
	{
		//Préparation de la requete selon le nombre d'arguments à considérer
		StringBuilder sString = new StringBuilder("SELECT * FROM Fichier");
		
		if (rep != null && !rep.equals("INBOX")) {
			sString.append(" JOIN RepertoireFichier ON nom = nomFic WHERE nomRep ='"+rep+"'");
		}
		
		if (extension != null) {
			if (sString.length() > 25)
				sString.append(" AND extension='"+extension+"'");
			else
				sString.append(" WHERE extension='"+extension+"'");
		}
		if (auteur != null) {
			if (sString.length() > 25)
				sString.append(" AND auteur='"+auteur+"'");
			else
				sString.append(" WHERE auteur='"+auteur+"'");
		}
		if (dateAjout != null) {
			if (sString.length() > 25)
				sString.append(" AND dateAjout='"+dateAjout+"'");
			else
				sString.append(" WHERE dateAjout='"+dateAjout+"'");
		}
		if (type != null) {
			if (sString.length() > 25)
				sString.append(" AND type='"+type+"'");
			else
				sString.append(" WHERE type='"+type+"'");
		}
		sString.append(" ORDER BY nom ASC");
		
		Statement sSelect = null;
		ResultSet rs = null;
		
		try {
			sSelect = Database.getInstance().createStatement();
			rs = sSelect.executeQuery(sString.toString());
			
			System.out.println(ansi().fgBrightCyan().a("\t"+String.format("%20s", "Fichier")+" : "+String.format("%10s", "Nom")+" | "+String.format("%10s", "Extension")+" | "+String.format("%12s", "Taille (ko)")+" | "+String.format("%15s", "Auteur")+" | "+String.format("%14s", "Date d'ajout")+" | "+String.format("%15s", "Type")).reset());
			while(rs.next()) {
				System.out.println("\t"+String.format("%20s", rs.getString(1))+" : "+String.format("%10s", rs.getString(2))+" | "+String.format("%10s", rs.getString(3))+" | "+String.format("%12s", rs.getDouble(4))+" | "+String.format("%15s", rs.getString(5))+" | "+String.format("%14s", rs.getString(6))+" | "+String.format("%15s", rs.getString(7))); 
			}
		} catch (SQLException sqle) {
            Database.printSQLException(sqle);
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
	
	public boolean existe(String nomFichier)
	{
		Statement sSelect = null;
		ResultSet rs = null;
		
		try {
			sSelect = Database.getInstance().createStatement();
			rs = sSelect.executeQuery("SELECT * FROM FICHIER WHERE nom = '"+nomFichier+"'");
			
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
