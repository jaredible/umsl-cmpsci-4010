package net.jaredible.umsl.cmpsci4010.dao;

import net.jaredible.umsl.cmpsci4010.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean checkUsername(String username) {
		return false;
	}

	@Override
	public boolean login(String username, String password) {
		return false;
	}

	@Override
	public void addUser(User user) {
	}

	@Override
	public User getUser(int id) {
		return null;
	}

	@Override
	public void updateUser(int id) {
	}

	@Override
	public void deleteUser(int id) {
	}

}
