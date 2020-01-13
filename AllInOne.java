/*----------------Stuudent for Hibernate--------------------
package hibernateQ3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	
	int id;
	String name;
	int marks;
	
	

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student( String name, int marks) {
		super();
		
		this.name = name;
		this.marks = marks;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", marks=" + marks + "]";
	}
	

}
------------------------------Studenttest--------------------------
package hibernateQ3;

import java.util.Scanner;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class StudentTest {

	public static void main(String[] args) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
			Scanner sc=new Scanner(System.in);
			/*int cnt=0;
			while(cnt<4)
			{
				System.out.println("enter name");
				String name=sc.next();
				System.out.println("enter marks");
				int marks=sc.nextInt();
				Student s1=new Student(name, marks);
				session.save(s1);
				cnt++;
			}
			tx.commit();*/
			
			Query query=session.createQuery("from Student");
			List mylist=query.list();
			System.out.println(mylist);
			tx=session.beginTransaction();
			
		}
		catch (Exception e) 
		{
			System.out.println(e);
		
		}

	}

}
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

------------------------------------------------------------------------------------------------------------
-----------------------------------Student Spring-------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
------------------------------------StudentDAOImpl------------------------------------------------------------
package mypack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class StudentDAOImpl implements StudentDAO 
{
	@Autowired
	private HibernateTemplate template;
	
	@Override
	public void add(Student ref) 
	{
		template.save(ref);

	}

}
----------------------------------------Student DAO-------------------------------------------------------
package mypack;

public interface StudentDAO 
{
	void add(Student ref);
}
-------------------------------------------Student Controller-----------------------------------------------
package mypack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController 
{
	@Autowired
	StudentDAO sdao;
	@GetMapping("new_student")
	public ModelAndView before()
	{
		Student s=new Student();
		return new ModelAndView("stud","mystudent",s);
	}
	@PostMapping("new_student")
	public String afterSubmit(Student student)
	{
		sdao.add(student);
		return "success";
	}
}

--------------------------------------------Student Java---------------------------------------------------------
package mypack;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stud")
public class Student 
{
	private int rollno;
	private String name;
	private int age;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	@Column
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	@Column
	public void setAge(int age) {
		this.age = age;
	}
	
}

------------------------------------------------------Stud.jsp----------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Entry</title>
</head>
<body>
<h1>Add New Student</h1>
<form:form method="post" modelAttribute="mystudent">
Student Name:<br>
<form:input path="name"/> <br><br>
Age:<br><br>
<form:input path="age"/><br><br>
<input type=submit value="Add_Student"/>
</form:form>	
</body>
</html>
--------------------------------------------------------Success.jsp------------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Entry</title>
</head>
<body>
<h1>Add New Student</h1>
<form:form method="post" modelAttribute="mystudent">
Student Name:<br>
<form:input path="name"/> <br><br>
Age:<br><br>
<form:input path="age"/><br><br>
<input type=submit value="Add_Student"/>
</form:form>	
</body>
</html>
------------------------------------------------------------dispatcher servlet---------------------------------------------
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	 http://www.springframework.org/schema/beans/spring-beans.xsd
	 http://www.springframework.org/schema/context
	 http://www.springframework.org/schema/context/spring-context.xsd
	 http://www.springframework.org/schema/mvc
	 http://www.springframework.org/schema/mvc/spring-mvc.xsd 
	 http://www.springframework.org/schema/tx
	http://www.springframework.org/schema/tx/spring-tx.xsd">
<context:component-scan base-package="mypack"/>
<tx:annotation-driven transaction-manager="transactionManager" />
 <bean id="dataSource"
        class="org.springframework.jdbc.datasource.DriverManagerDataSource">

        <property name="driverClassName" value="com.mysql.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://localhost:3306/mydb" />
        <property name="username" value="root" />
        <property name="password" value="root" />
    </bean>

    <!-- Hibernate template configuration -->
   
       <bean id="sessionFactory"
        class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">

        <property name="dataSource" ref="dataSource" />
        <property name="hibernateProperties">
            <props>
                <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
                <prop key="hibernate.show_sql">true</prop>
                <prop key="hibernate.hbm2ddl.auto">update</prop>

            </props>
        </property>
        <property name="annotatedClasses">
            <list>
                <value>mypack.Student</value>
             </list>
        </property>
   </bean>
    <bean id="transactionManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager"> 
<property name="sessionFactory" ref="sessionFactory" /> 
</bean> 
	    <bean id="template" class="org.springframework.orm.hibernate5.HibernateTemplate">  
	    <property name="sessionFactory" ref="sessionFactory"></property>  
	    </bean>  

<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
	<property name="prefix" value="/"/>
	<property name="suffix" value=".jsp"/>
</bean>
</beans>
----------------------------------------------------------web xml----------------------------------------------------------
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
   <servlet>
   	<servlet-name>studentdispatcher</servlet-name>
   	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
   	<load-on-startup>1</load-on-startup>
   </servlet>
   <servlet-mapping>
   	<servlet-name>studentdispatcher</servlet-name>
   	<url-pattern>*.do</url-pattern>
   </servlet-mapping>
   <welcome-file-list>
   	<welcome-file>/new_student.do</welcome-file>
   </welcome-file-list>
   <session-config>
   	<session-timeout>30</session-timeout>
   </session-config>
</web-app>
---------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------Servlet jsp----------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------Servlet.java--------------------------------------------------------------------------


package com.vita;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
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
		
		String name= request.getParameter("name");
		String pass=request.getParameter("pass");
		
		if(name.equals("mohini") && pass.equals("123"))
		{
			RequestDispatcher rd=  request.getRequestDispatcher("Success.jsp");
			request.setAttribute("name", name);
			rd.forward(request, response);
		}
		
	}

}
-------------------------------------------------------index.jsp-----------------------------------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="servlet" method="post">
<input type="text" name="name" >
<input type="password" name="pass">
<input type="submit"> 
</form>
</body>
</html>
--------------------------------------------------------------success.jsp-----------------------------------------------------------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
----------------------------------------------------------------web.xml-----------------------------------------------------------------------------------------------

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
  <display-name>Servletjsp</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
</web-app>
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



*/