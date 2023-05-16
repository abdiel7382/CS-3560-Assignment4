package api;

import domain.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomerDAO 
{
	public static void createCustomer(String phone, String name, String email) 
	{
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Order.class)
								.addAnnotatedClass(Address.class)
								.addAnnotatedClass(Customer.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try
		{
			session.beginTransaction();
			
			Customer tempCustomer = new Customer(phone, email, name);
			
			session.save(tempCustomer);
			
			session.getTransaction().commit();
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
	
	public static Customer createCustomer(String name)
	{
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Order.class)
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Customer.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try
		{
			session.beginTransaction();
			
			Customer tempCustomer = new Customer();
			
			tempCustomer.setName(name);
			
			// need to use the Class name, not table name
			String hql = "FROM Customer WHERE name=:name";
			
			
			@SuppressWarnings("unchecked")
			List<Customer> customers = session.createQuery(hql)
					.setParameter("name", tempCustomer.getName())
					.list();
			
			tempCustomer = (customers.isEmpty()) ? null : customers.get(0);
			
			session.getTransaction().commit();
			
			return tempCustomer;
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
	
	public static Customer readCustomer(String Phone)
	{
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Order.class)
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Customer.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try
		{
			session.beginTransaction();
			
			Customer tempCustomer = session.get(Customer.class, Phone);
			
			return tempCustomer;
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
		
	public static void updateCustomer(Customer customer)
	{
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Order.class)
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Customer.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try
		{
			session.beginTransaction();
			
			Customer tempCustomer = session.get(Customer.class, customer.getPhone());
			
			tempCustomer.setPhone(customer.getPhone());
			tempCustomer.setName(customer.getName());
			tempCustomer.setEmail(customer.getEmail());
			
			session.getTransaction().commit();
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
	
	public static boolean deleteCustomer(Customer customer)
	{
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Order.class)
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Customer.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try
		{
			session.beginTransaction();
			
			// Retrieve the student from the database using their name
			String hql = "FROM Customer WHERE name=:name";
			Customer tempCustomer = (Customer) session.createQuery(hql)
			                                    .setParameter("name", customer.getName())
			                                    .uniqueResult();
			

			// Update the customer object with the correct ID
			customer.setName(tempCustomer.getName());
			
			session.delete(session.get(Customer.class, customer.getPhone()));
			
			session.getTransaction().commit();
			
			return true;
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
}

