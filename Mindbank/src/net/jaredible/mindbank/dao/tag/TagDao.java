package net.jaredible.mindbank.dao.tag;

import java.util.List;

import net.jaredible.mindbank.model.Tag;

public interface TagDao {

	Tag getTagById(long i);

	List<Tag> getAllTags();

	int addTag(Tag t);

	int deleteTagById(long i);

	boolean getTagExistsByName(String s);

}
