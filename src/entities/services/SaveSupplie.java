package entities.services;

import entities.models.Supplies;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilizando o modelo singthom, registra as materias
 * @author Alvaro Basilio
 */
public class SaveSupplie {
    private List<Supplies> suppliesList = new ArrayList<>();
    private final static SaveSupplie INSTANCE = new SaveSupplie();

    private SaveSupplie(){

    }
    public void add(Supplies supplies){ suppliesList.add(supplies);}

    public Supplies getSupplie(int num){ return suppliesList.get(num);}

    public List<Supplies> getRegister(){ return  suppliesList;}

    @Contract(pure = true)
    public static SaveSupplie getInstance(){ return INSTANCE;}
}
