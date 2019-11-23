package net.jaredible.mindbank.dao.tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.Tag;
import net.jaredible.mindbank.util.DbUtil;

public class TagDaoImpl implements TagDao {

	private Connection connection;
	private PreparedStatement getTagById;
	private PreparedStatement getTagByName;
	private PreparedStatement getAllTags;
	private PreparedStatement addTag;
	private PreparedStatement deleteTagById;

	@Override
	public Tag getTagById(long id) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getTagById == null) {
				getTagById = connection.prepareStatement("SELECT * FROM Tag WHERE ID = ?;");
			}

			getTagById.setLong(1, id);

			rs = getTagById.executeQuery();

			if (rs.next()) {
				Tag tag = new Tag();

				tag.setId(rs.getInt("ID"));
				tag.setName(rs.getString("Title"));
				tag.setCreatedTime(rs.getTimestamp("CreatedTime"));
				tag.setCreatedByUserId(rs.getInt("CreatedByUserID"));

				return tag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public Tag getTagByName(String name) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getTagByName == null) {
				getTagByName = connection.prepareStatement("SELECT * FROM Tag WHERE Name = ?;");
			}

			getTagByName.setString(1, name);

			rs = getTagByName.executeQuery();

			if (rs.next()) {
				Tag tag = new Tag();

				tag.setId(rs.getInt("ID"));
				tag.setName(rs.getString("Title"));
				tag.setCreatedTime(rs.getTimestamp("CreatedTime"));
				tag.setCreatedByUserId(rs.getInt("CreatedByUserID"));

				return tag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public List<Tag> getAllTags() {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (getAllTags == null) {
				getAllTags = connection.prepareStatement("SELECT * FROM Tag;");
			}

			rs = getAllTags.executeQuery();

			List<Tag> tags = new ArrayList<Tag>();

			while (rs.next()) {
				Tag tag = new Tag();

				tag.setId(rs.getInt("ID"));
				tag.setName(rs.getString("Title"));
				tag.setCreatedTime(rs.getTimestamp("CreatedTime"));
				tag.setCreatedByUserId(rs.getInt("CreatedByUserID"));

				tags.add(tag);
			}

			return tags;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public int addTag(Tag tag) {
		ResultSet rs = null;

		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (addTag == null) {
				addTag = connection.prepareStatement("INSERT INTO Tag (ID, Name, CreatedTime, CreatedByUserID) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			}

			addTag.setNull(1, Types.INTEGER);
			addTag.setString(2, tag.getName());
			addTag.setTimestamp(3, tag.getCreatedTime());
			addTag.setInt(4, tag.getCreatedByUserId());

			int rowAffected = addTag.executeUpdate();

			if (rowAffected == 1) {
				rs = addTag.getGeneratedKeys();

				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return -1;
	}

	@Override
	public int deleteTagById(long id) {
		try {
			if (connection == null) {
				connection = DbUtil.openConnection();
			}
			if (deleteTagById == null) {
				deleteTagById = connection.prepareStatement("DELETE FROM Tag WHERE ID = ?;");
			}

			deleteTagById.setLong(1, id);

			return deleteTagById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	protected void finalize() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (getTagById != null && !getTagById.isClosed()) {
				getTagById.close();
			}
			if (getTagByName != null && !getTagByName.isClosed()) {
				getTagByName.close();
			}
			if (getAllTags != null && !getAllTags.isClosed()) {
				getAllTags.close();
			}
			if (addTag != null && !addTag.isClosed()) {
				addTag.close();
			}
			if (deleteTagById != null && !deleteTagById.isClosed()) {
				deleteTagById.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
