package net.jaredible.mindbank.dao.auth;

import net.jaredible.mindbank.model.AuthToken;

public interface TokenDao {

	AuthToken getTokenBySelector(String s);

	int addToken(AuthToken t);

	int deleteTokenById(long i);

}
