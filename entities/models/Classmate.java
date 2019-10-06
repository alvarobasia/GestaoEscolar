package entities.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Classmate extends Person{
	
    private String registration;
	
    List<Course> list = new ArrayList<Course>();
    
    List<Grades> grades = new ArrayList<Grades>();   
	
	
	public Classmate(String name, Date birthDate, String cpf, Course course) {
		super(name, birthDate, cpf);
		this.list.add(course);
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
		return null;
	}

	
}
