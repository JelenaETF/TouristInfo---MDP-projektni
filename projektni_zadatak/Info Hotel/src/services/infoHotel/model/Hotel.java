package services.infoHotel.model;

import java.io.Serializable;

public class Hotel implements Serializable, Comparable<Hotel>{

	private String name;
	private String address;
	private String telephone;
	private int id;
	private String category;
	
	public Hotel() {}

	public Hotel(String name, String address, String telephone, String category) {
		super();
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getId() {
		return id;
	}
   
	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int compareTo(Hotel other) {
		return this.getId() - other.getId();
	}
}
