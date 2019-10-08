package entities.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.interfaces.PersonOperations;

public abstract class Person implements PersonOperations{
	protected final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	
	private int ID;
	private String name;
	private Date dataJoin;
	private Date birthDate;
	private String cpf;
	private char gender;//M ou F
	private Address address;
	private String telNumber;
	
	List<Person> persons = new ArrayList<Person>();
	public int getID() {
		return ID;
	}
	
	public Person(String name, Date birthDate, String cpf, char gender , Address address) {
		this.name = name;
		this.dataJoin = new Date();
		this.birthDate = birthDate;
		this.ID = this.generateID();
		this.cpf = cpf;
		this.address = address;
		this.gender = gender;
	}
	
	public Person(String name, Date birthDate, String cpf, char gender) {
		this.name = name;
		this.dataJoin = new Date();
		this.birthDate = birthDate;
		this.ID = this.generateID();
		this.cpf = cpf;
		this.gender = gender;
	}
	
	public Person(String name, Date birthDate, String cpf, char gender, String telNUmber) {
		this.name = name;
		this.dataJoin = new Date();
		this.birthDate = birthDate;
		this.ID = this.generateID();
		this.cpf = cpf;
		this.telNumber = telNUmber;
		this.gender = gender;
	}
	
	public Person(String name, Date birthDate, String cpf, char gender , Address address, String telNUmber) {
		this.name = name;
		this.dataJoin = new Date();
		this.birthDate = birthDate;
		this.ID = this.generateID();
		this.cpf = cpf;
		this.address = address;
		this.gender = gender;
		this.telNumber = telNUmber;
	}
	
	public String getTelNumber() {
		return telNumber;
	}
	
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDataJoin() {
		return dataJoin;
	}
	
	public void setDataJoin(Date dataJoin) {
		this.dataJoin = dataJoin;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	protected abstract int generateID();
	
	public void addPerson(Person p) {
		persons.add(p);
	}
	
	@Override
	public abstract String toString();
	
	
}
