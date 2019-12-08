package entities.services;

import entities.models.Classmate;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;
/**
 * Utilizando o modelo singthom, registra as alunos
 * @author Alvaro Basilio
 */
public class SaveClassemate {
    private List<Classmate> classmateList = new ArrayList<>();
    private static final SaveClassemate INSTANCE = new SaveClassemate();

    private SaveClassemate(){
    }

    public void add(Classmate classmate) {
        classmateList.add(classmate);
    }

    public List<Classmate> getRegister(){ return this.classmateList;}

    @Contract(pure = true)
    public static SaveClassemate getInstance(){
        return INSTANCE;
    }
}
