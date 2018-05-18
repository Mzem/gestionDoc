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
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande add : veuillez spécifier le fichier à ajouter.\n").reset());
				return null;
			}
		}
		else if (entreeSplit[0].equals("mkdir"))
        {
			try {
				MkdirCommand Mkdir = new MkdirCommand(entreeSplit[1]);
				return Mkdir;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande mkdir : veuillez spécifier le repertoire à ajouter.\n").reset());
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
		else if (entreeSplit[0].equals("cd"))
        {
			try {
				CdCommand Cd = new CdCommand(entreeSplit[1]);
				return Cd;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande cd : veuillez spécifier le nom complet du repertoire.\n").reset());
				return null;
			}
		}
		else if (entreeSplit[0].equals("rmdir"))
        {
			try {
				RmdirCommand Rmdir = new RmdirCommand(entreeSplit[1]);
				return Rmdir;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande rmdir : veuillez spécifier le nom complet du repertoire à supprimer.\n").reset());
				return null;
			}
		}
		else if (entreeSplit[0].equals("ls"))
        {
			try {
				LsCommand Ls = new LsCommand();
				return Ls;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande ls \n").reset());
				return null;
			}
		}
		else if (entreeSplit[0].equals("rfdir"))
        {
			try {
				RfdirCommand Rfdir = new RfdirCommand(entreeSplit[2],entreeSplit[1]);
				return Rfdir;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande rfdir : veuillez spécifier le nom complet du repertoire à supprimer.\n").reset());
				return null;
			}
		}
		else if (entreeSplit[0].equals("rrdir"))
        {
			try {
				RrdirCommand Rrdir = new RrdirCommand(entreeSplit[2],entreeSplit[1]);
				return Rrdir;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande rfdir : veuillez spécifier le nom complet du repertoire à supprimer.\n").reset());
				return null;
			}
		}
		else if (entreeSplit[0].equals("afdir"))
        {
			try {
				AfdirCommand Afdir = new AfdirCommand(entreeSplit[1],entreeSplit[2]);
				return Afdir;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande afdir : veuillez spécifier le nom complet du repertoire et du fichier.\n").reset());
				return null;
			}
		}
		else if (entreeSplit[0].equals("ardir"))
        {
			try {
				ArdirCommand Ardir = new ArdirCommand(entreeSplit[2],entreeSplit[1]);
				return Ardir;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : commande ardir : veuillez spécifier le nom complet des deux repertoires.\n").reset());
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
