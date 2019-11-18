package controllers;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import entities.services.TextFieldFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TeacherRegistrationController implements Initializable{

    final private ObservableList options = FXCollections.observableArrayList();

    @FXML
    private AnchorPane back;

    @FXML
    private Button register;

    @FXML
    private Label Texts;

    @FXML
    private DatePicker data;

    @FXML
    private Pane lateralBar;

    @FXML
    private Button backButton;

    @FXML
    private TextField cpf;

    @FXML
    private TextField salary;

    @FXML
    private ListView disponivel;

    ListView listViewPREV = new ListView(options);
    ListView listViewPOS= new ListView();

    @FXML
    private ListView selecionadas;

    @FXML
    private Button colocar;

    @FXML
    private Button tirar;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private RadioButton masc;

    @FXML
    private RadioButton fem;

    @FXML
    private ToggleGroup Grau;

    @FXML
    private RadioButton B;

    @FXML
    private RadioButton Mr;

    @FXML
    private RadioButton Dr;

    void fillListView(){

    }
    @FXML
    void cpfFormatador() {
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("###.###.###-##");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(cpf);
    	tff.formatter();
    }

    @FXML
    void salarioFormatador() {
    	TextFieldFormatter t = new TextFieldFormatter();
    	t.setMask("'$####,##");
    	t.setCaracteresValidos("0123456789R");
    	t.setTf(salary);
    	t.formatter();
    }
    @FXML
    void voltarMenu() throws IOException {
    	//Stage sc = (Stage) voltar.getScene().getWindow();
    	//sc.close();
    	//Main main = new Main();
    	//Stage stage = new Stage();
    	//main.start(stage);
    	Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("../view/TeacherModel.fxml"));
    	Stage sc = (Stage) backButton.getScene().getWindow();
    	Scene scene = new Scene(root, sc.getWidth(), sc.getHeight());
    	scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
		sc.setScene(scene);
		sc.show();

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}