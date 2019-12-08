package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * @author alvaro Basilio
 * Classe de controller , utilizando padroes mvc
 * @version 1.0
 * @see javafx.fxml.Initializable
 */
public class TeacherModelController {

    @FXML
    private AnchorPane back;

    @FXML
    private Button newTeacher;

    @FXML
    private Button changeAndDelete;

    @FXML
    private Button backButton;

    /**
     * Retorna ao menu
     * @throws IOException
     */
    @FXML
    void backToMenu() throws IOException {
    	Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("../view/Sample.fxml"));
        AssistentScene.getScene(backButton,root);

    }

    /**
     * Carrega a tela de registro de professores
     * @throws IOException
     */
    @FXML
    void addTeacher() throws IOException {
        Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("../view/RegistrationTeacher.fxml"));
        AssistentScene.getScene(newTeacher,root);

    }

    /**
     * Vai para alteração/exclusão de professor
     * @throws IOException
     */
    @FXML
    void change() throws IOException {
        Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/AltTeacher.fxml"));
        AssistentScene.getScene(changeAndDelete,root);
    }
}
