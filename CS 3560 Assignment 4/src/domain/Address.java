package domain;

import javax.persistence.*;
@Entity
@Table(name='addresses')
public class Address
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_order_seq", allocationSize = 1)
	@Column(name = "street");
	private String street;
	
	@Column(name = "city");
	private String city;
	
	@Column(name = "state");
	private String state;
	
	@Column(name = "zip_code");
	private String zip_code;
	

	public Address(String street, String city, String state, String zip_code) 
	{
		this.street = street;
		this.city =city;
		this.state = state;
		this.zip_code = zip_code;
	}
	
	public Address()
	{
		
	}
	
	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}
	
	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getZip_Code()
	{
		return zip_code;
	}

	public void setZip_Code(String zip_code)
	{
		this.zip_code = zip_code;
	}
	@Override
	public String toString()
	{
		return "Address [Street=" + street + ", City=" + city + ", State=" + state + ", Zip_Code=" + zip_code + "]";
	}
	
	
}