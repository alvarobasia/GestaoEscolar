package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClassmateModelController {

    @FXML
    private AnchorPane back;

    @FXML
    private Button newClassmate;

    @FXML
    private Button changeAndDelete;

    @FXML
    private Button backButton;

    @FXML
    void backToMenu() throws IOException {
    	Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("../view/Sample.fxml"));
    	AssistentScene.getScene(backButton,root);
    }

    @FXML
    void addClassmate() throws IOException {
        Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Registration.fxml"));
        AssistentScene.getScene(newClassmate,root);
    }

    @FXML
    void change() throws IOException {
        Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/AltClassmate.fxml"));
        AssistentScene.getScene(newClassmate,root);
    }
}
