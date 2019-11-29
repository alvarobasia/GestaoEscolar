package entities.services;

import entities.models.Teacher;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

public class SaveTeachers {
    private List<Teacher> teachersList = new ArrayList<>();
    private static final SaveTeachers INSTANCE  = new SaveTeachers();

    private SaveTeachers(){
    }

    public void add(Teacher teacher) {
        teachersList.add(teacher);
    }

    public Teacher getTeacher(int num){ return teachersList.get(num);}

    public List<Teacher> getRegister(){ return this.teachersList; }

    @Contract(pure = true)
    public static SaveTeachers getInstance(){
        return INSTANCE;
    }

}
