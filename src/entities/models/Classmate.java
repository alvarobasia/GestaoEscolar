package entities.models;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.enums.Gender;
import entities.exeptions.infoBancoExeption;
import entities.services.ConnectJDCB;


public class Classmate extends Person{
	
    private String registration;
    private static int COUNT;

	static {
		try {
			COUNT = ConnectJDCB.getLestIdClass();
		} catch (entities.exeptions.infoBancoExeption | SQLException infoBancoExeption) {
			infoBancoExeption.printStackTrace();
		}
	}

	;
	
    Course course;
    
    List<Grades> grades = new ArrayList<Grades>();   
	
	
	public Classmate(String name, LocalDate birthDate, String cpf, Gender gender ,Course course, Address address, String telNumber) {
		super(name, birthDate, cpf, gender, address, telNumber);
		this.course = course;
		registrationGenerator();
	}
	
	public Classmate(String name, LocalDate birthDate, String cpf, Gender gender ,Course course, Address address) {
		super(name, birthDate, cpf, gender, address);
		this.course = course;
		registrationGenerator();
	}
	
	public Classmate(String name, LocalDate birthDate, String cpf, Gender gender ,Course course, String telNumber) {
		super(name, birthDate, cpf, gender,telNumber);
		this.course = course;
		registrationGenerator();
	}
	
	public Classmate(String name, LocalDate birthDate, String cpf, Gender gender ,Course course) {
		super(name, birthDate, cpf, gender);
		this.course = course;
		registrationGenerator();
	}
	
	private void registrationGenerator() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getDataJoin().getYear()-2000 + ".");
		if((this.getDataJoin().getMonthValue()+1) <= 6)
			sb.append("01");
		else
			sb.append("02");
		sb.append(".");
		int digitos = Integer.toString(COUNT).length();
		digitos = 4 - digitos;
		for(int i = 0; i < digitos; i++)
			sb.append("0");
		sb.append(COUNT);
		this.registration = sb.toString();
		COUNT++;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	@Override
	public Person removePerson(List<Person> p, int id) {
		return null;
	}

	@Override
	public Integer searchPersonByID(List<? extends Person> list, int id) {
		return null;
	}

	@Override
	public Integer searchPersonByName(List<? extends Person> list, String name) {
		return null;
	}

	@Override
	protected int generateID() {
		return 0;
	}

	@Override
	public Integer searchPersonByCPF(List<? extends Person> list, String cpf) {
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--------Informaçoẽs do Professor----------\n");
		sb.append("Nome: " + this.getName()+ "\n");
		sb.append("Identidade: " + this.getID()+ "\n");
		sb.append("Cpf: " + this.getCpf()+ "\n");
		if(super.getGender() == Gender.valueOf("MASCULINO"))
			sb.append("Gênero: Masculino" + "\n");
		else
			sb.append("Gênero: Feminino" + "\n");
		if(super.getTelNumber() != null)
			sb.append("Telefone: "  + super.getTelNumber() + "\n");
		if(super.getAddress() != null) {
			sb.append("Endereço: \n");
			sb.append("Cidade: " + super.getAddress().getCity() + "\n");
			sb.append("Bairro: " + super.getAddress().getDistrict() + "\n");
			sb.append("Rua: " + super.getAddress().getStreet() + "\n");
			sb.append("Número: " + super.getAddress().getNumber() + "\n");
			if(super.getAddress().getComplement() != null) {
				sb.append("Complemento: " + super.getAddress().getComplement() + "\n");
			}
		}
		sb.append("Curso: " + this.getCourse() + "\n");
		sb.append("Matrícula: "+ this.getRegistration() + "\n");
		sb.append("Data de nascimento: " + super.SDF.format(super.getBirthDate()) + "\n");
		sb.append("Data de inscrição : " + super.SDF.format(super.getDataJoin())  + "\n");
		return sb.toString();
	}

	
}
