package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class RmfCommand implements Command 
{
	private String nomRepertoire;
	private String nomFichier;
	private static final RepertoireFichierDAO repFicDAO = DAOFactory.getRepertoireFichierDAO();
	 
	 
	public RmfCommand(String nomF) {
		nomFichier = nomF;
		String[] reps = Repertoire.getActuel().split("/");
		nomRepertoire = reps[reps.length-1];
	}
	
	public void execute() 
	{
		if (repFicDAO.existe(nomRepertoire,nomFichier))
			repFicDAO.delete(nomRepertoire,nomFichier);
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le fichier \""+nomFichier+"\" n'existe pas dans le répertoire actuel.\n").reset());
			
	}
}
