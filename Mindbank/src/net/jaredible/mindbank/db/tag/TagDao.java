package net.jaredible.mindbank.db.tag;

import java.util.List;

import net.jaredible.mindbank.model.tag.TagModel;

public interface TagDao {

	TagModel getTagById(long id);

	TagModel getTagByName(String name);

	List<TagModel> getAllTags();

	long addTag(TagModel tag);

}
