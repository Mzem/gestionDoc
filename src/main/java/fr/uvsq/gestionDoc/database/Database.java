package fr.uvsq.gestionDoc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;


/**
 * <p>
 * Classe qui gère l'initialisation de la base de données de gestion de documents : création/connection à la base et création des tables.</p>
 * <p>
 * Cette classe utilise le pattern Singleton pour garantir une unique connection à la base de données.</p>
 * <p>
 * Le SGBD utilisé est Derby en mode embedded. L'application JDBC et le moteur Derby sont exécutés en meme temps par le meme processus de la JVM.</p>
 */
public class Database
{
	//Mode embarqué de Derby (par défaut)
	private static String framework = "embedded";	
	private static String url = "jdbc:derby:gestionDocDB;create=true";
	private static String user = "pglp";	
	private static String pass = "uvsq";
	//Objet de connection
	private static Connection cnx = null;
	
	/**
	* Création et initialisation des tables de la base de données
	*/
	private Database()
	{
		Statement s = null;
		try
		{
			//Connection et propriétés
			Properties props = new Properties();
			//Nom utilisateur et mdp optionnels, authentification desactivée par defaut
			props.put("user", user);
			props.put("password", pass);
			//Emplacement fichiers BD
			props = System.getProperties();
			props.setProperty("derby.system.home", "resources");
			
			//Connection, avec création de la BD pour la première connection
			cnx = DriverManager.getConnection(url, props);
			System.out.println("Connection à la base de données \"gestionDocDB\"");
			
			//Si la base de données contient déjà les table on les recrée pas (en capturant l'exception et continuant l'execution)
			s = cnx.createStatement();
			try {
				s.execute("CREATE TABLE Fichier (nom varchar(20) NOT NULL, nomPropre varchar(15), extension varchar(10), taille float, auteur varchar(20), dateAjout varchar(25), type varchar(15), CONSTRAINT ID_FicT PRIMARY KEY (nom))");
				System.out.println("\tTable Fichier créée");
			} catch (SQLException sqle) {
				if (sqle.getErrorCode() == 30000)
					System.out.println("\tTable Fichier déjà créée");
				else
					throw new SQLException();
			}
			//Clé étrangère rajoutée pour referencer l'unique repertoire parent qui contient ce repertoire (NULL si aucun repertoire parent)
			try {
				s.execute("CREATE TABLE Repertoire (nom varchar(20) NOT NULL, nomRepParent varchar(20), auteur varchar(20), dateCreation varchar(25), CONSTRAINT ID_Rep PRIMARY KEY (nom), CONSTRAINT FK_RepParent FOREIGN KEY (nomRepParent) REFERENCES Repertoire(nom))");
				System.out.println("\tTable Repertoire créée");
			} catch (SQLException sqle) {
				if (sqle.getErrorCode() == 30000)
					System.out.println("\tTable Repertoire déjà créée");
				else
					throw new SQLException();
			}
			//Table qui permet d'assurer qu'un fichier peut etre contenu dans plusieurs repertoires (hiérarchies virtuelles)
			try {
				s.execute("CREATE TABLE RepertoireFichier (nomRep varchar(20), nomFic varchar(20), CONSTRAINT ID_RepFic PRIMARY KEY (nomRep, nomFic))");
				System.out.println("\tTable RepertoireFichier créée");
			} catch (SQLException sqle) {
				if (sqle.getErrorCode() == 30000)
					System.out.println("\tTable RepertoireFichier déjà créée");
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
		}
	}

	/**
	* Méthode qui va retourner l'instance de connection à la BD et la créer si elle n'existe pas.
	* @return cnx : l'unique instance de connection à la base de données.
	*/
	public static Connection getInstance() {
		if(cnx == null)
			new Database();
		return cnx;   
	}
	
	/**
	* Méthode qui va fermer la connection à la base de données.
	*/
	public static void closeConnection() {
		try {
			if (cnx != null) {
				cnx.close();
				cnx = null;
				System.out.println(ansi().fgBrightGreen().a("Déconnection...\n").reset());
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
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
