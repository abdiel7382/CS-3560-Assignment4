package domain;

import javax.persistence.*;
@Entity
@Table(name='orders')
public class Order
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_order_seq", allocationSize = 1)
	@Column(name = "number");
	private String number;
	
	@Column(name = "date");
	private String date;
	
	@Column(name = "item");
	private String item;
	
	@Column(name = "price");
	private double price;
	

	public Order(String number, String date, String item, double price) 
	{
		this.number = number;
		this.date =date;
		this.item = item;
		this.price = price;
	}
	
	public Order()
	{
		
	}
	
	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}
	
	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}
	
	public String getItem()
	{
		return item;
	}
	
	public void setItem(String item)
	{
		this.item = item;
	}
	
	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}
	@Override
	public String toString()
	{
		return "Order [Number=" + number + ", Date=" + date + ", Item=" + item + ", Price=" + price + "]";
	}
	
	
}