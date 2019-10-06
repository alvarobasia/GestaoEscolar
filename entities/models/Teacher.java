package entities.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Teacher extends Person{
	private static int ID_CONT = 1;
	private Double salary;
	

	Set<Course> provideCourses = new HashSet<Course>();
	
	public Teacher(String name, Date birthDate, Double salary, String cpf) {
		super(name, birthDate, cpf);
		this.salary = salary;
	}
	
	public Double getSalary() {
		return this.salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public void addCourse(Course course) {
		provideCourses.add(course);
	}
	
	public Course removeCourse(Course course) {
		Course removed = (Course) provideCourses.stream().filter(x -> x.getCourseID() == course.getCourseID());
		provideCourses.remove(removed);
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
		StringBuilder sb = new StringBuilder();
		sb.append("--------Informaçoẽs do Professor----------\n");
		sb.append("Nome: " + this.getName()+ "\n");
		sb.append("Identidade: " + this.getID()+ "\n");
		sb.append("Salário "+ this.getSalary() + "\n");
		sb.append("Data de inclusão na faculdade: " + super.SDF.format(this.getDataJoin()) + "\n");
		sb.append("Data de nascimento: " + super.SDF.format(this.getBirthDate()) + "\n");
		sb.append("--------Cursos inscritos------------");
		for(Course course : this.provideCourses) {
			sb.append(course + " \n");
		}
		return sb.toString();
	}

	



}
