package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class LsCommand implements Command 
{
	private static final RepertoireFichierDAO repficDAO = DAOFactory.getRepertoireFichierDAO();

	 
	public LsCommand(){}
	
	public void execute() 
	{
		repficDAO.find(Repertoire.getActuel());
		System.err.println(ansi().fgBrightGreen().a("\n vous etes dans le repertoire : \""+Repertoire.getActuel()+"\".\n").reset());
	}	
}
