package fr.uvsq.gestionDoc.donnees;

/**
 * <p>
 * Classe qui modélise les données d'un répertoire.</p>
 */
public class Repertoire
{
	protected static String actuel = "INBOX/";
	protected String nom;
	protected String nomRepParent;
	protected String auteur;
	protected String dateCreation;
	
	/**
	* Construction d'un repertoire quelconque
	* Paramètres : les attributs d'un repertoire
	*/
	public Repertoire(String nom, String nomRepParent, String auteur, String dateCreation)
	{
		this.nom = nom;
		this.nomRepParent = nomRepParent;
		this.auteur = auteur;
		this.dateCreation = dateCreation;
		
		System.out.println("Création du repertoire \"" + nom + "\" : "+nomRepParent+", "+auteur+", "+dateCreation+"...");
	}
	
	
	public String getNom() { return this.nom; }
	public String getNomRepParent() { return this.nomRepParent; }
	public String getAuteur() { return this.auteur; }
	public String getDateCreation() { return this.dateCreation; }
	public static String getActuel() { return actuel; }
    public static void setActuel(String actu) { actuel = actu; }


}
