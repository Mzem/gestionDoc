package fr.uvsq.gestionDoc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import java.io.*;
import java.lang.ArrayIndexOutOfBoundsException;

import fr.uvsq.gestionDoc.commands.*;
import fr.uvsq.gestionDoc.database.*;
import fr.uvsq.gestionDoc.donnees.*;

/**
 * Classe testant l'ajout et la suppression d'un fichier de l'application (INBOX et BD).
 */
public class AddRemoveTest {
	
	private static final FichierDAO ficDAO = DAOFactory.getFichierDAO();
	
	@Test
	public void testAddInexistant() {
		
		AddCommand Add = new AddCommand("random.lol");
		
		Add.execute();
		
		assertTrue("Erreur execution commande add pour fichier inexistant",!ficDAO.existe("random.lol"));
	}
	
	@Test
	public void testAddExistant() {
		
		AddCommand Add = new AddCommand("src/test/java/fr/uvsq/gestionDoc/testFile.txt");
		
		Add.execute();
		
		assertTrue("Erreur execution commande add pour fichier existant",ficDAO.existe("testFile.txt"));
		
		RemoveCommand Remove = new RemoveCommand("testFile.txt");
		
		Remove.execute();
	}
	
	@Test
	public void testRemoveExistant() {
		
		AddCommand Add = new AddCommand("src/test/java/fr/uvsq/gestionDoc/testFile.txt");
		
		Add.execute();
		
		RemoveCommand Remove = new RemoveCommand("testFile.txt");
		
		Remove.execute();
		
		assertTrue("Erreur execution commande remove pour fichier existant",!ficDAO.existe("testFile.txt"));
	}

}
