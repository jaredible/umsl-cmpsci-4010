package net.jaredible.mindbank.dao.tag;

import java.util.List;

import net.jaredible.mindbank.model.Tag;

public interface TagDao {

	Tag getTagById(long i);

	Tag getTagByName(String s);

	List<Tag> getAllTags();

	long addTag(Tag t);

	int deleteTagById(long i);

}
