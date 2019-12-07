package controllers;

import entities.exeptions.infoBancoExeption;
import entities.models.Classmate;
import entities.models.Person;
import entities.services.ConnectJDCB;
import entities.services.SaveClassemate;
import entities.services.TextFieldFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class AltClassmateController implements Initializable {
    @FXML
    private Label texts;

    @FXML
    private AnchorPane ba;

    @FXML
    private Button cadastro;

    @FXML
    private Button alt;


    @FXML
    public  TableView<Classmate> tabela;

    @FXML
    private TableColumn<Classmate, String> nome;

    @FXML
    private TableColumn<Classmate, String> cpf;

    @FXML
    private TableColumn<Classmate, String> matricula;

    @FXML
    private TableColumn<Classmate, String> nascimento;


    @FXML
    private TableColumn<Classmate, String> curso;

    @FXML
    private  Button exc;

    @FXML
    private Pane lateralBar;

    @FXML
    private Button backButton;

    private ObservableList<Classmate> list;

    private void fillList(){
        List<Classmate> c = SaveClassemate.getInstance().getRegister();
        list =  FXCollections.observableArrayList(c);
        tabela.setItems( list);
    }
    @FXML
    void register() {

    }

    @FXML
    void voltarMenu() throws IOException {
        Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/ClassmateModel.fxml"));
        AssistentScene.getScene(backButton,root);
    }

    private void table(){
        fillList();
        nome.setCellValueFactory(new PropertyValueFactory<>("name"));
        cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        matricula.setCellValueFactory(new PropertyValueFactory<>("registration"));
        nascimento.setCellValueFactory(new PropertyValueFactory<>("telNumber"));
        curso.setCellValueFactory(new PropertyValueFactory<>("course"));
        editcols();
    }
    private void editcols() {
        nome.setCellFactory(TextFieldTableCell.forTableColumn());
        nome.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });
        cpf.setCellFactory(TextFieldTableCell.forTableColumn());
        cpf.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCpf(e.getNewValue());
        });
        matricula.setCellFactory(TextFieldTableCell.forTableColumn());
        matricula.setEditable(false);
        nascimento.setCellFactory(TextFieldTableCell.forTableColumn());
        nascimento.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        table();
        tabela.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        exc.setOnAction(e ->{
           ObservableList<Classmate> list = tabela.getSelectionModel().getSelectedItems();
           for (Classmate c : list) {
               tabela.getItems().remove(c);
               SaveClassemate.getInstance().getRegister().remove(c);
               try {
                   ConnectJDCB.deleteClassmate(c);
               } catch (entities.exeptions.infoBancoExeption infoBancoExeption) {
                   infoBancoExeption.printStackTrace();
               }
               SaveClassemate.getInstance().getRegister().remove(c);
           }
            tabela.getSelectionModel().clearSelection();
        });
        alt.setOnAction(e ->{
            Classmate classmate = tabela.getSelectionModel().getSelectedItem();
            ObservableList<TablePosition> tp = tabela.getSelectionModel().getSelectedCells();
            List<Classmate> L = SaveClassemate.getInstance().getRegister();
            for (TablePosition d : tp) {
                String name = tabela.getItems().get(d.getRow()).getName();
                String cpf = tabela.getItems().get(d.getRow()).getCpf();
                for(Classmate classmate1: L){
                    if(classmate.getRegistration().equals(classmate1.getRegistration())){
                        classmate1.setName(name);
                        classmate1.setCpf(cpf);
                        try {
                            ConnectJDCB.updateClassmate(classmate1,name,cpf);
                        } catch (entities.exeptions.infoBancoExeption infoBancoExeption) {
                            infoBancoExeption.printStackTrace();
                        }
                        break;
                    }
                }
            }
        });
    }
}
