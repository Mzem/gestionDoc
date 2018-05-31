package fr.uvsq.gestionDoc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import java.io.*;

import fr.uvsq.gestionDoc.commands.*;
import fr.uvsq.gestionDoc.database.*;


/**
 * Classe testant l'interaction avec l'utilisateur à l'aide de commandes.
 */
public class CommandUITest {
	CommandUI CUI = new CommandUI(); 
	
	//Dans ces test on va simuler l'entrée clavier
	//On ne testera pas le resultat de la commande mais seulement son type
	
	//Réinitialisation de l'entrée standard par défaut apres chaque test
	@After
	public void reinitEntreeStandard(){
		System.setIn(System.in);
	}
	
	@Test
	public void testNextCommandHelp() {
		ByteArrayInputStream in = new ByteArrayInputStream("help".getBytes());
		System.setIn(in);
		
		assertTrue("Erreur commande help",CUI.nextCommand() instanceof HelpCommand);
	}
	
	@Test
	public void testNextCommandShow() {
		ByteArrayInputStream in = new ByteArrayInputStream("show".getBytes());
		System.setIn(in);
		
		assertTrue("Erreur commande show",CUI.nextCommand() instanceof ShowCommand);
	}
	
	@Test
	public void testNextCommandLs() {
		ByteArrayInputStream in = new ByteArrayInputStream("ls".getBytes());
		System.setIn(in);
		
		assertTrue("Erreur commande ls",CUI.nextCommand() instanceof LsCommand);
	}
	
	@Test
	public void testNextCommandExit() {
		ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
		System.setIn(in);
		
		assertTrue("Erreur commande exit",CUI.nextCommand() instanceof ExitCommand);
	}
	
	@AfterClass
	public static void deco(){
		Database.closeConnection();
	}	
		
}
