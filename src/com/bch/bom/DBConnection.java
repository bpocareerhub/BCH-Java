package com.bch.bom;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConnection {
	private final String DBHOST = "localhost";
	private final String DBUSER = "bch_hybrid";
	private final String DBPASSWORD = "C@re3rHub101";
	private final Integer DBPORT = 3306; 
	private final String DBNAME = "bch_hybrid";
	private final String DBDRIVER = "com.mysql.jdbc.Driver";
	private Connection connection = null;
	
	public DBConnection() {
		try {
			Class.forName(this.DBDRIVER).newInstance();
			
			String url = "jdbc:mysql://" + this.DBHOST + ":" + this.DBPORT + "/" + this.DBNAME;
			
			this.connection = (Connection) DriverManager.getConnection(url, this.DBUSER, this.DBPASSWORD);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getDBHOST() {
		return DBHOST;
	}

	public String getDBUSER() {
		return DBUSER;
	}

	public String getDBPASSWORD() {
		return DBPASSWORD;
	}

	public Integer getDBPORT() {
		return DBPORT;
	}

	@Override
	public String toString() {
		return "DBConnection [DBHOST=" + DBHOST + ", DBUSER=" + DBUSER
				+ ", DBPASSWORD=" + DBPASSWORD + ", DBPORT=" + DBPORT
				+ ", connection=" + connection + "]";
	}	
}
