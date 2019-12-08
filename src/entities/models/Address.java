package entities.models;

import java.util.Objects;

/**
 * Entidade Endereço
 * @author Alvaro Basilio
 */
public class Address {
	
	private String city;
	private String district;
	private String street;
	private int number;
	private int cep;
	private String complement;
	private Integer id;
	public Address(String city, String district, String street, int number,int cep, String complement) {
		super();
		this.city = city;
		this.district = district;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.cep = cep;
	}
	public Address(String city, String district, String street, int number,int cep, String complement, Integer id) {
		super();
		this.city = city;
		this.district = district;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.cep = cep;
		this.id = id;
	}
	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public Address(String city, String district, String street, int number, int cep) {
		super();
		this.city = city;
		this.district = district;
		this.street = street;
		this.number = number;
		this.cep = cep;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", district=" + district + ", street=" + street + ", number=" + number
				+ ", cep=" + cep + ", complement=" + complement + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return Objects.equals(id, address.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
