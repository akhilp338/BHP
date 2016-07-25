package com.belhopat.backoffice.util.servlet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;

public class BelhopatServletContextInfo {

    private static String contextPath;
    private static String realPath;
    private static ServletContext context;
    private static String hostname;
    private static String servletHostname;
    private static String portNo = "8080";
    private static String deployURL ;

	public static String getRealPath() {
        return realPath;
    }

	public static void setRealPath( String realPath ) {
        BelhopatServletContextInfo.realPath = realPath;
    }

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath( String contextPath ) {
        BelhopatServletContextInfo.contextPath = contextPath;
    }

	public static ServletContext getContext() {
		return context;
	}

	public static void setContext(ServletContext context) {
		BelhopatServletContextInfo.context = context;
	}

	public static String getHostname() {
		return hostname;
	}

	public static void setHostname( String hostname ) throws UnknownHostException {
		byte[] ipAddr = new byte[] { 127, 0, 0, 1 };
	    InetAddress addr = InetAddress.getByAddress(ipAddr);
	    String hostnameCanonical = addr.getCanonicalHostName();
		BelhopatServletContextInfo.hostname = hostnameCanonical;
	}

	public static String getServletHostname() {
		return servletHostname;
	}

	public static void setServletHostname(String servletHostname) {
		BelhopatServletContextInfo.servletHostname = servletHostname;
	}

	public static String getPortNo() {
		return portNo;
	}

	public static void setPortNo(String portNo) {
		BelhopatServletContextInfo.portNo = portNo;
	}

	public static String getDeployURL() {
		return deployURL;
	}

	public static void setDeployURL(String deployURL) {
		BelhopatServletContextInfo.deployURL = "http://" + BelhopatServletContextInfo.getHostname() + ":"
				+ BelhopatServletContextInfo.getPortNo() + BelhopatServletContextInfo.getContext().getContextPath();
	}
	
}
