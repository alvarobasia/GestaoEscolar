package entities.models;

import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class Supplies {
	private static int COUNT = 0;
	private String supplieName;
	private Duration workLoad;
	private int supplieID;
	private Teacher teacher;
	Set<Classmate> list = new HashSet<Classmate>();
	
	public Supplies(String supplieName, Date init, Date finish, Teacher teacher) {
		this.supplieID = COUNT;
		COUNT++;
		this.supplieName = supplieName;
		this.workLoad = Duration.ofDays(init.getTime() - finish.getTime());
		this.teacher = teacher;
	}
	
	public Set<Classmate> getlistOfStudents(){
		return this.list;
	}
	
	public void addStudent(Classmate classmate) {
		list.add(classmate);
	}
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSupplieName() {
		return supplieName;
	}
	public void setSupplieName(String courseName) {
		this.supplieName = courseName;
	}
	public Duration getWorkLoad() {
		return workLoad;
	}
	public void setWorkLoad(Duration workLoad) {
		this.workLoad = workLoad;
	}
	public int getSupplieID() {
		return this.supplieID;
	}
	
	@Override
	public String toString() {
		return this.getSupplieName();
	}
	
}
