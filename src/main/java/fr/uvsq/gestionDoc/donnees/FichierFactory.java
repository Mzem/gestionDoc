package fr.uvsq.gestionDoc.donnees;

import java.io.*;
import java.nio.file.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FichierFactory
{
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static Fichier getFichier(Path path)
	{
		//Extraction metadonnées
		try {
			//Nom et extension
			String nomComplet = path.getName(2).toString();
			String[] nomSplit = nomComplet.split("\\.");
			StringBuilder nomPropre = new StringBuilder(nomSplit[0]);
			int i = 1;
			for (i = 1; i < (nomSplit.length - 1) ; i++) {
				nomPropre.append(".");
				nomPropre.append(nomSplit[i]);
			}
			String extension = "";
			extension = nomSplit[i];
			
			//Taille en octets
			double taille = Files.size(path);
			
			//Auteur
			String auteur = System.getProperty("user.name").toString();
			
			//Date Ajout
			Date date = new Date();
			String dateAjout = dateFormat.format(date).toString();
			
			if (extension.equals("txt")) {
				return new FichierTexte(nomComplet, nomPropre.toString(), extension, taille, auteur, dateAjout, "full text");
			} else if (extension.equals("pdf")) {
				String type = "";
				return new FichierTexte(nomComplet, nomPropre.toString(), extension, taille, auteur, dateAjout, type);
			} else if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("bmp") || extension.equals("gif")) {
				return new FichierMedia(nomComplet, nomPropre.toString(), extension, taille, auteur, dateAjout, "image");
			} else if (extension.equals("mp3")) {
				return new FichierMedia(nomComplet, nomPropre.toString(), extension, taille, auteur, dateAjout, "audio");
			} else if (extension.equals("mp4") || extension.equals("avi")) {
				return new FichierMedia(nomComplet, nomPropre.toString(), extension, taille, auteur, dateAjout, "video");
			} else 
				return new Fichier(nomComplet, nomPropre.toString(), extension, taille, auteur, dateAjout, "inconnu");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
