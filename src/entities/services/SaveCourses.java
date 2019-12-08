package entities.services;

import entities.models.Course;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilizando o modelo singthom, registra os cursos
 * @author Alvaro Basilio
 */
public class SaveCourses {
    private List<Course> courselist = new ArrayList<>();
    private final static SaveCourses INSTANCE = new SaveCourses();

    private SaveCourses(){

    }
    public void add(Course course){ courselist.add(course);}

    public Course getCourse(int num){ return courselist.get(num);}

    public List<Course> getRegister(){ return  courselist;}

    @Contract(pure = true)
    public static SaveCourses getInstance(){ return INSTANCE;}
}
