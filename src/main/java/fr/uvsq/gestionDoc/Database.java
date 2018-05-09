package fr.uvsq.gestionDoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;

import java.io.*;
import java.nio.file.*;

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
				s.execute("CREATE TABLE fichierTexte (nom varchar(20), extension varchar(5), taille float, dateAjout date, auteur varchar(40), type varchar(20))");
				System.out.println("Table fichierTexte créée");
			} catch (SQLException sqle) {
				if (sqle.getErrorCode() == 30000)
					System.out.println("Table fichierTexte déjà créée");
				else
					throw new SQLException();
			}
			try {
				s.execute("CREATE TABLE fichierMedia (nom varchar(20), extension varchar(5), taille float, dateAjout date, auteur varchar(40))");
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
	* Upload d'un fichier ou d'un répertoire dans INBOX et ajout à la base de données.
	* @param nomFichier : chemin (absolu ou relatif) du fichier/répertoire à ajouter.
	*/
	public static void ajouter(String cheminFichier)
	{
		//On essaye d'ouvrir ce chemin
		Path path = Paths.get(cheminFichier);
		//Si il n'existe pas on quitte
		if (!Files.exists(path)) {
			System.err.println("\n----- Erreur ----- : le chemin \""+ cheminFichier +"\" ne corréspond à aucun fichier ou répertoire.\n");
			return;
		}
		//Si c'est un répertoire, on ajoute récursivement tous ces sous-fichiers, sinon on l'ajoute directement
		if (Files.isDirectory(path))	
			ajouterRep(path);
		else
			ajouterFichier(path);
	}
	
	/**
	* Upload du fichier dans INBOX et ajout à la base de données.
	* @param cheminFichier : chemin (absolu ou relatif) du fichier à upload.
	*/
	private static void ajouterFichier(Path path) 
	{
		//On copie le fichier dans INBOX
		System.out.println("Upload du fichier \""+path.getFileName()+"\" dans INBOX...");
		Path pathINBOX = Paths.get("resources/INBOX/"+path.getFileName());
		try {
			Files.copy(path, pathINBOX);
			//On ajoute le fichier et ses métadonnées à la BD
			ajoutFichierDB(path);
		} catch (IOException e) {
			FileAlreadyExistsException e1 = new FileAlreadyExistsException(path.getFileName().toString());
			if (e.getClass() == e1.getClass())
				System.err.println("\n----- Erreur ----- : le fichier \""+ path.getFileName() +"\" existe déjà dans INBOX.\n");
			else
				e.printStackTrace();
		}
		
	}
	
	/**
	* Upload récursif des sous-fichiers du répertoire dans INBOX et ajout à la base de données.
	* @param cheminFichier : chemin (absolu ou relatif) du répertoire à upload.
	*/
	private static void ajouterRep(Path path)
	{
		try ( DirectoryStream<Path> sousPaths = Files.newDirectoryStream(path) ) 
		{
			for(Path sousPath : sousPaths)
			{
				if (Files.isDirectory(sousPath))
					ajouterRep(sousPath);
				else
					ajouterFichier(sousPath);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Ajout d'un fichier à la base de données et extraction des métadonnées.
	*/
	private static void ajoutFichierDB(Path path)	
	{	/*
		//On lit ce fichier depuis INBOX
		StringBuilder pathINBOX = new StringBuilder("resources/INBOX/");
		pathINBOX.append(nomFichier);
		Path path = Paths.get(pathINBOX.toString());
		
		//Extraction metadonnées, si le fichier est bien présent dans INBOX
		if (Files.exists(path)) 
		{
			try{
				//Nom et extension
				String nomComplet = path.getName(2).toString();
				String[] nomSplit = nomComplet.split("\\.");
				StringBuilder nomPropre = new StringBuilder(nomSplit[0]);
				int i = 1;
				for (i = 1; i < (nomSplit.length - 1) ; i++) {
					nomPropre.append(".");
					nomPropre.append(nomSplit[i]);
				}
				String extension = nomSplit[i];
				
				//Taille en octets
				double taille = Files.size(path);
				
				System.out.println("Ajout du fichier \"" + path.getName(2) + "\" de taille + "+ taille);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			System.err.println("\n----- Erreur ----- fichier "+ nomFichier +" non trouvé dans INBOX");
		*/
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
