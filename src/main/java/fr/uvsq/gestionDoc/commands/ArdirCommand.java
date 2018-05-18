package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class ArdirCommand implements Command 
{
	private String nomRepertoire;
	private String nomRepertoire2;
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
	private static final RepertoireFichierDAO repficDAO = DAOFactory.getRepertoireFichierDAO();
	 
	public ArdirCommand(String nomR, String nomR2) {
		nomRepertoire = nomR;
		nomRepertoire2 = nomR2;
	}
	
	public void execute() 
	{
		//rajouter test nomR1 != nomR2 et R1 n'est pas dans R2 
		if ((repDAO.existe(nomRepertoire)) && (repDAO.existe(nomRepertoire2))){
			RepertoireFichier RF = new RepertoireFichier(nomRepertoire,nomRepertoire2);
			repficDAO.create(RF);
		}
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le repertoire \""+nomRepertoire+"\" ou \""+nomRepertoire2+"\" n'existe pas.\n").reset());
			
	}
}
