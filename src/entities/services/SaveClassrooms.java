package entities.services;

import entities.models.Classroom;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilizando o modelo singthom, registra as salas
 * @author Alvaro Basilio
 */
public class SaveClassrooms {
    private List<Classroom> classroomslist = new ArrayList<>();
    private final static SaveClassrooms INSTANCE = new SaveClassrooms();

    private SaveClassrooms(){

    }
    public void add(Classroom classroom){ classroomslist.add(classroom);}

    public Classroom getClassroom(int num){ return classroomslist.get(num);}

    public List<Classroom> getRegister(){ return  classroomslist;}

    @Contract(pure = true)
    public static SaveClassrooms getInstance(){ return INSTANCE;}
}
