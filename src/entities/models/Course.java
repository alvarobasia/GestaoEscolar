package entities.models;


import entities.services.ConnectJDCB;

/**
 * Entidade Curso
 * @author Alvaro Basilio
 */
public class Course {
	
	private String courseName;
	private Integer duration = 0;
	private Integer id;
	private static int ID;

	static {
		try {
			ID = ConnectJDCB.getCourseId();
		} catch (entities.exeptions.infoBancoExeption infoBancoExeption) {
			infoBancoExeption.printStackTrace();
		}
	}

	public Course(String courseName, int duration, int id) {
		this.courseName = courseName;
		this.duration = duration;
		this.id = id;
	}
	public Course(String courseName, int duration) {
		this.courseName = courseName;
		this.duration = duration;
		this.id = ID;
		ID++;
	}
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return this.getCourseName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
