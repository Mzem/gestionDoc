package fr.uvsq.gestionDoc;

import fr.uvsq.gestionDoc.commands.*;
import fr.uvsq.gestionDoc.database.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
import org.fusesource.jansi.AnsiConsole;

/**
 * <p>
 * Classe principale qui gère le déroulement global de l'application : initialisation de la base de données et manipulation en lignes de commandes.</p>
 * <p>
 */
public class App 
{
	public static void main( String[] args )
    {
		AnsiConsole.systemInstall();
        System.out.println(ansi().eraseScreen().fgBrightGreen().a("\nBonjour !\n").reset());
        Database.getInstance();
        System.out.println(ansi().a("\nTapez la commande ").bold().a("help").reset().a(" pour afficher l'aide ou ").bold().a("help").reset().fgBrightYellow().a(" <nom commande>").reset().a(" pour l'afficher l'aide spécifique de la commande.\n"));
        
        CommandUI CUI = new CommandUI();
		while(true)
		{
			System.out.println(ansi().fgBrightBlue().a("\n>>>>>>>>>>>>>  Entrez une commande :").reset());
			CUI.affiche(CUI.nextCommand());
		}
    }
}
