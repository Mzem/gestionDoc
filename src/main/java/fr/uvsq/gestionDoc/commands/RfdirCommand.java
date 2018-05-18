package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class RfdirCommand implements Command 
{
	private String nomRepertoire;
	private String nomFichier;
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
	private static final FichierDAO ficDAO = DAOFactory.getFichierDAO();
	private static final RepertoireFichierDAO repficDAO = DAOFactory.getRepertoireFichierDAO();
	 
	 
	public RfdirCommand(String nomR,String nomF) {
		nomRepertoire = nomR;
		nomFichier = nomF;
	}
	
	public void execute() 
	{
		if (repficDAO.existe(nomRepertoire,nomFichier))
			repficDAO.delete(nomRepertoire,nomFichier);
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le repertoire \""+nomRepertoire+"\" ou le fichier \""+nomFichier+"\" n'existe pas.\n").reset());
			
	}
}
