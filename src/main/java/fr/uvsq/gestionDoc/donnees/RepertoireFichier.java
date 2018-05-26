package fr.uvsq.gestionDoc.donnees;

/**
 * <p>
 * Classe qui modélise les données d'un fichier quelconque et qui gère l'upload à l'INBOX de l'application.</p>
 */
public class RepertoireFichier
{
	protected String nomFichier;
	protected String nomRepertoire;
	
	/**
	* Construction d'un fichier quelconque
	* @param les attributs d'un fichier
	*/
	public RepertoireFichier(String nomR, String nomF)
	{
		this.nomFichier = nomF;
		this.nomRepertoire = nomR;
		
		System.out.println("Ajout du fichier \""+nomFichier+"\" dans le répertoire \""+nomRepertoire+"\"...");	
	}
	
	
	public String getNomFichier() { return this.nomFichier; }
	public String getNomRepertoire() { return this.nomRepertoire; }
	

}
