package fr.uvsq.gestionDoc;

/**
 * <p>
 * Classe principale qui gère le déroulement global de l'application : initialisation de la base de données et manipulation en lignes de commandes.</p>
 * <p>
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Bonjour !");
        Database DB = new Database();
        
        CommandUI CUI = new CommandUI();
		while(true)
		{
			System.out.println("\n===> Entrez une commande :");
			CUI.affiche(CUI.nextCommand());
		}
        
    }
}
