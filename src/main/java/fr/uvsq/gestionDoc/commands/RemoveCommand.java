package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import java.util.ArrayList;

/**
 * Commande de suppression d'un fichier de la BD 
 */
public class RemoveCommand implements Command 
{
	private String nomFichier;
	private static final FichierDAO ficDAO = DAOFactory.getFichierDAO();
	private static final RepertoireFichierDAO repFicDAO = DAOFactory.getRepertoireFichierDAO();
	 
	public RemoveCommand(String nom) {
		nomFichier = nom;
	}
	
	public void execute() 
	{
		if (Fichier.existeINBOX(nomFichier))
			//Efface le fichier de INBOX
			Fichier.deleteINBOX(nomFichier);
		if (ficDAO.existe(nomFichier)) {
			//Efface le fichier de la BD
			ficDAO.delete(nomFichier);
			//On supprime les lien répertoire - fichier s'ils existent
			ArrayList<String> repsParents = repFicDAO.findRepertoiresParents(nomFichier);
			for (String rep : repsParents)
				repFicDAO.delete(rep,nomFichier);
		}
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le fichier \""+nomFichier+"\" n'existe pas.\n").reset());
			
	}
}

