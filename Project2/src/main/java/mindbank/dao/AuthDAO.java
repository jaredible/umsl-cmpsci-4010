package main.java.mindbank.dao;

import main.java.mindbank.model.Auth;

public interface AuthDAO {

	public void updateSelectorById(int i, String s);

	public void updateValidatorById(int i, String s);

	public void createWithToken(Auth t);

	public Auth getBySelector(String s);

	public void updateWithToken(Auth t);

	public void deleteById(int i);

}
