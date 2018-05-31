package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * Commande d'affichage d'aide sur les commandes implémentées.
 */
public class HelpCommand implements Command 
{
	private String commande;
	
	public HelpCommand(String c) {
		commande = c;
	}
	
	/**
	* Affichage de l'aide complète ou l'aide propre à une commande particulière.
	*/
	public void execute() 
	{
		if (commande == null || commande.equals("add")) {
			System.out.println(ansi().a("\t").bold().a("add").reset().fgBrightYellow().a(" <chemin fichier>").reset().a(" : ajoute le fichier ou tous les fichiers du répertoire dont le chemin est indiqué par <chemin fichier> à la base de données.\n"));
		}	
		if (commande == null || commande.equals("remove")) {
			System.out.println(ansi().a("\t").bold().a("remove").reset().fgBrightYellow().a(" <nom fichier>").reset().a(" : supprime le fichier nommé <nom fichier> de la base de données.\n"));
		}	
		if (commande == null || commande.equals("show")) {
			System.out.println(ansi().a("\t").bold().a("show").reset().fgBrightYellow().a(" -extension=<ext> -auteur=<auteur> -date=<yyyy/MM/dd> -type=<type> -rep").reset().a(" : affiche les fichiers contenus dans la base de données.\n\t\tPossibilité d'affiner la recherche avec les options en utilisant la syntaxe ").fgBrightYellow().a("-critere=valeur").reset().a(".\n\t\tL'option ").fgBrightYellow().a("-rep").reset().a(" n'affiche que les fichiers du répertoire actuel.\n"));
		}	
		if (commande == null || commande.equals("mkdir")) {
			System.out.println(ansi().a("\t").bold().a("mkdir").reset().fgBrightYellow().a(" <nom répertoire>").reset().a(" : crée un répertoire dont le nom est <nom répertoire> et dont le répertoire parent est le répertoire actuel (par défaut INBOX).\n"));
		}	
		if (commande == null || commande.equals("rmdir")) {
			System.out.println(ansi().a("\t").bold().a("rmdir").reset().fgBrightYellow().a(" <nom répertoire>").reset().a(" : supprime le répertoire dont le nom est <nom répertoire> s'il est bien un sous-répertoire du répertoire actuel et supprime les liens avec les fichiers qu'il contient.\n"));
		}	
		if (commande == null || commande.equals("ls")) {
			System.out.println(ansi().a("\t").bold().a("ls").reset().a(" : affiche les fichiers et les répertoires contenus dans le répertoire actuel.\n"));
		}	
		if (commande == null || commande.equals("cd")) {
			System.out.println(ansi().a("\t").bold().a("cd").reset().fgBrightYellow().a(" <nom répertoire>").reset().a(" : accède au sous-répertoire dont le nom est <nom répertoire> si il est bien un sous-répertoire du répertoire courant. <nom répertoire> peut valoir \"..\" ou \"INBOX\".\n"));
		}	
		if (commande == null || commande.equals("addf")) {
			System.out.println(ansi().a("\t").bold().a("addf").reset().fgBrightYellow().a(" <nom fichier>").reset().a(" : ajoute le fichier nommé <nom fichier> au répertoire actuel.\n"));
		}	
		if (commande == null || commande.equals("rmf")) {
			System.out.println(ansi().a("\t").bold().a("rmf").reset().fgBrightYellow().a(" <nom fichier>").reset().a(" : supprime le fichier nommé <nom fichier> du répertoire actuel.\n"));
		}	
		if (commande == null || commande.equals("exit")) {
			System.out.println(ansi().a("\t").bold().a("exit").reset().a(" : quitte l'application.\n"));
		}	
	}
}

