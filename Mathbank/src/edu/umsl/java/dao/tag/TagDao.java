package edu.umsl.java.dao.tag;

import java.util.List;

import edu.umsl.java.model.Tag;

public interface TagDao {

	public int addTag(Tag t);

	public List<Tag> getTags();

	public boolean getTagIdExists(int i);

	public boolean getNameExists(String s);

	public Tag getTagById(int i);

	public void updateTag(Tag t);

	public void deleteTagById(int i);

}
