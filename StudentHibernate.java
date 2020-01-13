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