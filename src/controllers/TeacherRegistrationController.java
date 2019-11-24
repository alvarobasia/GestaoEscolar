package controllers;



import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


import entities.exeptions.DataExeption;
import entities.exeptions.InvalidCharacterExeption;
import entities.exeptions.InvalidCpfExeption;
import entities.exeptions.infoBancoExeption;
import entities.models.Supplies;
import entities.services.ConnectJDCB;
import entities.services.SaveSupplie;
import entities.services.TextFieldFormatter;
import entities.services.Validatefields;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TeacherRegistrationController implements Initializable{


    @FXML
    private AnchorPane back;

    @FXML
    private Button register;

    @FXML
    private Label Texts;

    @FXML
    private Label erroName;

    @FXML
    private Label erroNick;

    @FXML
    private Label erroCPF;

    @FXML
    private Label erroData;

    @FXML
    private DatePicker data;

    @FXML
    private Pane lateralBar;

    @FXML
    private Button backButton;

    @FXML
    private TextField cpf;

    @FXML
    private TextField salary;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField filedNick;

    @FXML
    private ListView<Supplies> disponivel;


    @FXML
    private ListView<Supplies> selecionadas;

    @FXML
    private Button colocar;

    @FXML
    private Button tirar;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private RadioButton masc;

    @FXML
    private RadioButton fem;

    @FXML
    private ToggleGroup Grau;

    @FXML
    private RadioButton B;

    @FXML
    private RadioButton Mr;

    @FXML
    private RadioButton Dr;

    private ObservableList<Supplies> lists;

    private ObservableList<Supplies> put;


    private boolean fieldsValidate(){
        return !(fieldName.getText().isEmpty() || filedNick.getText().isEmpty() || data.getValue() == null ||
                cpf.getText().isEmpty() || !Gender.getSelectedToggle().isSelected());
    }


    @FXML
   private void register(){
        RadioButton gender = (RadioButton) Gender.getSelectedToggle();
        if(fieldsValidate())
            register.setDisable(false);
        else
            register.setDisable(true);
    }

    @FXML
    private String getCorectName(RadioButton gender, RadioButton grau){
        String name = null;
        switch (grau.getId()){
            case "B" : name = fieldName.getText() + ' ' + filedNick.getText();break;
            case "Mr" : switch (gender.getId()){
                case "fem" :  name = "Ma. " + fieldName.getText() + ' ' + filedNick.getText();break;
                case "masc" :  name = "Me. " + fieldName.getText() + ' ' + filedNick.getText();break;
            }break;
            case "Dr" : switch (gender.getId()){
                case "fem" :  name = "Dra. " + fieldName.getText() + ' ' + filedNick.getText();break;
                case "masc" :  name = "Dr. " + fieldName.getText() + ' ' + filedNick.getText();break;
            }break;
        }
        return name;
    }

    @FXML
    private void registerTeacher(){
        erroName.setVisible(false);
        erroNick.setVisible(false);
        erroData.setVisible(false);
        erroCPF.setVisible(false);
        LocalDate nascimeto = data.getValue();
        String Cpf = cpf.getText();
        RadioButton gender = (RadioButton) Gender.getSelectedToggle();
        RadioButton grau = (RadioButton) Grau.getSelectedToggle();
        String name = getCorectName(gender,grau);
        String sex, g;
        Integer Salary =null;
        if(!salary.getText().equals("")) {
            Salary = Integer.parseInt(Validatefields.formatNumbers(salary.getText()));
        }

        if(gender.getId() == "masc"){
            sex = "MASCULINO";
        }else {
            sex = "FEMININO";
        }
        if(grau.getId() == "B")
            g = "BACHARELADO";
        else if(grau.getId() == "Mr")
            g = "MESTRADO";
        else
            g = "DOUTORADO";

        try {
            if (!Validatefields.isAllLettes(name))
                throw new InvalidCharacterExeption("O nome e sobrenome só podem conter letras");

            if (!Validatefields.isCpfValid(Cpf))
                throw new InvalidCpfExeption("Cpf Inválido");

            if (nascimeto.isAfter(LocalDate.now()))
                throw new DataExeption("Você deve colocar uma data válida");

        }catch (InvalidCharacterExeption e){
            erroName.setVisible(true);
            erroNick.setVisible(true);
        }catch (InvalidCpfExeption e){
            erroCPF.setVisible(true);
        }catch (DataExeption e){
            erroData.setVisible(true);
        }


    }


    @FXML
    private void colocarMateria(){
        List<Supplies> supplies = disponivel.getSelectionModel().getSelectedItems();
        ObservableList<Supplies> put = FXCollections.observableArrayList(supplies);
        for(Supplies supplies1 : put){
            if(selecionadas.getItems().contains(supplies1))
                continue;
            selecionadas.getItems().add(supplies1);
        }
    }
    private void fillListView() throws infoBancoExeption {
            SaveSupplie saveSupplie = SaveSupplie.getInstance();
            List<Supplies> t = (List<Supplies>) saveSupplie.getRegister();
            lists  = FXCollections.observableArrayList(t);
            disponivel.setItems(lists);
    }
    @FXML
    void cpfFormatador() {
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("###.###.###-##");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(cpf);
    	tff.formatter();
    }

    @FXML
    void salarioFormatador() {
    	TextFieldFormatter t = new TextFieldFormatter();
    	t.setMask("'$####,##");
    	t.setCaracteresValidos("0123456789R");
    	t.setTf(salary);
    	t.formatter();
    }
    @FXML
    void voltarMenu() throws IOException {
    	//Stage sc = (Stage) voltar.getScene().getWindow();
    	//sc.close();
    	//Main main = new Main();
    	//Stage stage = new Stage();
    	//main.start(stage);
    	Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("../view/TeacherModel.fxml"));
        AssistentScene.getScene(backButton,root);


    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        disponivel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        register.setDisable(true);
        erroName.setVisible(false);
        erroNick.setVisible(false);
        erroData.setVisible(false);
        erroCPF.setVisible(false);
        try {
            ConnectJDCB.getAllSupplies();
            fillListView();
        } catch (entities.exeptions.infoBancoExeption infoBancoExeption) {
            infoBancoExeption.printStackTrace();
        }
    }

}