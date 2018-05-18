package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class CdCommand implements Command 
{
	private String nomRepertoire;
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
	private static final RepertoireFichierDAO repficDAO = DAOFactory.getRepertoireFichierDAO();

	 
	public CdCommand(String nom) {
		nomRepertoire = nom;
	}
	
	public void execute() 
	{
		//c'est un repertoire et il est dans Actuel
		if ((repDAO.existe(nomRepertoire)) && (repficDAO.existe(Repertoire.getActuel(),nomRepertoire))){
			Repertoire.setActuel(nomRepertoire);
			System.err.println(ansi().fgBrightGreen().a("\n vous etes dans le repertoire : \""+nomRepertoire+"\".\n").reset());
		}
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le repertoire \""+nomRepertoire+"\" n'existe pas.\n").reset());
			
	}
}
