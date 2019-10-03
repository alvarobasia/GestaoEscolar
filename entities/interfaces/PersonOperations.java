package entities.interfaces;


import java.util.List;

import entites.Person;

public interface PersonOperations {
	void addPerson(Person p);
	Person removePerson(List<Person> p, int id);
	Integer searchPersonByID(List<? extends Person> list, int id);
	Integer searchPersonByName(List<? extends Person> list, String name);
	Integer searchPersonByCPF(List<? extends Person> list, String cpf);
}
