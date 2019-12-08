package controllers;

import entities.enums.Situation;
import entities.exeptions.infoBancoExeption;
import entities.models.Classmate;
import entities.models.Classroom;
import entities.models.Grades;
import entities.models.Supplies;
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

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    private void fillList(){
        List<Classmate> c = SaveClassemate.getInstance().getRegister();
        list =  FXCollections.observableArrayList(c);
        tabela.setItems( list);
    }

    private void fillListView(){
        SaveClassrooms saveClassrooms = SaveClassrooms.getInstance();
        List<Classroom> t =  saveClassrooms.getRegister();
        sup  = FXCollections.observableArrayList(t);
        for(Classroom s : sup){
            disci.getItems().add(s);
        }
    }
    private void table() {
        fillList();
        nome.setCellValueFactory(new PropertyValueFactory<>("name"));
        cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        matricula.setCellValueFactory(new PropertyValueFactory<>("registration"));
    }

    private boolean iscorrety(){
        ObservableList<Classmate> c = tabela.getSelectionModel().getSelectedItems();
        return c.size()> 0&& disci.getValue() != null;
    }
    @FXML
    void textValidate(){
        if(iscorrety()){
            registrar.setDisable(false);
        }
    }

    @FXML
    void register() {
        try {
            ObservableList<Classmate> result = tabela.getItems();
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
    @FXML
    void voltarMenu() throws IOException {
        Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/CourseModel.fxml"));
        AssistentScene.getScene(backButton,root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        registrar.setDisable(true);
        table();
        fillListView();

    }
}
