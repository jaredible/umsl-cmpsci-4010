package edu.umsl.java.dao.tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import edu.umsl.java.model.Tag;
import edu.umsl.java.util.DbUtil;

public class TagDaoImpl implements TagDao {

	private Connection connection;
	private PreparedStatement addTag;
	private PreparedStatement getTags;
	private PreparedStatement getTagIdExists;
	private PreparedStatement getNameExists;
	private PreparedStatement getTagById;
	private PreparedStatement updateTag;
	private PreparedStatement deleteTagById;

	public TagDaoImpl() throws Exception {
		connection = DbUtil.openConnection();
		addTag = connection.prepareStatement("INSERT INTO Tag (ID, Name, CreatedTime, Edited, TrackingID) VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		getTags = connection.prepareStatement("SELECT * FROM Tag ORDER BY CreatedTime DESC;");
		getTagIdExists = connection.prepareStatement("SELECT * FROM Tag WHERE ID = ?;");
		getNameExists = connection.prepareStatement("SELECT * FROM Tag WHERE Name = ?;");
		getTagById = connection.prepareStatement("SELECT * FROM Tag WHERE ID = ?;");
		updateTag = connection.prepareStatement("UPDATE Tag SET Name = ?, Edited = TRUE, TrackingID = ? WHERE ID = ?;");
		deleteTagById = connection.prepareStatement("DELETE FROM Tag WHERE ID = ?;");
	}

	@Override
	public int addTag(Tag tag) {
		ResultSet rs = null;
		int resultId = 0;

		try {
			addTag.setNull(1, Types.INTEGER);
			addTag.setString(2, tag.getName());
			addTag.setTimestamp(3, tag.getCreatedTime());
			addTag.setBoolean(4, tag.isEdited());
			addTag.setInt(5, tag.getTrackingId());
			int rowAffected = addTag.executeUpdate();
			if (rowAffected == 1) {
				rs = addTag.getGeneratedKeys();
				if (rs.next()) {
					resultId = rs.getInt(1);
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

		return resultId;
	}

	@Override
	public List<Tag> getTags() {
		ResultSet rs = null;

		try {
			rs = getTags.executeQuery();
			List<Tag> tags = new ArrayList<Tag>();
			while (rs.next()) {
				Tag tag = new Tag();
				tag.setId(rs.getInt("ID"));
				tag.setName(rs.getString("Name"));
				tag.setCreatedTime(rs.getTimestamp("CreatedTime"));
				tag.setEdited(rs.getBoolean("Edited"));
				tag.setTrackingId(rs.getInt("TrackingID"));
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
	public boolean getTagIdExists(int id) {
		ResultSet rs = null;

		try {
			getTagIdExists.setInt(1, id);
			rs = getTagIdExists.executeQuery();
			if (rs.next()) {
				return true;
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

		return false;
	}

	@Override
	public boolean getNameExists(String name) {
		ResultSet rs = null;

		try {
			getNameExists.setString(1, name);
			rs = getNameExists.executeQuery();
			if (rs.next()) {
				return true;
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

		return false;
	}

	@Override
	public Tag getTagById(int id) {
		ResultSet rs = null;

		try {
			getTagById.setInt(1, id);
			rs = getTagById.executeQuery();
			if (rs.next()) {
				Tag tag = new Tag();
				tag.setId(rs.getInt("ID"));
				tag.setName(rs.getString("Name"));
				tag.setCreatedTime(rs.getTimestamp("CreatedTime"));
				tag.setEdited(rs.getBoolean("Edited"));
				tag.setTrackingId(rs.getInt("TrackingID"));
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
	public void updateTag(Tag tag) {
		try {
			updateTag.setString(1, tag.getName());
			updateTag.setInt(2, tag.getTrackingId());
			updateTag.setInt(3, tag.getId());
			updateTag.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTagById(int id) {
		try {
			deleteTagById.setInt(1, id);
			deleteTagById.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (addTag != null && !addTag.isClosed()) {
				addTag.close();
			}
			if (getTags != null && !getTags.isClosed()) {
				getTags.close();
			}
			if (getTagIdExists != null && !getTagIdExists.isClosed()) {
				getTagIdExists.close();
			}
			if (getTagById != null && !getTagById.isClosed()) {
				getTagById.close();
			}
			if (updateTag != null && !updateTag.isClosed()) {
				updateTag.close();
			}
			if (deleteTagById != null && !deleteTagById.isClosed()) {
				deleteTagById.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
