package entities.services;

import entities.models.Grades;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilizando o modelo singthom, registra as notas
 * @author Alvaro Basilio
 */
public class SaveGrades {
    private List<Grades> gradesList = new ArrayList<>();
    private static final SaveGrades INSTANCE  = new SaveGrades();

    private SaveGrades(){
    }

    public void add(Grades grades) {
        gradesList.add(grades);
    }

    public List<Grades> getRegister(){ return this.gradesList;}

    @Contract(pure = true)
    public static SaveGrades getInstance(){
            return INSTANCE;
    }

}
