/** 
 * OSGi Test Suite Implementation. OSGi Confidential.
 * (C) Copyright Telia Research AB. 2000.
 * This source code is owned by Telia Research AB,
 * and is being distributed to OSGi MEMBERS as
 * MEMBER LICENSED MATERIALS under the terms of section 3.2 of
 * the OSGi MEMBER AGREEMENT. 
 */
package org.osgi.test.cases.http.junit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpTestServlet2 extends javax.servlet.http.HttpServlet {
	/**
	 * 
	 */
	private static final long				serialVersionUID	= 1L;
	private static final String	pagePart1	= "<html><head><title>OSGi - HTTP Service test case</title></head><body><h1>";
	private static final String	pagePart2	= "</body></html>";
	private static Hashtable<String,String>	testCaseMap			= new Hashtable<>();
	static {
		testCaseMap.put("3", "3 Registration of two overlapping servlets</h1>");
		testCaseMap.put("4", "4 Unregistration of overlapping servlet</h1>");
		testCaseMap.put("8", "8 Case sensitivity</h1>");
		testCaseMap.put("62", "62 Verification of ServletContext sharing</h1>");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String testCase = request.getParameter("TestCase");
		if ("3".equals(testCase) || "4".equals(testCase)) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(pagePart1 + testCaseMap.get(testCase)
					+ "<p>Servlet aabb</p>" + pagePart2);
		}
		else
			if ("8".equals(testCase)) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(pagePart1 + testCaseMap.get(testCase)
						+ "<p>Uppercase Servlet</p>" + pagePart2);
			}
			else
				if ("62".equals(testCase)) {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println(pagePart1 + testCaseMap.get(testCase));
					ServletContext SC = getServletConfig().getServletContext();
					String paramName = "param1";
					out.println("<p>Parameter " + paramName + " has value: "
							+ SC.getAttribute(paramName) + "</p>" + pagePart2);
				}
	}
}
