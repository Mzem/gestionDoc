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
	}
	
	
	public String getNomFichier() { return this.nomFichier; }
	public String getNomRepertoire() { return this.nomRepertoire; }
	

}
