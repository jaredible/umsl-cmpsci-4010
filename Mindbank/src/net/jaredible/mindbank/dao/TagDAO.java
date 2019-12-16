package net.jaredible.mindbank.dao;

import java.util.List;

import net.jaredible.mindbank.model.Tag;

public interface TagDAO {

	List<Tag> list() throws DAOException;

	List<Tag> list(Long problemId) throws DAOException;

	void create(Tag tag) throws IllegalArgumentException, DAOException;

	void createProblemAssociation(Long problemId, Long tagId) throws DAOException;

	void deleteProblemAssociations(Long problemId) throws DAOException;

	boolean existName(String name) throws DAOException;

}
