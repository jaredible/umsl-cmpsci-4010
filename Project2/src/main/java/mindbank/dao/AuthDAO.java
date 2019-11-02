package main.java.mindbank.dao;

import main.java.mindbank.model.AuthToken;

public interface AuthDAO {

	public AuthToken findBySelector(String s);

	public void createWithToken(AuthToken t);

	public void updateWithToken(AuthToken t);

}
