package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class AddfCommand implements Command 
{
	private String nomRepertoire;
	private String nomFichier;
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
	private static final FichierDAO ficDAO = DAOFactory.getFichierDAO();
	private static final RepertoireFichierDAO repFicDAO = DAOFactory.getRepertoireFichierDAO();
	 
	public AddfCommand(String nomF) {
		nomFichier = nomF;
		String[] reps = Repertoire.getActuel().split("/");
		nomRepertoire = reps[reps.length-1];
	}
	
	public void execute() 
	{
		if (nomRepertoire.equals("INBOX"))
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : impossible d'ajouter un fichier au répertoire racine INBOX.\n").reset());
		else if (ficDAO.existe(nomFichier)) {
			//Si le fichier qu'on veut rajouter n'existe pas déja dans un sous-répertoire du répertoire actuel
			if (!repFicDAO.existeDansSousRep(nomRepertoire, nomFichier)) {
				RepertoireFichier RF = new RepertoireFichier(nomRepertoire,nomFichier);
				repFicDAO.create(RF);
			} else
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le fichier \""+nomFichier+"\" existe déjà dans un sous-répertoire du répertoire actuel.\n").reset());
		} else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le fichier \""+nomFichier+"\" n'existe pas dans la base de données.\n").reset());	
	}
}
