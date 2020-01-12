/*---------------Book.java-------------
package mypack;
public class Book 
{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+bookName+"   "+price+"]";
	}
	private String bookName;
	private long price;

	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
}
--------------------bookdispatcher-servlet xml----------------
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	 http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
	 http://www.springframework.org/schema/context
	 http://www.springframework.org/schema/context/spring-context-4.0.xsd">
<context:component-scan base-package="mypack"/>
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
	<property name="prefix" value="/"/>
	<property name="suffix" value=".jsp"/>
</bean>
</beans>
---------------------------bookNew.jsp----------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BookShop</title>
</head>
<body>
<h1>Add New Book</h1>
<form:form method="post" modelAttribute="mybook">
Book Name<br>
<form:input path="bookName"/> <br><br>
Price<br><br>
<form:input path="price"/><br><br>
<input type=submit value="Submit"/>
</form:form>	
</body>
</html>
--------------------------------BookNewController.java-----------------------
package mypack;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class BookNewController
{
	@GetMapping("book")
	public ModelAndView before()
	{
 		Book defaultBook=new Book();
	
		return new ModelAndView("bookNew","mybook",defaultBook);
	}
	
	@PostMapping("book")
	public String afterSubmit(
		@ModelAttribute("mb") Book book,HttpSession session) 
	{
		//return form success view
		System.out.println("inside afterSubmit\t"+book);
session.setAttribute("val","1000");
		return "success";
	}
}
----------------------------------success.jsp-----------------------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
welcome to success jsp <br>
<%=request.getAttribute("mb") %>
<br>
The val from session scope is    ${sessionScope.val}
<br><br>
<h1>Book selected</h1>
	<table border="1">
	<tr>
		<th align="left">Book Name</th>
		<th align="left">Price</th>
	</tr>
		<tr>
			<td> ${mb.bookName}</td>
			<td> ${mb.price}</td>
		</tr>
	</table>
	<br><br><br>
	<a href="new_book.do">Add More Books</a>
</body>
</html>
-------------------------------------web.xml---------------------------
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
welcome to success jsp <br>
<%=request.getAttribute("mb") %>
<br>
The val from session scope is    ${sessionScope.val}
<br><br>
<h1>Book selected</h1>
	<table border="1">
	<tr>
		<th align="left">Book Name</th>
		<th align="left">Price</th>
	</tr>
		<tr>
			<td> ${mb.bookName}</td>
			<td> ${mb.price}</td>
		</tr>
	</table>
	<br><br><br>
	<a href="new_book.do">Add More Books</a>
</body>
</html>
--------------------------------------------------------------------------------
*/