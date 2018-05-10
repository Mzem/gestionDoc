package fr.uvsq.gestionDoc.commands;

import java.util.Scanner;

/**
 * <p>
 * Classe qui gère l'entrée et l'exécution des commandes (moteur de commandes).</p>
 * <p>
 */
public class CommandUI
{
    public CommandUI() {
    }
    
    /**
	* Demande à rentrer une commande et analyse son résultat.
	* @return Commande à executer.
	*/
    public Command nextCommand()
	{
        Scanner sc = new Scanner(System.in);
        String entree =  sc.nextLine();
        String[] entreeSplit = entree.split(" ");
        
        if (entreeSplit[0].equals("add"))
        {
			try {
				AddCommand Add = new AddCommand(entreeSplit[1]);
				return Add;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("\n----- Erreur ----- : commande add : veuillez spécifier le fichier à ajouter.\n");
				return null;
			}
		}/*
		else if (entreeSplit[0].equals("show"))
        {
			RechercheNomCommand R = new RechercheNomCommand(entree);
			return R;
		}*/
		else if (entree.equals("exit"))
		{
			ExitCommand E = new ExitCommand();
			return E;
		}
		else 
			return null;
	}

	/**
	* Affiche le résultat de l'execution de la commande si possible, ou un message d'erreur.
	* @param C : commande à executer.
	*/
	public void affiche(Command C) {
		System.out.println();
		if (C == null)
			System.err.println("\n----- Erreur ----- : commande incorrecte.\n");
		else
			C.execute();
	}
}
