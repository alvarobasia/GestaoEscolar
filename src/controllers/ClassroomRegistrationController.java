package controllers;

import entities.exeptions.infoBancoExeption;
import entities.models.Course;
import entities.models.Supplies;
import entities.models.Teacher;
import entities.services.ConnectJDCB;
import entities.services.SaveCourses;
import entities.services.SaveSupplie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;


public class ClassroomRegistrationController implements Initializable {

    @FXML
    private Pane lateralBar;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane back;

    @FXML
    private Label info;

    @FXML
    private Label supplie;

    @FXML
    private Button register;

    @FXML
    private TextField fieldClass;

    @FXML
    private TextField filedID;

    @FXML
    private ComboBox<Supplies> suplliesOnComboBox;

    @FXML
    private Label name;

    @FXML
    private Label texts;

    @FXML
    private Label labelclass;

    @FXML
    private Label turn;

    private Label t1;

    private Label t2;

    private Text infoP;

    private Label D1;

    private Text infodis;

    @FXML
    private RadioButton VESPERTINO;

    @FXML
    private ToggleGroup turno;

    @FXML
    private RadioButton NOTURNO;

    @FXML
    private RadioButton MATUTINO;


    @FXML
    private TextFlow infoDiciplina;

    @FXML
    private TextFlow infoProf;

    private ObservableList<Supplies> lists;


    @FXML
    void register() {

    }

    private boolean validator(){
        return !fieldClass.getText().isEmpty() && !filedID.getText().isEmpty() &&
                suplliesOnComboBox.getValue() != null;
    }

    @FXML
    void textValidate() {
        if(validator())
            register.setDisable(false);
    }

    private void fillComboBox(){
        List<Supplies> supplies = SaveSupplie.getInstance().getRegister();
        lists = FXCollections.observableArrayList(supplies);
        suplliesOnComboBox.setItems(lists);
    }

    @FXML
    private void fillTaxtFlow(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Teacher teacher = suplliesOnComboBox.getValue().getTeacher();
        Supplies supplies = suplliesOnComboBox.getValue();
        if(teacher == null) {
            infoProf.setStyle("-fx-border-color: red;" +
                    "-fx-border-style: solid");
            if(!infoProf.getChildren().contains(t2))
                infoProf.getChildren().removeAll();
           if(!infoProf.getChildren().contains(t1))
                infoProf.getChildren().add(t1);

       }else {
            infoProf.setStyle("-fx-border-color: black;" +
                    "-fx-border-style: solid");
            if(infoProf.getChildren().contains(t1))
                infoProf.getChildren().remove(t1);
            infoP.setText("\nNome: "+ teacher.getName() + "\n" +
           "Nascimento: "+ teacher.getBirthDate().format(formatter) + "\n" +
              "Entrada: " + teacher.getDataJoin().format(formatter)+ "\n"+
                    "Gênero: "+ teacher.getGender().toString()+ "\n"+
                    "Nomeação: "+ teacher.getNomination().toString());
            infoProf.getChildren().remove(t2);
            infoProf.getChildren().remove(infoP);
            infoProf.getChildren().add(t2);
            infoProf.getChildren().add(infoP);
        }
        infoDiciplina.setStyle("-fx-border-color: black;" +
                "-fx-border-style: solid");
        infodis.setText("\nNome: "+ supplies.getSupplieName() + "\n" +
                "Código: "+ supplies.getSupplieID()+ "\n"+
                "Nota para aprovação: "+ supplies.getAprovedGrade() + "\n"+
                "Máximo de faltas: " + supplies.getMaxGap());
        infoDiciplina.getChildren().remove(D1);
        infoDiciplina.getChildren().remove(infodis);
        infoDiciplina.getChildren().add(D1);
        infoDiciplina.getChildren().add(infodis);
    }

    @FXML
    void voltarMenu() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t1 = new Label("Essa diciplina não possui professor!");
        t2 = new Label("Informações do professor: \n");
        D1 = new Label("Informações da disciplina: \n");
        t1.setStyle("-fx-text-fill: red;" +
                "-fx-font-size: 15px;" +
                "-fx-padding: 5px");
        t2.setStyle("-fx-text-fill: black;" +
                "-fx-font-size: 15px;" +
                "-fx-padding: 5px");
        D1.setStyle("-fx-text-fill: black;" +
                "-fx-font-size: 15px;" +
                "-fx-padding: 5px");
        infoP = new Text() ;
        infodis = new Text();
        infoP.setStyle("-fx-text-fill: black;" +
                "-fx-font-size: 18px;" +
                "-fx-padding: 10px");
        infodis.setStyle("-fx-text-fill: black;" +
                "-fx-font-size: 18px;" +
                "-fx-padding: 10px");
        infoDiciplina.setStyle("-fx-border-color: rgba(0,0,0,0);" +
                "-fx-border-style: solid");
        try {
            ConnectJDCB.getAllSupplies();
        } catch (entities.exeptions.infoBancoExeption infoBancoExeption) {
            infoBancoExeption.printStackTrace();
        }
        fillComboBox();
        register.setDisable(true);
    }
}
