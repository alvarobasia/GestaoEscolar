package entities.models;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private String courseName;
	
	private List<Supplies> suppliesOnCurse = new ArrayList<Supplies>();

	public Course(String courseName, List<Supplies> suppliesOnCurse) {
		this.courseName = courseName;
		this.suppliesOnCurse = suppliesOnCurse;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Supplies> getSuppliesOnCurse() {
		return suppliesOnCurse;
	}

	public void setSuppliesOnCurse(List<Supplies> suppliesOnCurse) {
		this.suppliesOnCurse = suppliesOnCurse;
	}
	
	public void addSupplie(Supplies supplie) {
		this.suppliesOnCurse.add(supplie);
	}
	public Supplies removeSupplie(Supplies supplie) {
		int index = this.suppliesOnCurse.indexOf(supplie);
		return this.suppliesOnCurse.remove(index);
	}

	@Override
	public String toString() {
		return this.getCourseName();
	}
	
}
