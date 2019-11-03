package main.java.mindbank.dao;

import main.java.mindbank.model.AuthToken;

public interface AuthDAO {

	public void createWithToken(AuthToken t);

	public AuthToken getBySelector(String s);

	public void updateWithToken(AuthToken t);

	public void deleteById(int i);

}
