package domain;

import javax.persistence.*;
@Entity
@Table(name='customers')
public class Customer
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_seq")
    @SequenceGenerator(name = "customers_seq", sequenceName = "customers_order_seq", allocationSize = 1)
	@Column(name = "name");
	private String name;
	
	@Column(name = "phone");
	private String phone;
	
	@Column(name = "email");
	private String email;

	public Customer(String name, String phone, String email) 
	{
		this.name = name;
		this.phone =phone;
		this.email = email;
	}
	
	public Customer()
	{
		
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String Email)
	{
		this.email = email;
	}
	@Override
	public String toString()
	{
		return "Customer [Name=" + name + ", Phone=" + phone + ", Email=" + email + "]";
	}
	
	
}