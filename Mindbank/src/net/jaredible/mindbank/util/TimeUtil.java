package net.jaredible.mindbank.util;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtil {

	public static int getNumSecondsInDay() {
		return 60 * 60 * 24;
	}

	public static Date getNowTime() {
		return new Timestamp(new Date().getTime());
	}

}
