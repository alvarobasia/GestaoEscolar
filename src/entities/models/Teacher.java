package entities.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import entities.enums.Gender;
import entities.enums.Nomination;
import entities.exeptions.infoBancoExeption;
import entities.services.ConnectJDCB;
import org.w3c.dom.ls.LSOutput;

public class Teacher extends Person{
	private static int ID_CONT;

	static {
		try {
			ID_CONT = ConnectJDCB.getTeacherId();
			System.out.println(ID_CONT+"IIII");
		} catch (entities.exeptions.infoBancoExeption infoBancoExeption) {
			infoBancoExeption.printStackTrace();
		}
	}

	private Float salary;
	private int teacherID;
	private Nomination nomination;

	//Set<Supplies> provideSupplies = new HashSet<Supplies>();
	
	public Teacher(String name, LocalDate birthDate, Float salary, String cpf, Gender gender, Nomination nomination) {
		super(name, birthDate, cpf, gender);
		System.out.println("qwer"+ ID_CONT);
		this.salary = salary;
		this.nomination = nomination;
		this.teacherID = ID_CONT;
		ID_CONT++;
	}
	
	public Teacher(String name, LocalDate birthDate, String cpf, Gender gender, Nomination nomination) {
		super(name, birthDate, cpf, gender);
		System.out.println("qwer"+ ID_CONT);
		this.nomination = nomination;
		this.teacherID = ID_CONT;
		ID_CONT++;
	}


	public static int getID_CONT() {
		return ID_CONT;
	}

	public static void setID_CONT(int iD_CONT) {
		ID_CONT = iD_CONT;
	}

	public Nomination getNomination() {
		return nomination;
	}

	public void setNomination(Nomination nomination) {
		this.nomination = nomination;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public Float getSalary() {
		return this.salary;
	}
	
	public void setSalary(Float salary) {
		this.salary = salary;
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
	protected int generateID() {
		return 0;
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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Teacher teacher = (Teacher) o;
		return teacherID == teacher.teacherID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(teacherID);
	}
}
