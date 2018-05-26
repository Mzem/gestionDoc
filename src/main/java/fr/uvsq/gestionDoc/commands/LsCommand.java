package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class LsCommand implements Command 
{
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
	private static final RepertoireFichierDAO repFicDAO = DAOFactory.getRepertoireFichierDAO();

	 
	public LsCommand(){}
	
	public void execute() 
	{
		System.out.println();
		//Affichage de tous les répertoires contenus dans le répertoire actuel
		repDAO.show(Repertoire.getActuel());
		//Affichage de tous les fichiers contenus dans le répertoire actuel
		if (!Repertoire.getActuel().equals("INBOX/"))
			repFicDAO.show(Repertoire.getActuel());
		System.out.println();
	}	
}
