package net.jaredible.mindbank.dao.user;

import java.util.List;

import net.jaredible.mindbank.model.User;

public interface UserDao {

	User getUserById(long id);

	User getUserByEmail(String email);

	User getUserByUserName(String userName);

	User getUserByCookieSelector(String cookieSelector);

	List<User> getAllUsers();

	long addUser(User user);

	int updateUser(User user);

	int deleteUserById(long id);

}
