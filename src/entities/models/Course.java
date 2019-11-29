package entities.models;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private String courseName;
	private Integer duration = 0;

	public Course(String courseName, int duration) {
		this.courseName = courseName;
		this.duration = duration;
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
	
}
