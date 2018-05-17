package fr.uvsq.gestionDoc.donnees;

import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Repertoire
{
	protected String nom;
	protected String nomRepParent;
	protected String auteur;
	protected String dateCreation;
	
	/**
	* Construction d'un repertoire quelconque
	* @param les attributs d'un repertoire
	*/
	public Repertoire(String nom, String nomRepParent, String auteur, String dateCreation)
	{
		this.nom = nom;
		this.nomRepParent = nomRepParent;
		this.auteur = auteur;
		this.dateCreation = dateCreation;
		
		System.out.println("Création du repertoire \"" + nom + "\" : " +auteur+", "+dateCreation+"...");
	}
	
	
	public String getNom() { return this.nom; }
	public String getNomRepParent() { return this.nomRepParent; }
	public String getAuteur() { return this.auteur; }
	public String getDateCreation() { return this.dateCreation; }
	

}
