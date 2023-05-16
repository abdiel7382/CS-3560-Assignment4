package api;

import domain.*;

import java.lang.Thread.State;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OrderDAO 
{
	public static void createOrder(String number, String date, String item, double price) 
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
			
			Order tempOrder = new Order(number, date, item, price);
			
			session.save(tempOrder);
			
			session.getTransaction().commit();
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
	
	public static Order readOrder(String number)
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
			
			Order tempOrder = new Order();
			
			tempOrder.setNumber(number);
			
			// need to use the Class name, not table name
			String hql = "FROM Address WHERE name=:name";
			
			
			@SuppressWarnings("unchecked")
			List<Order> orders = session.createQuery(hql)
					.setParameter("number", tempOrder.getNumber())
					.list();
			
			tempOrder = (orders.isEmpty()) ? null : orders.get(0);
			
			session.getTransaction().commit();
			
			return tempOrder;
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
	
	public static Order readOrderByNumber(String Number)
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
			
			Order tempOrder = session.get(Order.class, Number);
			
			return tempOrder;
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
		
	public static void updateOrder(Order order)
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
			
			Order tempOrder = session.get(Order.class, order.getNumber()));
			
			tempOrder.setNumber(order.getNumber());
			tempOrder.setDate(order.getDate());
			tempOrder.setItem(order.getItem());
			tempOrder.setPrice(order.getPrice());

			
			
			session.getTransaction().commit();
		}
		
		finally
		{
			session.close();
			factory.close();
		}
	}
	
	public static boolean deleteOrder(Order order)
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
			String hql = "FROM Order WHERE name=:name";
			Order tempOrder = (Order) session.createQuery(hql)
			                                    .setParameter("number", order.getNumber())
			                                    .uniqueResult();
			

			// Update the customer object with the correct state
			order.setNumber(tempOrder.getNumber());
			
			session.delete(session.get(Order.class, order.getNumber()));
			
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
