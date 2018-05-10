package fr.uvsq.gestionDoc.donnees;

import java.io.*;
import java.nio.file.*;

/**
 * <p>
 * Classe qui modélise les données d'un fichier texte.</p>
 */
public class FichierTexte extends Fichier
{
	/**
	* Construction d'un fichier texte
	* @param les attributs d'un fichier
	*/
	public FichierTexte(String nom, String nomPropre, String extension, double taille, String auteur, String dateAjout, String type)
	{
		super(nom, nomPropre, extension, taille, auteur, dateAjout, type);
	}
	

}
