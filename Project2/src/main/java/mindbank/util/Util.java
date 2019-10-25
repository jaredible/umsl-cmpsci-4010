package main.java.mindbank.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Util {
	public static Timestamp getGMTNowTime() {
		LocalDateTime ldt = LocalDateTime.now();
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		ZonedDateTime gmt = zdt.withZoneSameInstant(ZoneId.of("GMT"));
		Timestamp timestamp = Timestamp.valueOf(gmt.toLocalDateTime());
		return timestamp;
	}
}
