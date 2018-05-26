package fr.uvsq.gestionDoc.commands;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

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
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande add : veuillez spécifier le chemin fichier à ajouter.\n").reset());
				return null;
			}
		}
		else if (entreeSplit[0].equals("remove"))
        {
			try {
				RemoveCommand Remove = new RemoveCommand(entreeSplit[1]);
				return Remove;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande remove : veuillez spécifier le nom complet du fichier à supprimer.\n").reset());
				return null;
			}
		}
		else if (entreeSplit[0].equals("show"))
        {
			ShowCommand S = new ShowCommand(entreeSplit);
			return S;
		}
		else if (entreeSplit[0].equals("help"))
        {
			try {
				HelpCommand S = new HelpCommand(entreeSplit[1]);
				return S;
			} catch (ArrayIndexOutOfBoundsException e) {
				HelpCommand S = new HelpCommand(null);
				return S;
			}
		}
		else if (entree.equals("exit"))
		{
			ExitCommand E = new ExitCommand();
			return E;
		}
		else if (entreeSplit[0].equals("mkdir"))
        {
			try {
				MkdirCommand Mkdir = new MkdirCommand(entreeSplit[1]);
				return Mkdir;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande mkdir : veuillez spécifier le nom du répertoire à ajouter.\n").reset());
				return null;
			}
		}
		else if (entreeSplit[0].equals("ls"))
        {
			LsCommand ls = new LsCommand();
			return ls;
		}
		else if (entreeSplit[0].equals("cd"))
        {
			try {
				CdCommand CD = new CdCommand(entreeSplit[1]);
				return CD;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande cd : veuillez spécifier le nom du répertoire à accéder.\n").reset());
				return null;
			}
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
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande incorrecte.\n").reset());
		else
			C.execute();
	}
}
