package net.jaredible.mindbank.service;

import java.util.List;

import net.jaredible.mindbank.dao.DAOFactory;
import net.jaredible.mindbank.dao.UserDAO;
import net.jaredible.mindbank.model.User;
import net.jaredible.mindbank.util.SecurityUtil;
import net.jaredible.mindbank.util.TimeUtil;

public class UserService {

	private static UserService instance;

	private UserDAO userDAO = DAOFactory.getInstance("mindbank.jndi").getUserDAO();

	private UserService() {
	}

	public User getUserById(Long id) {
		return userDAO.find(id);
	}

	public User getUserByUsername(String userName) {
		return userDAO.find(userName);
	}

	public User getUserByCookieSelector(String cookieSelector) {
		return userDAO.find(cookieSelector, 0);
	}

	public List<User> listAllUsers() {
		return userDAO.list();
	}

	public void login(String userName) {
		User user = userDAO.find(userName);
		user.setLastLoginTime(TimeUtil.getNowTime());
		userDAO.update(user);
	}

	public void updateCookieInfo(String userName, String selector, String rawValidator) {
		User user = userDAO.find(userName);
		String secretKey = SecurityUtil.generateRandomSalt();
		String hashedValidator = SecurityUtil.generateSHA512Hash(rawValidator, secretKey);
		user.setCookieSecretKey(secretKey);
		user.setCookieSelector(selector);
		user.setHashedCookieValidator(hashedValidator);
		userDAO.update(user);
	}

	public void updateProfileInfo(String userName, String name, String bio, String statusEmoji, String statusText) {
		System.out.println(userName);
		User user = userDAO.find(userName);
		user.setName(name);
		user.setBio(bio);
		user.setStatusEmoji(statusEmoji);
		user.setStatusText(statusText);
		userDAO.update(user);
	}

	public void register(String email, String userName, String passWord) {
		User user = new User();
		String passWordSalt = SecurityUtil.generateRandomSalt();
		String passWordHash = SecurityUtil.generateSHA512Hash(passWord, passWordSalt);
		user.setEmail(email);
		user.setUserName(userName);
		user.setRegisteredTime(TimeUtil.getNowTime());
		user.setPassWordSalt(passWordSalt);
		user.setPassWordHash(passWordHash);
		userDAO.create(user);
	}

	public boolean emailExists(String email) {
		return userDAO.existEmail(email);
	}

	public boolean userNameExists(String userName) {
		return userDAO.existUserName(userName);
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

}
