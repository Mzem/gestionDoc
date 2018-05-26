package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class CdCommand implements Command 
{
	private String nomRepertoire;
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();

	 
	public CdCommand(String nom) {
		nomRepertoire = nom;
	}
	
	public void execute() 
	{
		String[] reps = Repertoire.getActuel().split("/");
		String actuel = reps[reps.length-1];
		
		
		if (nomRepertoire.equals("INBOX"))
			Repertoire.setActuel("INBOX/");
		else if (nomRepertoire.equals("..")) {
			if (reps.length >= 2) {
				reps[reps.length-1] = "";
				Repertoire.setActuel(String.join("/",reps));
			} else
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : il n'existe pas de répertoire parent.\n").reset());
		} else if ( repDAO.existeFils(actuel, nomRepertoire) ) {
			Repertoire.setActuel(Repertoire.getActuel()+nomRepertoire+"/");
		} else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le repertoire \""+nomRepertoire+"\" n'est pas un sous-répertoire du répertoire actuel.\n").reset());
			
	}
}
