package edu.umsl.java.dao;

import edu.umsl.java.model.Tracking;

public interface TrackingDao {

	public int addTracking(Tracking t);

	public Tracking getTrackingById(int i);

	public int getViewCount();

}
