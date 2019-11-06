package main.java.mindbank.dao;

import java.sql.Connection;

import main.java.mindbank.model.User;

public interface UserDAO {

	public boolean getEmailExists(String s);

	public boolean isValidCredentials(String s1, String s2);

	public void updateLoginTimestampById(int i);

	public void addUser(User u);

	public User getUserById(int i);

	public User getUserByEmail(String s);

	public void updateUserNameById(int i, String s);

	public void deleteUser(int i);

	public Connection getConnection();

}
