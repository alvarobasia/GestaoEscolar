package controllers;

import entities.exeptions.InvalidCharacterExeption;
import entities.exeptions.NumbersExeption;
import entities.models.Course;
import entities.services.ConnectJDCB;
import entities.services.SaveCourses;
import entities.services.Validatefields;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author alvaro Basilio
 * Classe de controller , utilizando padroes mvc
 * @version 1.0
 * @see javafx.fxml.Initializable
 */
public class CourseRegistrationController implements Initializable {
    @FXML
    private Label texts;

    @FXML
    private Label course;

    @FXML
    private Label seme;

    @FXML
    private Label erroName;

    @FXML
    private Label erroNum;

    @FXML
    private TextField fieldcourse;

    @FXML
    private TextField fieldseme;

    @FXML
    private Button cadastro;

    @FXML
    private Button backButton;

    /**
     * Valida os campos
     */
    @FXML
    private void textValidate(){
        if(validator())
            cadastro.setDisable(false);
    }

    /**
     *
     * @return boolean - verifica o preechimento dos campos
     */
    private boolean validator(){
        return !fieldseme.getText().isEmpty() && !fieldcourse.getText().isEmpty();
    }

    /**
     * Registra um novo curso
     */
    @FXML
    private void register(){
        erroName.setVisible(false);
        erroNum.setVisible(false);
        try {
            if(!Validatefields.isOnlyNumbers(fieldseme.getText()))
                throw new NumbersExeption("Digite apenas números");

            if(!Validatefields.isAllLettes(fieldcourse.getText()))
                throw new InvalidCharacterExeption("Digite apenas letras");

            Course course = new Course(fieldcourse.getText(),Integer.parseInt(fieldseme.getText()));
            SaveCourses saveCourses = SaveCourses.getInstance();
            saveCourses.getRegister().add(course);
            ConnectJDCB.insertCourse(course);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Cursos");
            alert.setHeaderText("O curso foi cadastrado com sucesso!");
            alert.show();
            try {
                voltarMenu();
            }catch (Exception e){
                System.out.println(e);
            }
        }catch (NumbersExeption e ) {
            erroNum.setVisible(true);
        }catch (InvalidCharacterExeption e){
            erroName.setVisible(true);
        } catch (entities.exeptions.infoBancoExeption e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no banco de dados");
            alert.setHeaderText(e.getMessage());
            alert.show();
        }
    }

    /**
     * Retorna ao modulo de cursos
     * @throws IOException
     */
    @FXML
    private void voltarMenu() throws IOException {
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
        erroName.setVisible(false);
        erroNum.setVisible(false);
        cadastro.setDisable(true);
    }
}
