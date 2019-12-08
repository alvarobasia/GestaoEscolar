package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SampleController implements Initializable{


	@FXML
	private AnchorPane back;

	@FXML
	private Button btn;

	@FXML
	private Button classmate;
	
	@FXML
	private Button course;

	@FXML
	private Button close;

    @FXML
    private ContextMenu context;
    
    @FXML
    private Button teacher;
    
    @FXML 
    private Tooltip infoClassmate = new Tooltip(); 
    
    @FXML 
    private Tooltip infoTeacher = new Tooltip();

	public SampleController() {
	}


	@FXML
	void closeApplication() {
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}

	@FXML
	void moduloAluno() throws IOException {
		Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/ClassmateModel.fxml"));
		AssistentScene.getScene(classmate,root);
	}
	
	@FXML
	void moduloProfessor() throws IOException {
		Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/TeacherModel.fxml"));
		AssistentScene.getScene(teacher,root);

	}

	@FXML
	void moduloColegiado() throws IOException {
		Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/College.fxml"));
		AssistentScene.getScene(teacher,root);

	}

	@FXML
	void moduloCursos() throws IOException {
		Stage sc = (Stage) teacher.getScene().getWindow();
		Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/CourseModel.fxml"));
		Scene scene = new Scene(root, sc.getWidth(), sc.getHeight());
		scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
		sc.setScene(scene);
		sc.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		infoTeacher.setShowDelay(new Duration(500));
		infoClassmate.setShowDelay(new Duration(500));
		classmate.setTooltip(infoClassmate);
		teacher.setTooltip(infoTeacher);
		
	}
	
}
