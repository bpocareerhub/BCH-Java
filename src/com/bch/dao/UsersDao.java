package com.bch.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.bch.beans.Users;
import com.bch.bom.DBConnection;

public class UsersDao {
	private Statement statement = null;
	private ResultSet rs = null;
	private Connection connect;
	static final Logger logger = Logger.getLogger(UsersDao.class);

	public UsersDao() {
		DBConnection conn = new DBConnection();
		this.connect = (Connection) conn.getConnection();
		PropertyConfigurator.configure("log4j.properties");
	}

	public ArrayList<Users> retrieveAll() {
		
		ArrayList<Users> users = new ArrayList<Users>();
		
		try {
			String sql = "select * from `20120126-ama` where `user_id`=1";
			this.statement = this.connect.createStatement();
			this.rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				Users user = new Users();
				user.setUser_id(rs.getLong("user_id"));
				user.setEmail(rs.getString("EMAIL"));
				user.setFirstname(rs.getString("FIRST_NAME"));
				user.setLastname(rs.getString("LAST_NAME"));
				user.setBranch(rs.getString("BRNCH_CODE"));
				user.setHash(rs.getString("HASH_LINK"));
				users.add(user);				
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return users;
	}
	
	public Users retrieveByUserId(int user_id) {
		Users user = null;
		try {
			String sql = "select * from user where user_id="+(int)user_id;
			this.statement = this.connect.createStatement();
			this.rs = statement.executeQuery(sql);
			
			if(this.rs.next()) {
				user = new Users();
				user.setUser_id(rs.getLong("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return user;
	}
	
	public boolean addNewUser(String username, String password, String email, String firstname, String lastname) {
		boolean success = false;
		try {
			String sql = "insert into user(username,password,email,firstname,lastname) " +
						 "values ('"+username+"','"+password+"','"+email+"','"+firstname+"','"+lastname+"')";
			this.statement = this.connect.createStatement();
			int insert = statement.executeUpdate(sql);
			if(insert > 0) {
				success = true;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return success;
	}
	
	public boolean deleteUserByUserId(int user_id) {
		int success = 0;
		
		boolean a = Boolean.parseBoolean(Integer.toString(success));
		try {
			String sql = "delete from user where user_id="+(int)user_id;
			this.statement = this.connect.createStatement();
			a = Boolean.parseBoolean(Integer.toString(statement.executeUpdate(sql)));
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return a;
	}
	
	public boolean retrieveUserByEmail(String email) {
		boolean success = false;
		try {
			String sql = "select * from user where email='"+(String)email+"'";
			this.statement = this.connect.createStatement();
			this.rs = statement.executeQuery(sql);
			success = this.rs.next();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return success;
	}
	
}
