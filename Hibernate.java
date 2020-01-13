/*---------------Main.java---------------
package mypack;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction transaction = null; 
        try { 
            transaction = session.beginTransaction(); 
           Student s1=new Student();
           Phone p1=new Phone();
           p1.setPhoneNumber("923456789");
           p1.setPhoneType("mobile");
           Phone p2=new Phone();
           p2.setPhoneNumber("2654321");
           p2.setPhoneType("resd");
           HashSet<Phone> h=new HashSet<Phone>();
           h.add(p1);
           h.add(p2);
           s1.setStudentName("abc");
           s1.setStudentPhoneNumbers(h);
           session.save(s1);
           transaction.commit(); 
            System.out.println("done with student");  
             }
        catch(Exception ee)
        {
        	System.out.println("in catch\t"+ee);
        }
}
}
--------------------------Phone----------------------
package mypack;

import javax.persistence.*;

@Entity
@Table(name = "PHONE")
public class Phone {
private long phoneId;
private String phoneType;
private String phoneNumber;
public Phone() {
}
public Phone(String phoneType, String phoneNumber) {
this.phoneType = phoneType;
this.phoneNumber = phoneNumber;
}
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "PHONE_ID")
public long getPhoneId() {
return this.phoneId;
}
public void setPhoneId(long phoneId) {
this.phoneId = phoneId;
}
@Column(name = "PHONE_TYPE", nullable = false, length=10)
public String getPhoneType() {
return this.phoneType;
}
public void setPhoneType(String phoneType) {
this.phoneType = phoneType;
}
@Column(name = "PHONE_NUMBER", nullable = false, length=15)
public String getPhoneNumber() {
return this.phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
this.phoneNumber = phoneNumber;
}
}
---------------------------Student.java------------------
package mypack;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student {
private long studentId;
private String studentName;
private Set<Phone> studentPhoneNumbers;
public Student() {
}
public Student(String studentName, Set<Phone> studentPhoneNumbers) {
this.studentName = studentName;
this.studentPhoneNumbers = studentPhoneNumbers;
}
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "STUDENT_ID")
public long getStudentId() {
return this.studentId;
}
public void setStudentId(long studentId) {
this.studentId = studentId;
}
@Column(name = "STUDENT_NAME", nullable = false, length = 100)
public String getStudentName() {
return this.studentName;
}
public void setStudentName(String studentName) {
this.studentName = studentName;
}
@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "sid", referencedColumnName="STUDENT_ID")
public Set<Phone> getStudentPhoneNumbers() {
return this.studentPhoneNumbers;
}
public void setStudentPhoneNumbers(Set<Phone> studentPhoneNumbers) {
this.studentPhoneNumbers = studentPhoneNumbers;
}
}
---------------------------------------------------------------------------------
*/