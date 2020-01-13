/*-----------------Person.java--------------
package mypack;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person implements java.io.Serializable {

	private int prnno;
	private String name;
	private int age;

	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prnno")
	public int getPrnno() {
		return this.prnno;
	}

	@Override
	public String toString() {
		return "Person [prnno=" + prnno + ", name=" + name + ", age=" + age
				+ "]";
	}

	public void setPrnno(int prnno) {
		this.prnno = prnno;
	}
	@Column(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="age")
	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
------------------------------PersonTest.java---------------------------------

package mypack;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonTest 
{
	public static void main(String[] args) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction tx = null; 
		try
		{
			tx=session.beginTransaction();
			Scanner sc=new Scanner(System.in);
			int cnt=0;
			while(cnt<4)
			{
			System.out.println("Enter name");	
			String name=sc.next();
			System.out.println("Enter age");
			int age=sc.nextInt();
			Person p1=new Person(name,age);
			session.save(p1);
			cnt++;
			}
			tx.commit();
			
			
			Query query=session.createQuery("from Person");
			List mylist1=query.list();
			
			System.out.println(mylist1);
			tx=session.beginTransaction();
			query=session.createQuery("update Person p set p.name='Amar'");
			int k=query.executeUpdate();
			tx.commit();
			System.out.println("Records updated\t"+k);
									
			query=session.createQuery("from Person");
			mylist1=query.list();
		
			System.out.println(mylist1);
			tx=session.beginTransaction();
			query=session.createQuery("update Person p set p.name=:str");
			query.setString("str","vishal");
			k=query.executeUpdate();
			tx.commit();
			System.out.println("Records updated\t"+k);
				
			
			query=session.createQuery("from Person");
			mylist1=query.list();
			
			System.out.println(mylist1);
			tx=session.beginTransaction();
			query=session.createQuery("update Person p set p.name=:str1 where p.age > :j");
			query.setString("str1","varun");
			query.setString("j","30");
			k=query.executeUpdate();
			tx.commit();
			System.out.println("Records updated\t"+k);
			
			
			query=session.createQuery("from Person");
			mylist1=query.list();
			
			System.out.println(mylist1);
tx=session.beginTransaction();
			query=session.createQuery("update Person p set p.name=? where p.age <?");
			query.setString(0,"sanjay");
			query.setString(1,"18");
			k=query.executeUpdate();
			tx.commit();
			System.out.println("Records updated\t"+k);
			
			
			query=session.createQuery("from Person");
			mylist1=query.list();
			
			System.out.println(mylist1);
			session.close();
			System.out.println("Done with Person");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

---------------------------------------------------------------------------------

query=session.createQuery("from Person c order by c.age desc");
			mylist1=query.list();
			
			for(int i=0;i<mylist1.size();i++)
			{
				System.out.println(mylist1.get(i));
			}

System.out.println("...................................");

			query=session.createQuery("from Person c order by c.age desc, c.name asc");
			mylist1=query.list();
			
			for(int i=0;i<mylist1.size();i++)
			{
				System.out.println(mylist1.get(i));
			}
			session.close();
			System.out.println("Done with Person");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
-----------------------------------------------------------------------------------
package mypack;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonTest 
{
	public static void main(String[] args) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction tx = null; 
		try
		{
			tx=session.beginTransaction();
			Scanner sc=new Scanner(System.in);
			int cnt=0;
			while(cnt<4)
			{
			System.out.println("Enter name");	
			String name=sc.next();
			System.out.println("Enter age");
			int age=sc.nextInt();
			Person p1=new Person(name,age);
			session.save(p1);
			cnt++;
			}
			tx.commit();
			
			
			Query query=session.createQuery("select c.name,c.age from Person c where c.age>30");
			List mylist1=query.list();
			
			for(int i=0;i<mylist1.size();i++)
			{
				Object arr[]=(Object[])mylist1.get(i);
				for(int j=0;j<arr.length;j++)
				{
					System.out.print(arr[j]+"\t");
				}
				System.out.println();
			}
System.out.println(".......................");

		
			query=session.createQuery("select c.name,c.age from Person c where c.name like '%a%' and c.age between 25 and 35");
			mylist1=query.list();
			
			for(int i=0;i<mylist1.size();i++)
			{
				Object arr[]=(Object[])mylist1.get(i);
				for(int j=0;j<arr.length;j++)
				{
					System.out.print(arr[j]+"\t");
				}
				System.out.println();
			}

System.out.println(".......................");

		
			query=session.createQuery("select c.name,c.age from Person c where c.name in ('abc','pqr')");
			mylist1=query.list();
			
			for(int i=0;i<mylist1.size();i++)
			{
				Object arr[]=(Object[])mylist1.get(i);
				for(int j=0;j<arr.length;j++)
				{
					System.out.print(arr[j]+"\t");
				}
				System.out.println();
			}

System.out.println(".......................");

		
			query=session.createQuery("select c.name,c.age from Person c where c.id=3)");
			mylist1=query.list();
			
			for(int i=0;i<mylist1.size();i++)
			{
			Object arr[]=(Object[])mylist1.get(i);
								System.out.println(arr[0]+"\t"+arr[1]);
			}

			session.close();
			System.out.println("Done with Person");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}







*/
