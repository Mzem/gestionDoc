package fr.uvsq.gestionDoc.commands;

import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class HelpCommand implements Command 
{
	private String commande;
	
	public HelpCommand(String c) {
		commande = c;
	}
	
	public void execute() 
	{
		if (commande == null || commande.equals("add")) {
			System.out.println(ansi().a("\t").bold().a("add").reset().fgBrightYellow().a(" <chemin fichier>").reset().a(" : ajoute le fichier ou tous les fichiers du répertoire dont le chemin est indiqué par <chemin fichier> à la base de données.\n"));
		}	
		if (commande == null || commande.equals("remove")) {
			System.out.println(ansi().a("\t").bold().a("remove").reset().fgBrightYellow().a(" <nom fichier>").reset().a(" : supprime le fichier nommé <nom fichier> de la base de données.\n"));
		}	
		if (commande == null || commande.equals("show")) {
			System.out.println(ansi().a("\t").bold().a("show").reset().fgBrightYellow().a(" -extension=<ext> -auteur=<auteur> -date=<yyyy/MM/dd> -type=<type>").reset().a(" : affiche les fichiers contenus dans la base de données, avec possibilité d'affiner la recherche avec les options en utilisant la syntaxe ").fgBrightYellow().a("-critere=valeur").reset().a(".\n"));
		}	
		if (commande == null || commande.equals("exit")) {
			System.out.println(ansi().a("\t").bold().a("exit").reset().a(" : quitte l'application.\n"));
		}	
	}
}

