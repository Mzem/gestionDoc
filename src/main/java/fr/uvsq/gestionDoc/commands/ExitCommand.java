package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import org.fusesource.jansi.AnsiConsole;

/**
 * Commande de sortie du programme avec déconnection de la BD. 
 */
public class ExitCommand implements Command {

	public ExitCommand() {
	}

	public void execute() {
		Database.closeConnection();
		AnsiConsole.systemUninstall();
		System.exit(1);
	}
}
