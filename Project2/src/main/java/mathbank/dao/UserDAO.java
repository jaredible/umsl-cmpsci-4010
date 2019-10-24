package main.java.mathbank.dao;

import main.java.mathbank.model.User;

public interface UserDAO {

	public boolean checkUsername(String s);

	public boolean login(String s1, String s2);

	public void addUser(User u);

	public User getUser(int i);
	
	public User getUser(String s);

	public void updateUser(int i);

	public void deleteUser(int i);

}
