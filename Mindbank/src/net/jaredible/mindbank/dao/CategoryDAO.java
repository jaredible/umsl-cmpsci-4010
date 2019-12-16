package net.jaredible.mindbank.dao;

import java.util.List;

import net.jaredible.mindbank.model.Category;

public interface CategoryDAO {

	List<Category> list() throws DAOException;

	List<Category> list(Long problemId) throws DAOException;

	void create(Category category) throws IllegalArgumentException, DAOException;

	void createProblemAssociation(Long problemId, Long categoryId) throws DAOException;

	void deleteProblemAssociations(Long problemId) throws DAOException;

	boolean existName(String name) throws DAOException;

}
