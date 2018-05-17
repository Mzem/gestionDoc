package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import java.nio.file.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class MkdirCommand implements Command 
{
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private String nomRepertoire;
	private static final DAO<Repertoire> repDAO = DAOFactory.getRepertoireDAO();
	 
	public MkdirCommand(String nom) {
		nomRepertoire = nom;
	}
	
	/**
	 * L'execution d'une commande d'ajout d'un fichier/répertoire se passe en 3 étapes :
	 * - upload du fichier/répertoire à l'INBOX de l'application
	 * - création d'un objet Fichier pour chaque fichier upload dans INBOX, avec extraction de métadonnées
	 * - ajout de chaque objet Fichier à la base de données
	 */
	public void execute() 
	{
			//Auteur
			String auteur = System.getProperty("user.name").toString();
			
			//Date Ajout
			Date date = new Date();
   			String dateAjout = dateFormat.format(date).toString();

			Repertoire rep = new Repertoire(nomRepertoire,null,auteur,dateAjout);//auteur a modifier
		//Ajout à la BD
			repDAO.create(rep);
			
	}
}


