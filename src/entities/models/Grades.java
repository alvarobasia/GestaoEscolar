package entities.models;

import entities.enums.Situation;

import java.util.Objects;

/**
 * Entidade Notas
 * @author Alvaro Basilio
 */
public class Grades {
	
	Float grade;
	Integer gap;
	String matricula;
	String sala;
	Supplies disciplina;
	Situation situation;
	String name;
	public Grades(String name,Float grade, String matricula,Supplies disciplina, String sala,Integer gap, Situation situation){
		this.grade = grade;
		this.name = name;
		this.gap = gap;
		this.disciplina = disciplina;
		this.matricula = matricula;
		this.sala = sala;
		this.situation = situation;
	}

	public Grades( String matricula, String sala, Supplies disciplina){
		this.matricula = matricula;
		this.sala = sala;
		this.disciplina = disciplina;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Supplies getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Supplies disciplina) {
        this.disciplina = disciplina;
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
	public void setStringsituation(String string){
		this.situation = Situation.valueOf(string);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Grades grades = (Grades) o;
		return Objects.equals(matricula, grades.matricula) &&
				Objects.equals(sala, grades.sala) &&
				Objects.equals(disciplina, grades.disciplina);
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula, sala, disciplina);
	}
}
