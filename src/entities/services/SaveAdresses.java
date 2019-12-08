package entities.services;

import entities.models.Address;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilizando o modelo singthom, registra as endereços
 * @author Alvaro Basilio
 */
public class SaveAdresses {
    private List<Address> adressList = new ArrayList<>();
    private static final SaveAdresses INSTANCE  = new SaveAdresses();

    private SaveAdresses(){
    }

    public void add(Address address) {
        adressList.add(address);
    }

    public List<Address> getRegister(){ return this.adressList;}

    @Contract(pure = true)
    public static SaveAdresses getInstance(){
            return INSTANCE;
    }

}
