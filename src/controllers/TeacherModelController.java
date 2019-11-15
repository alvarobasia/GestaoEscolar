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

public class TeacherModelController {

    @FXML
    private AnchorPane back;

    @FXML
    private Button newTeacher;

    @FXML
    private Button changeAndDelete;

    @FXML
    private Button backButton;

    @FXML
    void backToMenu() throws IOException {
    	Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("../view/Sample.fxml"));
    	Stage sc = (Stage) backButton.getScene().getWindow();
    	Scene scene = new Scene(root, sc.getWidth(), sc.getHeight());
    	scene.getStylesheets().add(getClass().getResource("../view/Registration.css").toExternalForm());
		sc.setScene(scene);
		sc.show();
    }
    
    @FXML
    void addTeacher() throws IOException {
    	Stage sc = (Stage) newTeacher.getScene().getWindow();
		Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/RegistrationTeacher.fxml"));
		Scene scene = new Scene(root, sc.getWidth(), sc.getHeight());
		scene.getStylesheets().add(getClass().getResource("../view/Registration.css").toExternalForm());
		sc.setScene(scene);
		sc.show();
    }
}
