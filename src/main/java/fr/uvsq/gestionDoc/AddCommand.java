package fr.uvsq.gestionDoc;

public class AddCommand implements Command 
{
	private String cheminFichier;
	 
	public AddCommand(String chemin) {
		cheminFichier = chemin;
	}

	public void execute() 
	{
		Database.ajouter(cheminFichier);
	}
}

