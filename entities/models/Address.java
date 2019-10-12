package entities.models;

public class Address {
	
	private String city;
	private String district;
	private String street;
	private int number;
	private int cep;
	private Integer complement;
	
	public Address(String city, String district, String street, int number,int cep, Integer complement) {
		super();
		this.city = city;
		this.district = district;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.cep = cep;
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
	public Integer getComplement() {
		return complement;
	}
	public void setComplement(Integer complement) {
		this.complement = complement;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", district=" + district + ", street=" + street + ", number=" + number
				+ ", cep=" + cep + ", complement=" + complement + "]";
	}
	
	
}
