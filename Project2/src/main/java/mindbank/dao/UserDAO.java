package main.java.mindbank.dao;

import main.java.mindbank.model.User;

public interface UserDAO {

	public boolean checkExists(String s);

	public boolean login(String s1, String s2);

	public void addUser(User u);

	public User getUser(int i);
	
	public User getUser(String s);

	public void updateUser(int i);

	public void deleteUser(int i);

}
