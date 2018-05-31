package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * Commande d'affichage des fichiers de la base de données.
 */
public class ShowCommand implements Command 
{
	String extension = null;
	String auteur = null;
	String dateAjout = null;
	String type = null;
	String actuel = null;
	private static final FichierDAO ficDAO = DAOFactory.getFichierDAO();
	 
	public ShowCommand(String[] options) 
	{
		for (int i = 1; i < options.length; i++)
		{
			String[] option = options[i].split("=");
			if (option.length == 2) {
				if (option[0].equals("-extension")) {
					extension = option[1];
				} 
				else if (option[0].equals("-auteur")) {
					auteur = option[1];
				}
				else if (option[0].equals("-date")) {
					dateAjout = option[1];
				}
				else if (option[0].equals("-type")) {
					type = option[1];
				}
			} else if (option.length == 1 && option[0].equals("-rep")) {
				String[] reps = Repertoire.getActuel().split("/");
				actuel = reps[reps.length-1];
			}
		}
	}
	
	public void execute() 
	{
		ficDAO.show(extension, auteur, dateAjout, type, actuel);
	}
}

