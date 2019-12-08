package controllers;

import entities.enums.Situation;
import entities.exeptions.infoBancoExeption;
import entities.models.Classmate;
import entities.models.Classroom;
import entities.models.Grades;
import entities.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author alvaro Basilio
 * Classe de controller , utilizando padroes mvc
 * @version 1.0
 * @see javafx.fxml.Initializable
 */

public class AltClassroomController implements Initializable {
    @FXML
    private AnchorPane ba;

    @FXML
    private Label texts;

    @FXML
    private TableView<Classmate> tabela;

    @FXML
    private TableColumn<Classmate, String> nome;

    @FXML
    private TableColumn<Classmate, String> cpf;

    @FXML
    private TableColumn<Classmate, String> matricula;

    @FXML
    private Button registrar;

    @FXML
    private Label texts1;

    @FXML
    private ComboBox<Classroom> disci;

    @FXML
    private Label texts11;

    @FXML
    private Pane lateralBar;

    @FXML
    private Button backButton;

    private ObservableList<Classmate> list;

    private ObservableList<Classroom> sup;

    /**
     * Metodo para povoar a tabela
     */
    private void fillList(){
        List<Classmate> c = SaveClassemate.getInstance().getRegister();
        list =  FXCollections.observableArrayList(c);
        tabela.setItems( list);
    }

    /**
     * Método para povoar o comboBox
     */
    private void fillListView(){
        SaveClassrooms saveClassrooms = SaveClassrooms.getInstance();
        List<Classroom> t =  saveClassrooms.getRegister();
        sup  = FXCollections.observableArrayList(t);
        for(Classroom s : sup){
            disci.getItems().add(s);
        }
    }
    /**
     * Médoto construtor das células na tabela
     */
    private void table() {
        fillList();
        nome.setCellValueFactory(new PropertyValueFactory<>("name"));
        cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        matricula.setCellValueFactory(new PropertyValueFactory<>("registration"));
    }

    /**
     *
     * @return boolean - se a tabela esta selecionada e disciplina no combobox tambem
     */
    private boolean iscorrety(){
        ObservableList<Classmate> c = tabela.getSelectionModel().getSelectedItems();
        return c.size()> 0&& disci.getValue() != null;
    }

    /**
     * Valida os campos
     */
    @FXML
    void textValidate(){
        if(iscorrety()){
            registrar.setDisable(false);
        }
    }

    /**
     * Registra as alterações
     */
    @FXML
    void register() {
        try {
            ObservableList<Classmate> result = tabela.getSelectionModel().getSelectedItems();
            Classroom cl = disci.getValue();
            Grades grades = null;
            for (Classmate c : result) {
                grades = new Grades(c.getName(),0f, c.getRegistration(),cl.getSupplies(),cl.getID(),0, Situation.PENDENTE );
                SaveGrades.getInstance().add(grades);
                ConnectJDCB.generateClass(cl.getID(), c.getRegistration(), grades);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de turma");
            alert.setHeaderText("O professor foi cadastrado com sucesso!");
            alert.show();
        }catch (infoBancoExeption e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no banco de dados");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }
    /**
     * Médoto para retorar ao modulo
     * @throws IOException
     */
    @FXML
    void voltarMenu() throws IOException {
        Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/CourseModel.fxml"));
        AssistentScene.getScene(backButton,root);
    }

    /**
     * Método que sobreescreve da interface Initialize, ela é executada quando a cena é carregada
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        registrar.setDisable(true);
        table();
        fillListView();

    }
}
