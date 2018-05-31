package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import java.nio.file.*;
import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * Commande d'ajout de fichiers à la BD.
 */
public class AddCommand implements Command 
{
	private String cheminFichier;
	private static final DAO<Fichier> ficDAO = DAOFactory.getFichierDAO();;
	 
	public AddCommand(String chemin) {
		cheminFichier = chemin;
	}
	
	/**
	 * L'execution d'une commande d'ajout d'un fichier/répertoire se passe en 3 étapes :
	 * - upload du fichier/répertoire à l'INBOX de l'application
	 * - création d'un objet Fichier pour chaque fichier upload dans INBOX, avec extraction de métadonnées
	 * - ajout de chaque objet Fichier à la base de données
	 */
	public void execute() 
	{
		ArrayList<Fichier> fichiers = new ArrayList<Fichier>();
		
		//Upload
		ArrayList<Path> paths = Fichier.upload(cheminFichier);
		//Création d'objets
		for (Path path : paths)
			fichiers.add(FichierFactory.getFichier(path));
		//Ajout à la BD
		for (Fichier fichier : fichiers)
			ficDAO.create(fichier);
			
	}
}

