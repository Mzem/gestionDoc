package fr.uvsq.gestionDoc.database;

import fr.uvsq.gestionDoc.donnees.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;
import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;


/**
 * Classe représentant les opérations d'interaction entre une donnée "RepertoireFichier" et la BD. 
 */
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
		return false;
	}
	
	public boolean delete(String nomRepertoire, String nomFichier) 
	{	
		PreparedStatement psDelete = null;
		
		try {
			psDelete = Database.getInstance().prepareStatement("DELETE FROM RepertoireFichier WHERE nomRep = ? and nomFic = ?");
            psDelete.setString(1, nomRepertoire);
            psDelete.setString(2, nomFichier);
            psDelete.executeUpdate();
			System.out.println("Suppression du fichier \""+nomFichier+"\" du repertoire \""+nomRepertoire+"\"...");
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

	public boolean update(RepertoireFichier obj) {
		return false;
	}
	
	public RepertoireFichier find(String id) {
		return null;
	}

	public void show (String cheminActuel) {
		Statement sSelect = null;
		ResultSet rs = null;
		
		String[] reps = cheminActuel.split("/");
		String actuel = reps[reps.length-1];
		
		try {
			sSelect = Database.getInstance().createStatement();
			rs = sSelect.executeQuery("SELECT * FROM REPERTOIREFICHIER WHERE nomRep = '"+actuel+"'");
			
			System.out.print("\tFichiers :   ");
			while(rs.next()) {
				System.out.print(ansi().fgCyan().a(rs.getString(2)).reset().a(" | ")); 
			}
			System.out.println();
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

	public boolean existe(String nomRepertoire, String nomFichier)
	{
		Statement sSelect = null;
		ResultSet rs = null;
		
		try {
			sSelect = Database.getInstance().createStatement();
			rs = sSelect.executeQuery("SELECT * FROM REPERTOIREFICHIER WHERE nomRep = '"+nomRepertoire+"'and nomFic = '"+nomFichier+"'");
			
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
	
	public boolean existeDansSousRep(String nomRepertoire, String nomFichier) 
	{
		if (this.existe(nomRepertoire, nomFichier))
			return true;
		
		RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
		ArrayList<String> repsFils = repDAO.findFils(nomRepertoire);
		for (String repFils : repsFils)
			return existeDansSousRep(repFils, nomFichier);
		
		return false;
	}

	public ArrayList<String> findFichiersFils (String repertoire)
	{
		ArrayList<String> fichiersFils = new ArrayList<String>();
		
		Statement sSelect = null;
		ResultSet rs = null;
		
		try {
			sSelect = Database.getInstance().createStatement();
			rs = sSelect.executeQuery("SELECT * FROM REPERTOIREFICHIER WHERE nomRep = '"+repertoire+"'");
			
			while(rs.next()) {
				fichiersFils.add(rs.getString(2)); 
			}
		} catch (SQLException sqle) {
            Database.printSQLException(sqle);
            return null;
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
		return fichiersFils;
	}
	
	public ArrayList<String> findRepertoiresParents (String fichier)
	{
		ArrayList<String> repsParents = new ArrayList<String>();
		
		Statement sSelect = null;
		ResultSet rs = null;
		
		try {
			sSelect = Database.getInstance().createStatement();
			rs = sSelect.executeQuery("SELECT * FROM REPERTOIREFICHIER WHERE nomFic = '"+fichier+"'");
			
			while(rs.next()) {
				repsParents.add(rs.getString(1)); 
			}
		} catch (SQLException sqle) {
            Database.printSQLException(sqle);
            return null;
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
		return repsParents;
	}
}
