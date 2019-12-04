package net.jaredible.mindbank.dao.tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.jaredible.mindbank.model.tag.Tag;
import net.jaredible.mindbank.util.DbUtil;

public class TagDaoImpl implements TagDao {

	private Connection connection;
	private PreparedStatement getTagById;
	private PreparedStatement getTagByName;
	private PreparedStatement getAllTags;
	private PreparedStatement addTag;

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
				return extractTagFromResultSet(rs);
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
				return extractTagFromResultSet(rs);
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
				tags.add(extractTagFromResultSet(rs));
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
	public long addTag(Tag tag) {
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
			addTag.setLong(4, tag.getCreatedByUserId());

			int rowAffected = addTag.executeUpdate();

			if (rowAffected == 1) {
				rs = addTag.getGeneratedKeys();

				if (rs.next()) {
					return rs.getLong(1);
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

	private Tag extractTagFromResultSet(ResultSet rs) throws SQLException {
		Tag tag = new Tag();

		tag.setId(rs.getInt("ID"));
		tag.setName(rs.getString("Name"));
		tag.setCreatedTime(rs.getTimestamp("CreatedTime"));
		tag.setCreatedByUserId(rs.getInt("CreatedByUserID"));

		return tag;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
