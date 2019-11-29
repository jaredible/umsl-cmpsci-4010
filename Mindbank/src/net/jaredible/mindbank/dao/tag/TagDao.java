package net.jaredible.mindbank.dao.tag;

import java.util.List;

import net.jaredible.mindbank.model.Tag;

public interface TagDao {

	Tag getTagById(long id);

	Tag getTagByName(String name);

	List<Tag> getAllTags();

	long addTag(Tag tag);

}
