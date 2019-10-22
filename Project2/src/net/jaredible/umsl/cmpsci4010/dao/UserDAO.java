package net.jaredible.umsl.cmpsci4010.dao;

import net.jaredible.umsl.cmpsci4010.model.User;

public interface UserDAO {

	public boolean checkUsername(String s);

	public boolean login(String s1, String s2);

	public void addUser(User u);

	public User getUser(int i);

	public void updateUser(int i);

	public void deleteUser(int i);

}
