package fr.uvsq.gestionDoc.database;

import fr.uvsq.gestionDoc.donnees.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

public class FichierTexteDAO extends FichierDAO 
{
	public FichierTexteDAO(Connection cnx) 
	{
		super(cnx);
	}

}
