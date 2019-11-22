package net.jaredible.mindbank.dao;

import java.sql.Connection;

import net.jaredible.mindbank.model.Auth;

public interface AuthDAO {

	public void updateSelectorById(int i, String s);

	public void updateValidatorById(int i, String s);

	public void createWithToken(Auth t);

	public Auth getBySelector(String s);

	public void updateWithToken(Auth t);

	public void deleteById(int i);

	public Connection getConnection();

	public void closeConnections();

}
