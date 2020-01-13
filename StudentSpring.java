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