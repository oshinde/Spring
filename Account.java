/*---------------Account Interface-------------------
package mypack;
public interface Account
{
   public void deposit();
}

----------------------Current Account---------------
package mypack;

import org.springframework.stereotype.Component;

@Component(value="caccount")
public class CurrentAccountImpl implements Account 
{
	@Override
	public void deposit() 
	{
        System.out.println("inside current deposit");
	}

}
-------------------------Saving Account----------------
package mypack;

import org.springframework.stereotype.Component;

@Component(value="saccount")
public class SavingAccountImpl implements Account 
{
	@Override
	public void deposit() 
	{
		System.out.println("inside saving deposit");
	}

}
---------------------------Accountbean---------------
package mypack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value="accountbean")
public class AccountBean
{
	@Autowired
	@Qualifier("saccount")
    private Account account;
public AccountBean()
    {
        System.out.println("in AccountBean no-arg constructor");
    }
    public void setAccount(Account account) 
    {
        System.out.println("inside setter method");
        this.account = account;
    }  
    public void makeDeposit()
    {
        account.deposit();
    }
}

---------------------------Main Java-------------------
package mypack;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main 
{
	public static void main(String[] args) 
	{
        /* Load the welcomeUser.xml to classpath  */
        ClassPathXmlApplicationContext appContext=new ClassPathXmlApplicationContext("account.xml");
        System.out.println("Classpath loaded");
        AccountBean ab=(AccountBean)appContext.getBean("accountbean");
        ab.makeDeposit();
        appContext.close();
	}

}
--------------------------------------Account xml--------------------
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	 http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
	 http://www.springframework.org/schema/context
	 http://www.springframework.org/schema/context/spring-context-4.0.xsd">
    <context:component-scan base-package="mypack"/>
</beans>

*/