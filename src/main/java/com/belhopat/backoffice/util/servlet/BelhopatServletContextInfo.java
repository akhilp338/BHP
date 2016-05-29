package com.belhopat.backoffice.util.servlet;

import javax.servlet.ServletContext;

public class BelhopatServletContextInfo {

    private static String contextPath;
    private static String realPath;
    private static ServletContext context;

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

}
