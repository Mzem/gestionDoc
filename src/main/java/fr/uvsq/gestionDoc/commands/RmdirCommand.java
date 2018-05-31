package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * Commande de suppression d'un répertoire de la BD.
 */
public class RmdirCommand implements Command 
{
	private String nomRepertoire;
	private String nomRepertoireParent;
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
	private static final RepertoireFichierDAO repFicDAO = DAOFactory.getRepertoireFichierDAO();
	 
	public RmdirCommand(String nom) {
		nomRepertoire = nom;
		String[] reps = Repertoire.getActuel().split("/");
		nomRepertoireParent = reps[reps.length-1];
	}
	
	public void execute() {
		delete(nomRepertoireParent, nomRepertoire);
	}
	
	public void delete(String nomRepertoireParent, String nomRepertoire) 
	{
		if (repDAO.existeFils(nomRepertoireParent,nomRepertoire)) {
			//On effecture d'abord la suppression sur les sous-répertoire (le plus bas)
			ArrayList<String> repsFils = repDAO.findFils(nomRepertoire);
			for (String repFils : repsFils)
				delete(nomRepertoire, repFils);
			//On efface les fichiers contenus dans ce répertoire (seulement le lien)
			ArrayList<String> fichiersFils = repFicDAO.findFichiersFils(nomRepertoire);
			for (String fichierFils : fichiersFils)
				repFicDAO.delete(nomRepertoire, fichierFils);
			//On efface le répertoire lui-meme
			repDAO.delete(nomRepertoire);
		}
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le repertoire \""+nomRepertoire+"\" n'existe pas dans le répertoire courant.\n").reset());
	}
}
