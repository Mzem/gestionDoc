package fr.uvsq.gestionDoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;

/**
 * <p>
 * Classe qui gère les fonctionnalités liés à la base de données de gestion de documents : création de la base, des tables et opération d'ajout de documents.</p>
 * <p>
 * Le SGBD utilisé est Derby en mode embedded. L'application JDBC et le moteur Derby sont exécutés en meme temps par le meme processus de la JVM.</p>
 */
public class Database
{
	private String framework = "embedded";	//Mode embarqué de Derby (par défaut)
	
	/**
	* Création et initialisation des tables de la base de données
	*/
	public Database()
	{
		//Ressources qui vont etre libérées
		Connection conn = null;
		Statement s = null;
		try
		{
			//Connection et propriétés
			Properties props = new Properties();
			//Nom utilisateur et mdp optionnels, authentification desactivée par defaut
			props.put("user", "pglp");
			props.put("password", "uvsq");
			//Emplacement BD
			props = System.getProperties();
			props.setProperty("derby.system.home", "resources");
			
			//Connection, avec création de la BD pour la première connection
			conn = DriverManager.getConnection("jdbc:derby:gestionDocDB;create=true", props);
			System.out.println("Connection à la base de données \"gestionDocDB\"");
			
			//Si la base de données contient déjà les table on les recrée pas (en capturant l'exception et continuant l'execution)
			s = conn.createStatement();
			try {
				s.execute("CREATE TABLE fichierTexte (nom varchar(20), extension varchar(5), taille float, dateCreation date, auteur varchar(40), type varchar(20))");
				System.out.println("Table fichierTexte créée");
			} catch (SQLException sqle) {
				if (sqle.getErrorCode() == 30000)
					System.out.println("Table fichierTexte déjà créée");
				else
					throw new SQLException();
			}
			try {
				s.execute("CREATE TABLE fichierMedia (nom varchar(20), extension varchar(5), taille float, dateCreation date, auteur varchar(40))");
				System.out.println("Table fichierMedia créée");
			} catch (SQLException sqle) {
				if (sqle.getErrorCode() == 30000)
					System.out.println("Table fichierMedia déjà créée");
				else
					throw new SQLException();
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		} finally {
			//Libération ressources
			try {	//Requetes
				if (s != null) {
				s.close();
				s = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
			try {	//Connection
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
	}


	/**
	* Affiche les détails de SQLException et le message d'erreur.
	* @param e : SQLException qui contient les détails de l'exception.
	*/
	public static void printSQLException(SQLException e) {
		while (e != null) {
			System.err.println("\n----- SQLException -----");
			System.err.println("  SQL State :  " + e.getSQLState());
			System.err.println("  Code d'erreur : " + e.getErrorCode());
			System.err.println("  Message :    " + e.getMessage());
			e = e.getNextException();
		}
	}
}
