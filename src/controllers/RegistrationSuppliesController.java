package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.exeptions.DataExeption;
import entities.exeptions.InvalidCharacterExeption;
import entities.exeptions.NumbersExeption;
import entities.exeptions.infoBancoExeption;
import entities.models.Supplies;
import entities.models.Teacher;
import entities.services.ConnectJDCB;
import entities.services.SaveSupplie;
import entities.services.SaveTeachers;
import entities.services.Validatefields;
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
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class RegistrationSuppliesController implements Initializable{
	
	@FXML
	private AnchorPane back;
	
	@FXML
	private Pane lateralBar;
	
	@FXML
	private Label texts;
	
	@FXML
	private Label supplie;

	@FXML
	private  Label aproved;

	@FXML
	private Label infoGrades;

	@FXML
	private Label erroName;

	@FXML
	private Label erroGrade;

	@FXML
	private Label erroGap;

	@FXML
	private TextField gepsField;

	@FXML
	private Label gaps;

	@FXML
	private TextField aprovedField;

	@FXML
	private Label id;
	
	@FXML
	private Label teacher;

	@FXML
	private Label info;
	
	@FXML
	private TextField fieldSupplie;
	
	@FXML
	private TextField fieldId;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button cadastro;
	
	@FXML
	private ComboBox<Teacher> fieldTeacher;
	
	private ObservableList<Teacher> lists;
	
	public void TeacherList() {
		SaveTeachers saveTeachers = SaveTeachers.getInstance();
		List<Teacher> t = (List<Teacher>) saveTeachers.getRegister();
		lists = FXCollections.observableArrayList(t);
		fieldTeacher.setItems(lists);
	}

	 @FXML
	 void textValidate() throws InvalidCharacterExeption, NumbersExeption {
		if(validateFields())
			cadastro.setDisable(false);
		else
			cadastro.setDisable(true);
	}

	@FXML
	void registerSupplies(){
		erroGap.setVisible(false);
		erroGrade.setVisible(false);
		erroName.setVisible(false);
		try {
			if (!Validatefields.isAllLettes(fieldSupplie.getText()))
				throw new InvalidCharacterExeption("Digite apenas letras");
			if (!Validatefields.isOnlyNumbers(gepsField.getText()))
				throw new NumbersExeption("Digite apenas numeros");
			if (!Validatefields.isOnlyNumbers(aprovedField.getText()))
				throw new DataExeption("Digite apenas numeros");
			Teacher teacher = fieldTeacher.getValue();
            System.out.println(teacher);
			Supplies supplies;
			if(teacher!= null) {
			    supplies = new Supplies(fieldSupplie.getText(), fieldId.getText(), teacher,
                        Float.parseFloat(aprovedField.getText()), Integer.parseInt(gepsField.getText()));
            }
			else {
                supplies = new Supplies(fieldSupplie.getText(), fieldId.getText(), null,
                        Float.parseFloat(aprovedField.getText()), Integer.parseInt(gepsField.getText()));
            }
			SaveSupplie saveSupplie = SaveSupplie.getInstance();
			saveSupplie.add(supplies);
			ConnectJDCB.creatNewTable(ConnectJDCB.generateSuppliesTable());
			ConnectJDCB.insertSuplies(supplies);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Cadastro de materia");
			alert.setHeaderText("Materia cadastrada com sucesso");
			alert.show();
			backToModel();
		}catch (InvalidCharacterExeption e){
      		erroName.setVisible(true);
		}catch (NumbersExeption e){
			erroGap.setVisible(true);
		} catch (DataExeption e) {
			erroGrade.setVisible(true);
		}catch (infoBancoExeption e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Conexão ao banco de dados");
			alert.setHeaderText("Não foi possivel conectar no banco de dados");
			alert.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 private boolean validateFields(){
		Teacher teacher = fieldTeacher.getValue();
		if(fieldSupplie.getText().isEmpty() || fieldId.getText().isEmpty() || gepsField.getText().isEmpty() ||
		aprovedField.getText().isEmpty())
			return false;
		return true;
	 }

	@FXML
	void backToModel() throws IOException {
		Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/CourseModel.fxml"));
		AssistentScene.getScene(backButton,root);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cadastro.setDisable(true);
		erroGap.setVisible(false);
		erroGrade.setVisible(false);
		erroName.setVisible(false);
		TeacherList();
	}
	
}
