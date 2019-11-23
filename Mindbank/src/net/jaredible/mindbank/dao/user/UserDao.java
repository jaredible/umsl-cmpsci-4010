package net.jaredible.mindbank.dao.user;

import java.util.List;

import net.jaredible.mindbank.model.User;

public interface UserDao {

	User getUserById(long i);

	User getUserByEmail(String s);

	User getUserByUserName(String s);

	List<User> getAllUsers();

	long addUser(User u);

	int updateUser(User u);

	int deleteUserById(long i);

}
