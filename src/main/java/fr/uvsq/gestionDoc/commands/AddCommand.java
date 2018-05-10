package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import java.nio.file.*;
import java.util.ArrayList;

public class AddCommand implements Command 
{
	private String cheminFichier;
	private static final DAO<FichierTexte> ficTDAO = DAOFactory.getFichierTexteDAO();
	private static final DAO<FichierMedia> ficMDAO = DAOFactory.getFichierMediaDAO();
	 
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
		for (Fichier fichier : fichiers) {
			if (fichier instanceof FichierTexte)
				ficTDAO.create((FichierTexte)fichier);
			else if (fichier instanceof FichierMedia)
				ficMDAO.create((FichierMedia)fichier);
			else if (fichier instanceof Fichier)
				System.out.println("Fichier \""+fichier.getNom()+" inconnu non ajouté à la BD...");
			else
				System.out.println("\n----- Erreur ----- : problème DAO ajout fichier à la BD.\n");
		}
			
	}
}

