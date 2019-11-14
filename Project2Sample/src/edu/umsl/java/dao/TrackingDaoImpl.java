package edu.umsl.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

import edu.umsl.java.model.Tracking;
import edu.umsl.java.util.DbConn;
import edu.umsl.java.util.TrackingType;

public class TrackingDaoImpl implements TrackingDao {

	private Connection connection;
	private PreparedStatement addTracking;
	private PreparedStatement getTrackingById;
	private PreparedStatement getViewCount;

	public TrackingDaoImpl() throws Exception {
		connection = DbConn.openConn();
		addTracking = connection.prepareStatement("INSERT INTO Tracking (ID, TrackingType, IP, UserAgent, CreatedTime, PreviousTrackingID) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
		getTrackingById = connection.prepareStatement("SELECT * FROM Tracking WHERE ID = ?;");
		getViewCount = connection.prepareStatement("SELECT COUNT(*) AS ViewCount FROM Tracking WHERE TrackingType = " + TrackingType.VIEW.getId() + ";");
	}

	@Override
	public int addTracking(Tracking tracking) {
		ResultSet rs = null;
		int resultId = 0;

		try {
			addTracking.setNull(1, Types.INTEGER);
			addTracking.setInt(2, tracking.getTrackingType());
			addTracking.setString(3, tracking.getIp());
			addTracking.setString(4, tracking.getUserAgent());
			addTracking.setTimestamp(5, tracking.getCreatedTime());
			addTracking.setInt(6, tracking.getPreviousTrackingId());
			int rowAffected = addTracking.executeUpdate();
			if (rowAffected == 1) {
				rs = addTracking.getGeneratedKeys();
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
	public Tracking getTrackingById(int id) {
		ResultSet rs = null;

		try {
			getTrackingById.setInt(1, id);
			rs = getTrackingById.executeQuery();
			if (rs.next()) {
				Tracking tracking = new Tracking();
				tracking.setId(rs.getInt("ID"));
				tracking.setTrackingType(rs.getInt("TrackingType"));
				tracking.setIp(rs.getString("IP"));
				tracking.setUserAgent(rs.getString("UserAgent"));
				tracking.setCreatedTime(rs.getTimestamp("CreatedTime"));
				tracking.setPreviousTrackingId(rs.getInt("PreviousTrackingID"));
				return tracking;
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
	public int getViewCount() {
		ResultSet rs = null;

		try {
			rs = getViewCount.executeQuery();
			if (rs.next()) {
				return rs.getInt("ViewCount");
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

		return 0;
	}

	protected void finalize() {
		try {
			if (addTracking != null && !addTracking.isClosed()) {
				addTracking.close();
			}
			if (getTrackingById != null && !getTrackingById.isClosed()) {
				getTrackingById.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
