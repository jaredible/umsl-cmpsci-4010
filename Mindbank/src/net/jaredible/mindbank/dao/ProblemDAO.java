package net.jaredible.mindbank.dao;

import java.util.List;

import net.jaredible.mindbank.model.Problem;

public interface ProblemDAO {

	Problem find(Long id) throws DAOException;

	Problem find(String title) throws DAOException;

	List<Problem> list() throws DAOException;

	List<Problem> list(String categories, String tags, String users) throws DAOException;

	void create(Problem problem) throws IllegalArgumentException, DAOException;

	void update(Problem problem) throws IllegalArgumentException, DAOException;

	void delete(Problem problem) throws DAOException;

	boolean existTitle(String title) throws DAOException;

}
