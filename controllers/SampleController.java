package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SampleController {

	@FXML
	private AnchorPane back;

	@FXML
	private Button btn;

	@FXML
	private Button classmate;

	@FXML
	private Button close;

    @FXML
    private ContextMenu context;
    
    @FXML
    private Button teacher;
    
	@FXML
	void closeApplication() {
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}

	@FXML
	void moduloAluno() throws IOException {
		Stage sc = (Stage) classmate.getScene().getWindow();
		Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/ClassmateModel.fxml"));
		Scene scene = new Scene(root, sc.getWidth(), sc.getHeight());
		scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
		sc.setScene(scene);
		sc.show();
	}
	
	@FXML
	void moduloProfessor() throws IOException {
		Stage sc = (Stage) teacher.getScene().getWindow();
		Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/TeacherModel.fxml"));
		Scene scene = new Scene(root, sc.getWidth(), sc.getHeight());
		scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
		sc.setScene(scene);
		sc.show();
	}
	
}
