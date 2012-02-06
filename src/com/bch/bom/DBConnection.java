package com.bch.bom;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class DBConnection {
	Properties prop = new Properties();
	private Connection connection = null;
	
	public DBConnection() {
		try {
			prop.load(new FileInputStream("config.properties"));
			Class.forName(prop.getProperty("dbdriver")).newInstance();
			String url = "jdbc:mysql://" + prop.getProperty("dbhost") + ":" + prop.getProperty("dbport") + "/" + prop.getProperty("dbname");
			
			this.connection = (Connection) DriverManager.getConnection(url, prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
