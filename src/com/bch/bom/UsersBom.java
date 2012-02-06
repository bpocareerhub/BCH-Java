package com.bch.bom;

import java.util.ArrayList;
import java.util.Iterator;

import com.bch.beans.Users;
import com.bch.dao.UsersDao;

public class UsersBom {
	private UsersDao userDao = null;
	
	public UsersBom() {
		this.userDao = new UsersDao();
	}
	
	public void executeEmail() {
		ArrayList<Users> users = this.userDao.retrieveAll();
		for(Iterator<Users> i = users.iterator(); i.hasNext();) {
			Users user = (Users) i.next();
			new EmailBom(user).sendMail();
		}
	}
	
	public boolean validateEmailMatching() {
		return false;
	}

	
	public UsersDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UsersDao userDao) {
		this.userDao = userDao;
	}
	
}
