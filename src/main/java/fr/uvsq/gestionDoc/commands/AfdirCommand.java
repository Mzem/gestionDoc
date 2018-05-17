package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class AfdirCommand implements Command 
{
	private String nomRepertoire;
	private String nomFichier;
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
	private static final FichierDAO ficDAO = DAOFactory.getFichierDAO();
	private static final RepertoireFichierDAO repficDAO = DAOFactory.getRepertoireFichierDAO();
	 
	public AfdirCommand(String nomF,String nomR) {
		nomFichier = nomF;
		nomRepertoire = nomR;
	}
	
	public void execute() 
	{
		
		if ((repDAO.existe(nomRepertoire)) && (ficDAO.existe(nomFichier))){
			RepertoireFichier RF = new RepertoireFichier(nomRepertoire,nomFichier);
			repficDAO.create(RF);
		}
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le repertoire \""+nomRepertoire+"\"ou le fichier \""+nomFichier+"\" n'existe pas.\n").reset());
			
	}
}
