package net.jaredible.mindbank.service;

import java.util.List;

import net.jaredible.mindbank.dao.DAOFactory;
import net.jaredible.mindbank.dao.TagDAO;
import net.jaredible.mindbank.model.Tag;
import net.jaredible.mindbank.util.TimeUtil;

public class TagService {

	private static TagService instance;

	private TagDAO tagDAO = DAOFactory.getInstance("mindbank.jndi").getTagDAO();

	private TagService() {
	}

	public List<Tag> listAllTags() {
		return tagDAO.list();
	}

	public List<Tag> listCategoriesByProblemId(Long problemId) {
		return tagDAO.list(problemId);
	}

	public void addNewTag(String name, Long createdByUserId) {
		Tag tag = new Tag();
		tag.setName(name);
		tag.setCreatedTime(TimeUtil.getNowTime());
		tag.setCreatedByUserId(createdByUserId);
		tagDAO.create(tag);
	}

	public void addNewProblemAssociation(Long problemId, Long categoryId) {
		tagDAO.createProblemAssociation(problemId, categoryId);
	}

	public void deleteAllProblemAssociations(Long problemId) {
		tagDAO.deleteProblemAssociations(problemId);
	}

	public boolean nameExists(String name) {
		return tagDAO.existName(name);
	}

	public static TagService getInstance() {
		if (instance == null) {
			instance = new TagService();
		}
		return instance;
	}

}
