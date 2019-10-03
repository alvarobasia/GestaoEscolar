package entites;

import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class Course {
	private static int COUNT = 0;
	private String courseName;
	private Duration workLoad;
	private int courseID;
	private Teacher teacher;
	Set<Classmate> list = new HashSet<Classmate>();
	
	public Course(String courseName, Date init, Date finish, Teacher teacher) {
		this.courseID = COUNT;
		COUNT++;
		this.courseName = courseName;
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

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Duration getWorkLoad() {
		return workLoad;
	}
	public void setWorkLoad(Duration workLoad) {
		this.workLoad = workLoad;
	}
	public int getCourseID() {
		return this.courseID;
	}
	
	@Override
	public String toString() {
		return "Nome do curso: "+ this.courseName + ", " + "duração: " + this.getWorkLoad();	
	}
	
}
