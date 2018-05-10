package fr.uvsq.gestionDoc.donnees;

import java.io.*;
import java.nio.file.*;

/**
 * <p>
 * Classe qui modélise les données d'un fichier média.</p>
 */
public class FichierMedia extends Fichier
{
	/**
	* Construction d'un fichier média
	* @param les attributs d'un fichier
	*/
	public FichierMedia(String nom, String nomPropre, String extension, double taille, String auteur, String dateAjout, String type)
	{
		super(nom, nomPropre, extension, taille, auteur, dateAjout, type);
	}
	

}
