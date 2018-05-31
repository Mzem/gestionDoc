package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import java.nio.file.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;


/**
 * Commande de création d'un répertoire dans la BD.
 */
public class MkdirCommand implements Command 
{
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private String nomRepertoire;
	private static final RepertoireDAO repDAO = DAOFactory.getRepertoireDAO();
	 
	public MkdirCommand(String nom) {
		nomRepertoire = nom;
	}
	
	public void execute() 
	{
		Date date = new Date();
		
		//Il faut au préalable s'assurer que le répertoire INBOX existe bien dans la base de données, sinon on le crée
		if (!repDAO.existe("INBOX")) {
			Repertoire INBOX = new Repertoire("INBOX", null, System.getProperty("user.name").toString(), dateFormat.format(date).toString());
			repDAO.create(INBOX);
		}
		
		//Création d'un objet répertoire
		String[] reps = Repertoire.getActuel().split("/");
		Repertoire rep = new Repertoire(nomRepertoire, reps[reps.length-1], System.getProperty("user.name").toString(), dateFormat.format(date).toString());
		
		//Ajout à la BD	
		if (!repDAO.existe(nomRepertoire))
			repDAO.create(rep);
		else
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : un répertoire nommé \""+nomRepertoire+"\" existe déja.\n").reset());
			
	}
}


