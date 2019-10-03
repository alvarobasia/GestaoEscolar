package entites;

public class Grades {
	
	float firstTest;
	float secondTest;
	float thirdTest;
	Course course;
	
	public Grades(){
		
	}

	public float getFirstTest() {
		return firstTest;
	}

	public void setFirstTest(float firstTest) {
		this.firstTest = firstTest;
	}

	public float getSecondTest() {
		return secondTest;
	}

	public void setSecondTest(float secondTest) {
		this.secondTest = secondTest;
	}

	public float getThirdTest() {
		return thirdTest;
	}

	public void setThirdTest(float thirdTest) {
		this.thirdTest = thirdTest;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
