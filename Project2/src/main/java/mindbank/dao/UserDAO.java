package main.java.mindbank.dao;

import main.java.mindbank.model.User;

public interface UserDAO {

	public boolean getEmailExists(String s);

	public boolean isValidCredentials(String s1, String s2);

	public void setLogin(User user);

	public void addUser(User u);

	public User getUser(int i);

	public User getUser(String s);

	public void deleteUser(int i);

}
