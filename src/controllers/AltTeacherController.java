package controllers;

import entities.models.Teacher;
import entities.services.ConnectJDCB;
import entities.services.SaveTeachers;
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
import javafx.util.converter.FloatStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author avaro Basilio
 * Classe de controller , utilizando padroes mvc
 * @version 1.0
 * @see javafx.fxml.Initializable
 */
public class AltTeacherController implements Initializable {

    @FXML
    private AnchorPane ba;

    @FXML
    private Label texts;

    @FXML
    private TableView<Teacher> tabela;

    @FXML
    private TableColumn<Teacher, String> nome;

    @FXML
    private TableColumn<Teacher, String> cpf;

    @FXML
    private TableColumn<Teacher, String> Nomeacao;

    @FXML
    private TableColumn<Teacher, Float> salario;

    @FXML
    private Button exc;

    @FXML
    private Button alt;

    @FXML
    private Pane lateralBar;

    @FXML
    private Button backButton;

    private ObservableList<Teacher> list;

    /**
     * Metodo para povoar a tabela
     */
    private void fillList(){
        List<Teacher> c = SaveTeachers.getInstance().getRegister();
        list =  FXCollections.observableArrayList(c);
        tabela.setItems(list);
    }
    /**
     * Médoto construtor das células na tabela
     */
    private void table(){
        fillList();
        nome.setCellValueFactory(new PropertyValueFactory<>("name"));
        cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        Nomeacao.setCellValueFactory(new PropertyValueFactory<>("nomination"));
        salario.setCellValueFactory(new PropertyValueFactory<>("salary"));
        editCols();
    }

    /**
     * Torna as celulas editaveis
     */
    private void editCols(){
        Nomeacao.setEditable(false);
        nome.setCellFactory(TextFieldTableCell.forTableColumn());
        nome.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
        });
        cpf.setCellFactory(TextFieldTableCell.forTableColumn());
        cpf.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCpf(e.getNewValue());
        });
        salario.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        salario.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSalary(e.getNewValue());
        });
    }
    /**
     * Médoto para retorar ao modulo
     * @throws IOException
     */
    @FXML
    void voltarMenu() throws IOException {
        Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/TeacherModel.fxml"));
        AssistentScene.getScene(backButton,root);
    }

    /**
     * Método que sobreescreve da interface Initialize, ela é executada quando a cena é carregada
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table();
        tabela.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        exc.setOnAction(e ->{
            ObservableList<Teacher> list = tabela.getSelectionModel().getSelectedItems();
            for (Teacher c : list) {
                tabela.getItems().remove(c);
                SaveTeachers.getInstance().getRegister().remove(c);
                try {
                    ConnectJDCB.deleteTeacher(c);
                } catch (entities.exeptions.infoBancoExeption infoBancoExeption) {
                    infoBancoExeption.printStackTrace();
                }
                SaveTeachers.getInstance().getRegister().remove(c);
            }
            tabela.getSelectionModel().clearSelection();
        });
        alt.setOnAction(e->{
            Teacher teacher = tabela.getSelectionModel().getSelectedItem();
            ObservableList<TablePosition> tp = tabela.getSelectionModel().getSelectedCells();
            List<Teacher> L = SaveTeachers.getInstance().getRegister();
            for (TablePosition d : tp) {
                String name = tabela.getItems().get(d.getRow()).getName();
                String cpf = tabela.getItems().get(d.getRow()).getCpf();
                Float salario = tabela.getItems().get(d.getRow()).getSalary();
                System.out.println(salario);
                for(Teacher teacher1: L){
                    if(teacher.equals(teacher1)){
                        teacher1.setName(name);
                        teacher1.setCpf(cpf);
                        teacher1.setSalary(salario);
                        try {
                            ConnectJDCB.updateTeacher(teacher1);
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
