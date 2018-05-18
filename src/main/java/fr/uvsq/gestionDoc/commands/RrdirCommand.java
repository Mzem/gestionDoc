package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class RrdirCommand implements Command 
{
	private String nomRepertoire;
	private String nomRepertoire2;
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
	private static final RepertoireFichierDAO repficDAO = DAOFactory.getRepertoireFichierDAO();
	 
	 
	public RrdirCommand(String nomR1,String nomR2) {
		nomRepertoire = nomR1;
		nomRepertoire2 = nomR2;
	}
	
	public void execute() 
	{
		if (repficDAO.existe(nomRepertoire,nomRepertoire2))
			repficDAO.delete(nomRepertoire,nomRepertoire2);
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le repertoire \""+nomRepertoire+"\" ou  \""+nomRepertoire2+"\" n'existe pas.\n").reset());
			
	}
}
