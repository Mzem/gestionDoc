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
 * Classe représentant les opérations d'interaction entre une donnée "Repertoire" et la BD. 
 */
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
	
	public void show (String cheminActuel) {
		Statement sSelect = null;
		ResultSet rs = null;
		
		String[] reps = cheminActuel.split("/");
		String actuel = reps[reps.length-1];
		
		try {
			sSelect = Database.getInstance().createStatement();
			rs = sSelect.executeQuery("SELECT * FROM REPERTOIRE WHERE nomRepParent = '"+actuel+"'");
			
			System.out.print("\tRépertoires :   ");
			while(rs.next()) {
				System.out.print(ansi().fgMagenta().a(rs.getString(1)).reset().a(" | ")); 
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
	
	public boolean existeFils(String nomRepertoireParent, String nomRepertoireFils)
	{
		Statement sSelect = null;
		ResultSet rs = null;
		
		try {
			sSelect = Database.getInstance().createStatement();
			rs = sSelect.executeQuery("SELECT * FROM Repertoire WHERE nom = '"+nomRepertoireFils+"' AND nomRepParent ='"+nomRepertoireParent+"'");
			
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
	
	public ArrayList<String> findFils (String repertoire) 
	{
		ArrayList<String> repsFils = new ArrayList<String>();
		
		Statement sSelect = null;
		ResultSet rs = null;
		
		try {
			sSelect = Database.getInstance().createStatement();
			rs = sSelect.executeQuery("SELECT * FROM REPERTOIRE WHERE nomRepParent = '"+repertoire+"'");
			
			while(rs.next()) {
				repsFils.add(rs.getString(1)); 
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
		return repsFils;
	}
}
