package entities.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;




public class Supplies {
	private String supplieName;
	private String supplieID;
	private Teacher teacher;
	private float aprovedGrade;
	private int maxGap;

	Set<Classmate> list = new HashSet<Classmate>();
	
	public Supplies(String supplieName, String id, Teacher teacher, float aprovedGrade, int maxGap) {
		this.supplieID = id;
		this.supplieName = supplieName;
		this.aprovedGrade = aprovedGrade;
		this.maxGap = maxGap;
		this.teacher = teacher;
	}

	public void setAprovedGrade(float aprovedGrade) {
		this.aprovedGrade = aprovedGrade;
	}

	public float getAprovedGrade(){
		return this.aprovedGrade;
	}
	public int getMaxGap() {
		return maxGap;
	}

	public void setMaxGap(int maxGap) {
		this.maxGap = maxGap;
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
	public String getSupplieID() {
		return this.supplieID;
	}
	
	@Override
	public String toString() {
		return this.getSupplieName();
	}
	
}
