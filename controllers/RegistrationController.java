package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entities.enums.Gender;
import entities.exeptions.DataExeption;
import entities.exeptions.InvalidCharacterExeption;
import entities.exeptions.InvalidCpfExeption;
import entities.models.Address;
import entities.models.Classmate;
import entities.models.Course;
import entities.models.Person;
import entities.services.ConnectJDCB;
import entities.services.TextFieldFormatter;
import entities.services.Validatefields;
import entities.services.ViaCEP;
import entities.services.ViaCEPException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class RegistrationController implements Initializable {


	@FXML
	private AnchorPane back;

	@FXML
	private Label info;

	@FXML
	private Label texts;

	@FXML
	private Label course;

	@FXML
	private Label birthDate;

	@FXML
	private Label name;

	@FXML
	private Label nickName;

	@FXML
	private Label tel;

	@FXML
	private Label gender;

	@FXML
	private Label cpf;

	@FXML
	private Label address;

	@FXML
	private Label infoClassmate;

	@FXML
	private Label city;

	@FXML
	private Label district;

	@FXML
	private Label street;

	@FXML
	private Label number;

	@FXML
	private Label complement;

	@FXML
	private Label cep;

	@FXML
	private Label errorName1;

	@FXML
	private Label errorName2;

	@FXML
	private Label errorCpf;

	@FXML
	private Label errorDate;

	@FXML
	private Label errorCep;

	@FXML
	private TextField fieldName;

	@FXML
	private TextField fieldNickName;

	@FXML
	private TextField fieldCpf;

	@FXML
	private TextField fieldTel;

	@FXML
	private TextField fieldCity;

	@FXML
	private TextField fieldDistrict;

	@FXML
	private TextField fieldStreet;

	@FXML
	private TextField fieldNumber;

	@FXML
	private TextField fieldComplement;

	@FXML
	private TextField fieldCep;

	@FXML
	private RadioButton MASCULINO;

	@FXML
	private RadioButton FEMININO;

	@FXML
	private ToggleGroup sex;

	@FXML
	private Button register;

	@FXML
	private DatePicker data;

	@FXML
	private Pane lateralBar;

	@FXML
	private Button backButton;

	@FXML
	private Line layout;

	@FXML
	private ComboBox<Course> cursesOnComboBox;

	private ObservableList<Course> lists;

	public void CourseList() {
		List<Course> course = Main.ComboBoxUpdate();
		lists = FXCollections.observableArrayList(course);
		cursesOnComboBox.setItems(lists);
	}

	@FXML
	void cpfFormatador() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(fieldCpf);
		tff.formatter();
	}

	@FXML
	void telFormatador() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##)#####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(fieldTel);
		tff.formatter();
	}

	@FXML
	void voltarMenu() throws IOException {
		// Stage sc = (Stage) voltar.getScene().getWindow();
		// sc.close();
		// Main main = new Main();
		// Stage stage = new Stage();
		// main.start(stage);
		Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/ClassmateModel.fxml"));
		Stage sc = (Stage) backButton.getScene().getWindow();
		Scene scene = new Scene(root, sc.getWidth(), sc.getHeight());
		scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
		sc.setScene(scene);
		sc.show();

	}

	@FXML
	private void textValidate() {
		try {
			if (fieldCep.getText().length() == 8) {
				errorCep.setVisible(false);
				ViaCEP vC = new ViaCEP(fieldCep.getText());
				fieldCity.setText(vC.getLocalidade());
				fieldDistrict.setText(vC.getBairro());
				fieldStreet.setText(vC.getLogradouro());
			} 
		} catch (ViaCEPException e) {
			errorCep.setText(e.getMessage());
			errorCep.setVisible(true);
		}
		if (!fieldName.getText().isEmpty() && (fieldCpf.getText().length() == 14 || fieldCpf.getText().length() == 15)
				&& !fieldNickName.getText().isEmpty() && data.getValue() != null && cursesOnComboBox.getValue() != null)
			if (fieldCity.getText().isEmpty() && fieldCep.getText().isEmpty() && fieldDistrict.getText().isEmpty()
					&& fieldStreet.getText().isEmpty() && fieldNumber.getText().isEmpty())
				register.setDisable(false);
			else if (!fieldCity.getText().isEmpty() && !fieldCep.getText().isEmpty()
					&& !fieldDistrict.getText().isEmpty() && !fieldStreet.getText().isEmpty()
					&& !fieldNumber.getText().isEmpty())
				register.setDisable(false);
			else
				register.setDisable(true);
		else
			register.setDisable(true);
	}

	@FXML
	private void register() throws Exception {
		errorCpf.setVisible(false);
		errorName1.setVisible(false);
		errorName2.setVisible(false);
		errorDate.setVisible(false);
		try {
			String name = fieldName.getText() + " " + fieldNickName.getText();
			String cpf = fieldCpf.getText();
			RadioButton aws = (RadioButton) sex.getSelectedToggle();
			LocalDate date = data.getValue();
			Course course = cursesOnComboBox.getValue();
			if (!Validatefields.isAllLettes(name))
				throw new InvalidCharacterExeption("O nome e sobrenome só podem conter letras");

			if (!Validatefields.isCpfValid(cpf))
				throw new InvalidCpfExeption("Cpf Inválido");

			if (date.isAfter(LocalDate.now()))
				throw new DataExeption("Você deve colocar uma data válida");

			cpf = Validatefields.formatNumbers(cpf);
			int[] result = registrationOptionsClassmate();
			
		
			if(result[0] == 1)
				registerWithAdressClassmate(result, name, cpf, date, course, aws);
			else 
				registerWithoutAdressClassmate(result, name, cpf, date, course, aws);

			
		} catch (InvalidCharacterExeption e) {
			errorName1.setText(e.getMessage());
			errorName2.setText(e.getMessage());
			errorName1.setVisible(true);
			errorName2.setVisible(true);
		} catch (InvalidCpfExeption e) {
			errorCpf.setText(e.getMessage());
			errorCpf.setVisible(true);
		} catch (DataExeption e) {
			errorDate.setText(e.getMessage());
			errorDate.setVisible(true);
		}
	}

	private int[] registrationOptionsClassmate() {
		int[] result = new int[3];
		
		if(!fieldCep.getText().isEmpty()) {
			result[0] = 1;
			if(!fieldComplement.getText().isEmpty())
				result[1]= 1;
			else
				result[1]=0;
		}else {
			result[0] = 0;
			result[1] = 0;
		}
		
		if(!fieldTel.getText().isEmpty())
			result[2] = 1;
		else
			result[2]= 0;
		
		return result;
	}
		
	private void registerWithAdressClassmate(int[] op ,String name, String cpf, LocalDate date, Course course, RadioButton gender) {
		Address address;
		Classmate classmate;
		if(op[1]==1 && op[2]==1) {
			address = new Address(fieldCity.getText(), fieldDistrict.getText(), fieldStreet.getText(), 
				Integer.parseInt(fieldNumber.getText()), Integer.parseInt(fieldCep.getText()), fieldComplement.getText());
			String sql = ConnectJDCB.generateAdressTable();
			ConnectJDCB.creatNewTable(sql);
			ConnectJDCB.insertAdress(address);
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course, address, fieldTel.getText());
			System.out.println(classmate.getRegistration());
		}
		
		if(op[1]==0 && op[2]==1) {
			address = new Address(fieldCity.getText(), fieldDistrict.getText(), fieldStreet.getText(), 
					Integer.parseInt(fieldNumber.getText()), Integer.parseInt(fieldCep.getText()));
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course, address, fieldTel.getText());
			System.out.println(classmate);
		}
		
		if(op[1]==0 && op[2]==0) {
			address = new Address(fieldCity.getText(), fieldDistrict.getText(), fieldStreet.getText(), 
					Integer.parseInt(fieldNumber.getText()), Integer.parseInt(fieldCep.getText()));
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course, address);
			System.out.println(classmate);
		}
	}
	
	private void registerWithoutAdressClassmate(int[] op ,String name, String cpf, LocalDate date, Course course, RadioButton gender) {
		Classmate classmate;
		if(op[2] == 1) {
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course, fieldTel.getText());
			System.out.println(classmate);
		}
		else {
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course);
			System.out.println(classmate);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CourseList();
		register.setDisable(true);
	}

}