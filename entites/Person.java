package entites;

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
	
	List<Person> persons = new ArrayList<Person>();
	public int getID() {
		return ID;
	}
	public Person(String name, Date birthDate, String cpf) {
		this.name = name;
		this.dataJoin = new Date();
		this.birthDate = birthDate;
		this.ID = this.generateID();
		this.cpf = cpf;
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
