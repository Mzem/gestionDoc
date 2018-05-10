package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import org.fusesource.jansi.AnsiConsole;

public class ExitCommand implements Command {

	public ExitCommand() {
	}

	public void execute() {
		Database.closeConnection();
		AnsiConsole.systemUninstall();
		System.exit(1);
	}
}
