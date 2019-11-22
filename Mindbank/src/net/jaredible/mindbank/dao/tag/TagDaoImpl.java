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
	private PreparedStatement getAllTags;
	private PreparedStatement addTag;
	private PreparedStatement deleteTagById;
	private PreparedStatement getTagExistsByName;

	public TagDaoImpl() throws Exception {
		connection = DbUtil.openConnection();
		getTagById = connection.prepareStatement("SELECT * FROM Tag WHERE ID = ?;");
		getAllTags = connection.prepareStatement("SELECT * FROM Tag;");
		addTag = connection.prepareStatement("INSERT INTO Tag (ID, Name, CreatedTime, CreatedByUserID) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		deleteTagById = connection.prepareStatement("DELETE FROM Tag WHERE ID = ?;");
		getTagExistsByName = connection.prepareStatement("SELECT * FROM Tag WHERE Name = ?;");
	}

	@Override
	public Tag getTagById(long id) {
		ResultSet rs = null;

		try {
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
	public List<Tag> getAllTags() {
		try {
			ResultSet rs = getAllTags.executeQuery();

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
		}

		return null;
	}

	@Override
	public int addTag(Tag tag) {
		ResultSet rs = null;

		try {
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
			deleteTagById.setLong(1, id);

			return deleteTagById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public boolean getTagExistsByName(String name) {
		try {
			getTagExistsByName.setString(1, name);

			ResultSet rs = getTagExistsByName.executeQuery();

			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
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
			if (getAllTags != null && !getAllTags.isClosed()) {
				getAllTags.close();
			}
			if (addTag != null && !addTag.isClosed()) {
				addTag.close();
			}
			if (deleteTagById != null && !deleteTagById.isClosed()) {
				deleteTagById.close();
			}
			if (getTagExistsByName != null && !getTagExistsByName.isClosed()) {
				getTagExistsByName.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
