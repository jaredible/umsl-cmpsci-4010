package net.jaredible.mindbank.service;

import java.util.List;

import net.jaredible.mindbank.db.tag.TagDao;
import net.jaredible.mindbank.db.tag.TagDaoImpl;
import net.jaredible.mindbank.model.tag.TagModel;

public class TagService {

	private final TagDao tagDao = new TagDaoImpl();

	public List<TagModel> getAllTags() {
		return tagDao.getAllTags();
	}
	
}
