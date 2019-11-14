package edu.umsl.java.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletRequest;

public class Util {

	public static String getIPFromServletRequest(ServletRequest request) {
		String ip = request.getRemoteAddr();

		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
			try {
				InetAddress inetAddress = InetAddress.getLocalHost();
				String ipAddress = inetAddress.getHostAddress();
				ip = ipAddress;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}

		return ip;
	}

}
