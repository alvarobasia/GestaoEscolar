package entities.models;

import entities.enums.Situation;

public class Grades {
	
	Float grade;
	Integer gap;
	String matricula;
	String sala;
	Situation situation;
	public Grades(Float grade, String matricula, String sala,Integer gap, Situation situation){
		this.grade = grade;
		this.gap = gap;
		this.matricula = matricula;
		this.sala = sala;
		this.situation = situation;
	}

	public Grades( String matricula, String sala){
		this.matricula = matricula;
		this.sala = sala;
	}
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Float getGrade() {
		return grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	public Integer getGap() {
		return gap;
	}

	public void setGap(Integer gap) {
		this.gap = gap;
	}
}
