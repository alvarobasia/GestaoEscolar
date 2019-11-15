package entities.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;




public class Supplies {
	private String supplieName;
	private int workLoad;
	private long duration;
	private String supplieID;
	private Teacher teacher;
	Set<Classmate> list = new HashSet<Classmate>();
	
	public Supplies(String supplieName, String id,LocalDate init, LocalDate finish, Teacher teacher, int workLoad) {
		this.supplieID = id;
		this.supplieName = supplieName;
		this.workLoad  = workLoad;
		this.duration = ChronoUnit.DAYS.between(init, finish);
		this.teacher = teacher;
	}
	
	
	public long getDuration() {
		return duration;
	}


	public void setDuration(long duration) {
		this.duration = duration;
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
	public int getWorkLoad() {
		return workLoad;
	}
	public void setWorkLoad(int workLoad) {
		this.workLoad = workLoad;
	}
	public String getSupplieID() {
		return this.supplieID;
	}
	
	@Override
	public String toString() {
		return this.getSupplieName();
	}
	
}
