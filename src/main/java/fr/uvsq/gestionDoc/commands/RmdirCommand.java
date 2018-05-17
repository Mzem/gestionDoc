package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class RmdirCommand implements Command 
{
	private String nomRepertoire;
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
	 
	public RmdirCommand(String nom) {
		nomRepertoire = nom;
	}
	
	public void execute() 
	{
		if (repDAO.existe(nomRepertoire))
			//Efface le repertoire de la BD
			repDAO.delete(nomRepertoire);
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le repertoire \""+nomRepertoire+"\" n'existe pas.\n").reset());
			
	}
}
