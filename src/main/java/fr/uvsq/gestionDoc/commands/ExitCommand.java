package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;

public class ExitCommand implements Command {

	public ExitCommand() {
	}

	public void execute() {
		Database.closeConnection();
		System.exit(1);
	}
}
