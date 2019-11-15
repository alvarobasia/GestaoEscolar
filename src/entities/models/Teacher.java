package entities.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.enums.Gender;

public class Teacher extends Person{
	private static int ID_CONT = 1;
	private Double salary;
	private int teacherID;

	Set<Supplies> provideSupplies = new HashSet<Supplies>();
	
	public Teacher(String name, LocalDate birthDate, Double salary, String cpf, Gender gender, Address address, String telNumber ) {
		super(name, birthDate, cpf, gender, address, telNumber);
		this.salary = salary;
		this.teacherID = ID_CONT;
		ID_CONT++;
	}
	
	public Teacher(String name, LocalDate birthDate, String cpf, Gender gender, Address address, String telNumber ) {
		super(name, birthDate, cpf, gender, address, telNumber);
		this.teacherID = ID_CONT;
		ID_CONT++;
	}
	public Teacher(String name, LocalDate birthDate, Double salary, String cpf, Gender gender, Address address) {
		super(name, birthDate, cpf, gender, address);
		this.salary = salary;
		this.teacherID = ID_CONT;
		ID_CONT++;
	}
	public Teacher(String name, LocalDate birthDate, String cpf, Gender gender, Address address) {
		super(name, birthDate, cpf, gender, address);
		this.teacherID = ID_CONT;
		ID_CONT++;
	}
	
	public Teacher(String name, LocalDate birthDate, Double salary, String cpf, Gender gender, String telNumber ) {
		super(name, birthDate, cpf, gender, telNumber);
		this.salary = salary;
		this.teacherID = ID_CONT;
		ID_CONT++;
	}
	
	public Teacher(String name, LocalDate birthDate, String cpf, Gender gender, String telNumber ) {
		super(name, birthDate, cpf, gender, telNumber);
		this.teacherID = ID_CONT;
		ID_CONT++;
	}
	public Teacher(String name, LocalDate birthDate, Double salary, String cpf, Gender gender) {
		super(name, birthDate, cpf, gender);
		this.salary = salary;
		this.teacherID = ID_CONT;
		ID_CONT++;
	}
	
	public Teacher(String name, LocalDate birthDate, String cpf, Gender gender) {
		super(name, birthDate, cpf, gender);
		this.teacherID = ID_CONT;
		ID_CONT++;
	}
	public static int getID_CONT() {
		return ID_CONT;
	}

	public static void setID_CONT(int iD_CONT) {
		ID_CONT = iD_CONT;
	}
	
	
	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public Double getSalary() {
		return this.salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public void addSupplie(Supplies supplies) {
		provideSupplies.add(supplies);
	}
	
	public Supplies removeCourse(Supplies supplies) {
		Supplies removed = (Supplies) provideSupplies.stream().filter(x -> x.getSupplieID().equals(supplies.getSupplieID()));
		provideSupplies.remove(removed);
		return removed;
	}

	@Override
	protected int generateID() {
		int finalID = ID_CONT;
		ID_CONT++;
		return finalID;
	}

	@Override
	public Person removePerson(List<Person> p, int id) {
		int result = searchPersonByID(p, id);
		Person removed  = p.remove(result);
		return removed;
	}

	@Override
	public Integer searchPersonByID(List<? extends Person> list, int ID) {
		int c = 0 ;
		for(Person  p : list) {
			if(p.getID() == ID) {
				return c;
			}
			c++;
		}
		return null;
	}

	@Override
	public Integer searchPersonByName(List<? extends Person> list, String name) {
		int c = 0 ;
		for(Person  p : list) {
			if(p.getName() == name) {
				return c;
			}
			c++;
		}
		return null;
	}
	
	@Override
	
	public Integer searchPersonByCPF(List<? extends Person> list, String cpf) {
		int c = 0 ;
		for(Person  p : list) {
			if(p.getCpf() == cpf) {
				return c;
			}
			c++;
		}
		return null;
	}

	@Override
	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("--------Informaçoẽs do Professor----------\n");
//		sb.append("Nome: " + this.getName()+ "\n");
//		sb.append("Identidade: " + this.getID()+ "\n");
//		if(super.getGender() == Gender.valueOf("MASCULINO"))
//			sb.append("Gênero: Masculino" + "\n");
//		else
//			sb.append("Gênero: Feminino" + "\n");
//		if(super.getTelNumber() != null)
//			sb.append(super.getTelNumber() + "\n");
//		if(super.getAddress() != null) {
//			sb.append("Endereço: \n");
//			sb.append("Cidade: " + super.getAddress().getCity());
//			sb.append("Bairro: " + super.getAddress().getDistrict());
//			sb.append("Rua: " + super.getAddress().getStreet());
//			sb.append("Número: " + super.getAddress().getNumber());
//			if(super.getAddress().getComplement() != null) {
//				sb.append("Complemento: " + super.getAddress().getComplement());
//			}
//		}
//		if(this.salary != null)
//			sb.append("Salário "+ this.getSalary() + "\n");
//		sb.append("Data de inclusão na faculdade: " + super.SDF.format(this.getDataJoin()) + "\n");
//		sb.append("Data de nascimento: " + super.SDF.format(this.getBirthDate()) + "\n");
//		sb.append("--------Cursos inscritos------------");
//		for(Supplies supplies : this.provideSupplies) {
//			sb.append(supplies + " \n");
//		}
		return this.getName();
	}

	



}
