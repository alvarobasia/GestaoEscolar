package controllers;



import entities.services.TextFieldFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class RegistrationController {

	
    @FXML
    private AnchorPane back;

    @FXML
    private Button cadastro;

    @FXML
    private Label Texts;

    @FXML
    private DatePicker data;

    @FXML
    private Pane lateralBar;

    @FXML
    private Button voltar;

    @FXML
    private TextField cpf;

    @FXML
    void cpfFormatador() {
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("###.###.###-##");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(cpf);
    	tff.formatter();
    }
	
}