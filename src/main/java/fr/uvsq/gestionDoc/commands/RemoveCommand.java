package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class RemoveCommand implements Command 
{
	private String nomFichier;
	private static final FichierDAO ficDAO = DAOFactory.getFichierDAO();
	 
	public RemoveCommand(String nom) {
		nomFichier = nom;
	}
	
	public void execute() 
	{
		if (Fichier.existeINBOX(nomFichier))
			//Efface le fichier de INBOX
			Fichier.deleteINBOX(nomFichier);
		if (ficDAO.existe(nomFichier))
			//Efface le fichier de la BD
			ficDAO.delete(nomFichier);
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le fichier \""+nomFichier+"\" n'existe pas.\n").reset());
			
	}
}

