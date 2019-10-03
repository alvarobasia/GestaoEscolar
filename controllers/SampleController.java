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

public class SampleController {

    @FXML
    private AnchorPane back;

    @FXML
    private Button btn;
    
    @FXML
    private Button aluno;
    
    @FXML
    private Button close;

    @FXML
    void closeApplication() {
    	 Stage stage = (Stage) close.getScene().getWindow();
         stage.close();
    }
    @FXML
	void registrarAluno() throws IOException {
	    Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("../view/Registration.fxml"));    
	    Stage sc = (Stage) close.getScene().getWindow();
	    Scene scene = new Scene(root,800,700);
		scene.getStylesheets().add(getClass().getResource("../view/Registration.css").toExternalForm());
		sc.setScene(scene);
		sc.show();
    }

}
