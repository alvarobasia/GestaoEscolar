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
public class ClassmateModelController {

    @FXML
    private AnchorPane back;

    @FXML
    private Button newClassmate;

    @FXML
    private Button changeAndDelete;

    @FXML
    private Button backButton;

    /**
     * Metodo que retorna ao menu
     * @throws IOException
     */
    @FXML
    void backToMenu() throws IOException {
    	Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("../view/Sample.fxml"));
    	AssistentScene.getScene(backButton,root);
    }

    /**
     * Metodo que ira para cena de registrar um aluno
     * @throws IOException
     */
    @FXML
    void addClassmate() throws IOException {
        Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Registration.fxml"));
        AssistentScene.getScene(newClassmate,root);
    }

    /**
     * Método que carrega a cena de alteração/exclusão do aluno
     * @throws IOException
     */
    @FXML
    void change() throws IOException {
        Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/AltClassmate.fxml"));
        AssistentScene.getScene(newClassmate,root);
    }
}
