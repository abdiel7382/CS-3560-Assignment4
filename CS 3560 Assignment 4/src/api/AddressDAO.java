package api;

import domain.*;

import java.lang.Thread.State;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddressDAO 
{
	public static void createAddress(String street, String city, String state, String zip_code) 
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
			
			Address tempAddress = new Address(street, city, state, zip_code );
			
			session.save(tempAddress);
			
			session.getTransaction().commit();
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
	
	public static Address readAddress(String State)
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
			
			Address tempAddress = new Address();
			
			tempAddress.setState(State);
			
			// need to use the Class name, not table name
			String hql = "FROM Address WHERE name=:name";
			
			
			@SuppressWarnings("unchecked")
			List<Address> addresses = session.createQuery(hql)
					.setParameter("name", tempAddress.getState())
					.list();
			
			tempAddress = (addresses.isEmpty()) ? null : addresses.get(0);
			
			session.getTransaction().commit();
			
			return tempAddress;
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
	
	public static Address readCustomerByState(String State)
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
			
			Address tempAddress = session.get(Address.class, State);
			
			return tempAddress;
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
		
	public static void updateAddress(Address address)
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
			
			Address tempAddress = session.get(Address.class, address.getState());
			
			tempAddress.setStreet(address.getStreet());
			tempAddress.setCity(address.getCity());
			tempAddress.setState(address.getState());
			tempAddress.setZip_Code(address.getZip_Code());

			
			
			session.getTransaction().commit();
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
	
	public static boolean deleteAddress(Address address)
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
			Address tempAddress = (Address) session.createQuery(hql)
			                                    .setParameter("name", address.getState())
			                                    .uniqueResult();
			

			// Update the customer object with the correct state
			address.setState(tempAddress.getState());
			
			session.delete(session.get(Customer.class, address.getState()));
			
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
