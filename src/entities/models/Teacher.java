package entities.models;

import java.time.LocalDate;
import java.util.Objects;

import entities.enums.Gender;
import entities.enums.Nomination;
import entities.services.ConnectJDCB;

/**
 * Entidade Professor
 * @author Alvaro Basilio
 */
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

	
	public Teacher(String name, LocalDate birthDate, Float salary, String cpf, Gender gender, Nomination nomination) {
		super(name, birthDate, cpf, gender);
		this.salary = salary;
		this.nomination = nomination;
		this.teacherID = ID_CONT;
		ID_CONT++;
	}
    public Teacher(String name, LocalDate birthDate, Float salary, String cpf, Gender gender, Nomination nomination, int id) {
        super(name, birthDate, cpf, gender);
        this.salary = salary;
        this.nomination = nomination;
        this.teacherID = id;
    }
	public Teacher(String name, LocalDate birthDate, String cpf, Gender gender, Nomination nomination) {
		super(name, birthDate, cpf, gender);
		this.nomination = nomination;
		this.teacherID = ID_CONT;
        ID_CONT++;
	}

    public Teacher(String name, LocalDate birthDate, String cpf, Gender gender, Nomination nomination, int id) {
        super(name, birthDate, cpf, gender);
        this.nomination = nomination;
        this.teacherID = id;
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
	protected int generateID() {
		return 0;
	}

	@Override
	public String toString() {
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
