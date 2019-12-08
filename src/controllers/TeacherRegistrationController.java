package controllers;



import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


import entities.enums.Nomination;
import entities.exeptions.DataExeption;
import entities.exeptions.InvalidCharacterExeption;
import entities.exeptions.InvalidCpfExeption;
import entities.exeptions.infoBancoExeption;
import entities.models.Supplies;
import entities.models.Teacher;
import entities.services.*;
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

/**
 * @author alvaro Basilio
 * Classe de controller , utilizando padroes mvc
 * @version 1.0
 * @see javafx.fxml.Initializable
 */
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

    /**
     *Verifica se os campos estão preenchidos
     * @return boolean verificaçao de preenchimento
     */
    private boolean fieldsValidate(){
        return !(fieldName.getText().isEmpty() || filedNick.getText().isEmpty() || data.getValue() == null ||
                cpf.getText().isEmpty() || !Gender.getSelectedToggle().isSelected());
    }

    /**
     * Modifica o botão dependendo dos campos preenchidos
     */
    @FXML
   private void register(){
        if(fieldsValidate())
            register.setDisable(false);
        else
            register.setDisable(true);
    }

    /**
     * Coloca o nome corretamente dependendo da nomeação e genero
     * @param Gender gender
     * @param RadioButton grau
     * @return
     */
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

    /**
     * Registra um novo professor
     */
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
        String name = fieldName.getText() + ' ' + filedNick.getText();
        String nameFinal = getCorectName(gender,grau);
        String sex, g;
        Float Salary =null;
        if(!salary.getText().equals("")) {
            Salary =Float.parseFloat(Validatefields.formatNumbers(salary.getText()));
        }

        if(gender.getId().equals("fem")){
            sex = "FEMININO";
        }else {
            sex = "MASCULINO";
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

            Teacher teacher;
            System.out.println(Salary);
            if(Salary == null)
                teacher = new Teacher(nameFinal,nascimeto,null,Cpf, entities.enums.Gender.valueOf(sex), Nomination.valueOf(g));
            else
                teacher = new Teacher(nameFinal,nascimeto,Salary,Cpf, entities.enums.Gender.valueOf(sex), Nomination.valueOf(g));
            System.out.println(teacher.getTeacherID()+"qqq");
            String table = ConnectJDCB.generateTeacherTable();
            ConnectJDCB.creatNewTable(table);
            ConnectJDCB.insertTeacher(teacher);
           List<Supplies> supplies = selecionadas.getItems();
           for (Supplies s : supplies) {
                ConnectJDCB.ligaProfMat(teacher.getName(),teacher.getTeacherID(),s.getSupplieID());
           }
           SaveTeachers t = SaveTeachers.getInstance();
           t.getRegister().add(teacher);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Professores");
            alert.setHeaderText("O professor foi cadastrado com sucesso!");
            alert.show();
            try {
                voltarMenu();
            }catch (Exception e){
                System.out.println(e);
            }
        }catch (InvalidCharacterExeption e){
            erroName.setVisible(true);
            erroNick.setVisible(true);
        }catch (InvalidCpfExeption e){
            erroCPF.setVisible(true);
        }catch (DataExeption e) {
            erroData.setVisible(true);
        } catch (infoBancoExeption e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no banco de dados");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }


    }

    /**
     * Povoa a listView com as materias ministradas
     */
    @FXML
    private void colocarMateria(){
        List<Supplies> supplies = disponivel.getSelectionModel().getSelectedItems();
        ObservableList<Supplies> put = FXCollections.observableArrayList(supplies);
        for(Supplies supplies1 : put){
            if(disponivel.getItems().contains(supplies1))
                disponivel.getItems().remove(supplies1);
            if(selecionadas.getItems().contains(supplies1))
                continue;
            selecionadas.getItems().add(supplies1);
        }
    }

    /**
     * Retira materias do listView
     */
    @FXML
    private void retirarMateria(){
        List<Supplies> supplies = selecionadas.getSelectionModel().getSelectedItems();
        ObservableList<Supplies> remove = FXCollections.observableArrayList(supplies);
        for(Supplies s : remove){
            selecionadas.getItems().remove(s);
            disponivel.getItems().add(s);
        }
    }

    /**
     * Povoa o listView de matrias disponivies
     * @throws infoBancoExeption
     */
    private void fillListView() throws infoBancoExeption {
            SaveSupplie saveSupplie = SaveSupplie.getInstance();
            List<Supplies> t =  saveSupplie.getRegister();
            lists  = FXCollections.observableArrayList(t);
            for(Supplies s : lists){
                System.out.println("Prof -> " + s.getTeacher()+ "Materia ->"+ s.getSupplieName());
                if(s.getTeacher() ==  null)
                    disponivel.getItems().add(s);
            }
    }

    /**
     * Formata o cpf com uma mascara
     */
    @FXML
    void cpfFormatador() {
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("###.###.###-##");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(cpf);
    	tff.formatter();
    }

    /**
     * Formata o salario com uma data
     */
    @FXML
    void salarioFormatador() {
    	TextFieldFormatter t = new TextFieldFormatter();
    	t.setMask("'$####,##");
    	t.setCaracteresValidos("0123456789R");
    	t.setTf(salary);
    	t.formatter();
    }

    /**
     * Retorna ao modulo de professor
     * @throws IOException
     */
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

    /**
     * Método que sobreescreve da interface Initialize, ela é executada quando a cena é carregada
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        for (Supplies s : disponivel.getItems()){
            disponivel.getItems().remove(s);
        }
        disponivel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selecionadas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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