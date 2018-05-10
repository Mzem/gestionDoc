package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import java.nio.file.*;
import java.util.ArrayList;

public class RemoveCommand implements Command 
{
	private String nomFichier;
	private static final DAO<Fichier> ficDAO = DAOFactory.getFichierDAO();
	 
	public RemoveCommand(String nom) {
		nomFichier = nom;
	}
	
	public void execute() 
	{
		if (Fichier.existeINBOX(nomFichier))
		{
			//Efface le fichier de INBOX
			Fichier.deleteINBOX(nomFichier);
			//Efface le fichier de la BD
			ficDAO.delete(nomFichier);
		} else
			System.err.println("\n----- Erreur ----- : le fichier n'existe pas.\n");
			
	}
}

