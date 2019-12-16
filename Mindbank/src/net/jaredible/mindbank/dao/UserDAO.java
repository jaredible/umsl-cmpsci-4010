package net.jaredible.mindbank.dao;

import java.util.List;

import net.jaredible.mindbank.model.User;

public interface UserDAO {

	User find(Long id) throws DAOException;

	User find(String userName) throws DAOException;

	User find(String cookieSelector, int dummy) throws DAOException;

	List<User> list() throws DAOException;

	void create(User user) throws IllegalArgumentException, DAOException;

	void update(User user) throws IllegalArgumentException, DAOException;

	void delete(User user) throws DAOException;

	boolean existEmail(String email) throws DAOException;

	boolean existUserName(String userName) throws DAOException;

}
