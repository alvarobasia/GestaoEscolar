package entities.models;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.enums.Gender;
import entities.interfaces.PersonOperations;

public abstract class Person implements PersonOperations{
	protected final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	
	private int ID;
	private String name;
	private LocalDate dataJoin;
	private LocalDate birthDate;
	private String cpf;
	private Gender sex;
	private Address address;
	private String telNumber;
	
	List<Person> persons = new ArrayList<Person>();
	public int getID() {
		return ID;
	}
	
	public Person(String name, LocalDate birthDate, String cpf, Gender gender , Address address) {
		this.name = name;
		this.dataJoin = LocalDate.now();
		this.birthDate = birthDate;
		this.ID = this.generateID();
		this.cpf = cpf;
		this.address = address;
		this.sex = gender;
	}
	
	public Person(String name, LocalDate birthDate, String cpf, Gender gender) {
		this.name = name;
		this.dataJoin = LocalDate.now();
		this.birthDate = birthDate;
		this.ID = this.generateID();
		this.cpf = cpf;
		this.sex = gender;
	}
	
	public Person(String name, LocalDate birthDate, String cpf, Gender gender, String telNUmber) {
		this.name = name;
		this.dataJoin = LocalDate.now();
		this.birthDate = birthDate;
		this.ID = this.generateID();
		this.cpf = cpf;
		this.telNumber = telNUmber;
		this.sex = gender;
	}
	
	public Person(String name, LocalDate birthDate, String cpf, Gender gender , Address address, String telNUmber) {
		this.name = name;
		this.dataJoin = LocalDate.now();
		this.birthDate = birthDate;
		this.ID = this.generateID();
		this.cpf = cpf;
		this.address = address;
		this.sex = gender;
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
	public Gender getGender() {
		return sex;
	}
	
	public void setGender(Gender gender) {
		this.sex = gender;
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
	
	public LocalDate getDataJoin() {
		return dataJoin;
	}
	
	public void setDataJoin(LocalDate dataJoin) {
		this.dataJoin = dataJoin;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	protected abstract int generateID();
	
	public void addPerson(Person p) {
		persons.add(p);
	}
	
	@Override
	public abstract String toString();
	
	
}
