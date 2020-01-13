-------------------------------------------------------------------------------------
---------------------Core MVC - ----------------------------------------------------
-------------------------------------Login Bean-----------------------------------
package com.bean;

public class LoginBean {

	String name;
    String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginBean(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
    
    public boolean validate(String name, String password)
    {
    	if(this.name.equals(name) && this.password.equals(password))
    	{
    		System.out.println("in bean ka true");
    		return true;
    	}
    	return false;
    }
}
-----------------------------------------Login Serv-----------------------------------------
package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.LoginBean;

/**
 * Servlet implementation class LoginServ
 */
@WebServlet("/LoginServ")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		
		LoginBean l1=new LoginBean("mohini","123");
		boolean s=l1.validate(name, pass);
		System.out.println(s);
		if(s)
		{
			System.out.println("in true");
			RequestDispatcher rd=  request.getRequestDispatcher("success.jsp");
		request.setAttribute("name", name);
		rd.forward(request, response);
			
		}
		else
		{
			System.out.println("in false");
			response.sendRedirect("fail.jsp");
		}
		
	}

}
----------------------------------------------------Fail.jsp---------------------------------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> incorrect username and password </h1>
<a href="index.jsp">Retry </a>
</body>
</html>
----------------------------------------------index.jsp----------------------------------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="LoginServ" method="post">
<input type="text" name="name" >
<input type="password" name="pass">
<input type="submit"> 
</form>
</body>
</html>
---------------------------------------------------Success.jsp---------------------------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Welcome ${name}
</body>
</html>

---------------------------------------------web.xml----------------------------------------
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
  <display-name>CoreMVCQ2</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
</web-app>