package fr.uvsq.gestionDoc.donnees;

import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * <p>
 * Classe qui modélise les données d'un fichier quelconque et qui gère l'upload à l'INBOX de l'application.</p>
 */
public class Fichier
{
	protected String nom;
	protected String nomPropre;
	protected String extension;
	protected double taille;
	protected String auteur;
	protected String dateAjout;
	protected String type;
	
	/**
	* Construction d'un fichier quelconque
	* Paramètres : les attributs d'un fichier
	*/
	public Fichier(String nom, String nomPropre, String extension, double taille, String auteur, String dateAjout, String type)
	{
		this.nom = nom;
		this.nomPropre = nomPropre;
		this.extension = extension;
		this.taille = taille;
		this.auteur = auteur;
		this.dateAjout = dateAjout;
		this.type = type;
		
		System.out.println("Création du fichier \"" + nom + "\" : " + taille + "octets, "+auteur+", "+dateAjout+", "+type+"...");
	}
	
	/**
	* Upload d'un fichier ou d'un répertoire dans INBOX.
	* @param cheminFichier : chemin (absolu ou relatif) du fichier/répertoire à upload.
	* @return liste des noms des fichiers uploadés dans INBOX.
	*/
	public static ArrayList<Path> upload(String cheminFichier)
	{
		//On verifie INBOX, sinon on le crée
		try {
			Path pathINBOX = Paths.get("resources/INBOX");
			if (!Files.exists(pathINBOX) || !Files.isDirectory(pathINBOX))
				Files.createDirectory(pathINBOX);
		} catch (IOException e) {
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : impossible de créer INBOX.\n").reset());
			return null;
		}
		
		ArrayList<Path> fichiers = new ArrayList<Path>();
		
		//On essaye d'ouvrir ce chemin
		Path path = Paths.get(cheminFichier);
		//Si il n'existe pas on quitte
		if (!Files.exists(path)) {
			System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le chemin \""+ cheminFichier +"\" ne corréspond à aucun fichier ou répertoire.\n").reset());
			return fichiers;
		}
		//Si c'est un répertoire, on ajoute récursivement tous ces sous-fichiers, sinon on l'upload directement
		if (Files.isDirectory(path))	
			uploadRep(path, fichiers);
		else
			uploadFichier(path, fichiers);
			
		return fichiers;
	}
	
	/**
	* Upload d'un fichier dans INBOX.
	* @param path : chemin (absolu ou relatif) du fichier à upload.
	*/
	private static void uploadFichier(Path path, ArrayList<Path> fichiers) 
	{
		//On copie le fichier dans INBOX
		System.out.println("Upload du fichier \""+path.getFileName()+"\" dans INBOX...");
		Path pathINBOX = Paths.get("resources/INBOX/"+path.getFileName());
		try {
			Files.copy(path, pathINBOX);
			fichiers.add(pathINBOX);
		} catch (IOException e) {
			//if (e instanceof FileAlreadyExistsException)
			FileAlreadyExistsException e1 = new FileAlreadyExistsException(path.getFileName().toString());
			if (e.getClass() == e1.getClass())
				System.err.println(ansi().fgBrightRed().a("\n----- Erreur ----- : le fichier \""+ path.getFileName() +"\" existe déjà dans INBOX.\n").reset());
			else
				e.printStackTrace();
		}
		
	}
	
	/**
	* Upload récursif des sous-fichiers d'un répertoire dans INBOX.
	* @param path : chemin (absolu ou relatif) du répertoire à upload.
	*/
	private static void uploadRep(Path path, ArrayList<Path> fichiers)
	{
		try ( DirectoryStream<Path> sousPaths = Files.newDirectoryStream(path) ) 
		{
			for(Path sousPath : sousPaths)
			{
				if (Files.isDirectory(sousPath))
					uploadRep(sousPath,fichiers);
				else
					uploadFichier(sousPath,fichiers);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteINBOX(String nomFichier) 
	{
		Path pathINBOX = Paths.get("resources/INBOX/"+nomFichier);
		try {
			Files.delete(pathINBOX);
			System.out.println("Suppression du fichier \""+nomFichier+"\" de INBOX...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean existeINBOX(String nomFichier) 
	{
		//On essaye d'ouvrir ce chemin
		Path pathINBOX = Paths.get("resources/INBOX/"+nomFichier);
		//Si il n'existe pas on quitte
		if (!Files.exists(pathINBOX)) {
			return false;
		}
		return true;
	}
	
	public String getNom() { return this.nom; }
	public String getNomPropre() { return this.nomPropre; }
	public String getExtension() { return this.extension; }
	public double getTaille() { return this.taille; }
	public String getAuteur() { return this.auteur; }
	public String getDateAjout() { return this.dateAjout; }
	public String getType() { return this.type; }
	

}
