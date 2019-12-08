package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import entities.enums.Gender;
import entities.exeptions.*;
import entities.models.Address;
import entities.models.Classmate;
import entities.models.Course;
import entities.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import org.jetbrains.annotations.NotNull;

/**
 * @author alvaro Basilio
 * Classe de controller , utilizando padroes mvc
 * @version 1.0
 * @see javafx.fxml.Initializable
 */
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
	private Label errorNumber;
	
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

	/**
	 * Povoa o combo box de Cursos
	 */
	public void CourseList() {
		List<Course> courses = SaveCourses.getInstance().getRegister();
		lists = FXCollections.observableArrayList(courses);
		cursesOnComboBox.setItems(lists);
	}

	/**
	 * Formata o cpf utlizando uma mascara
	 */
	@FXML
	void cpfFormatador() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(fieldCpf);
		tff.formatter();

	}

	/**
	 * Formata o telefone utlizando uma mascara
	 */
	@FXML
	void telFormatador() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##)#####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(fieldTel);
		tff.formatter();
	}

	/**
	 * Retorna ao modulo de alunos
	 * @throws IOException
	 */
	@FXML
	void voltarMenu() throws IOException {
		Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/ClassmateModel.fxml"));
		AssistentScene.getScene(backButton,root);
	}

	/**
	 * Preenche os campos rua, bairro e cidade pelo cep ultilizando a api ViaCep
	 * @link https://viacep.com.br/
	 * @return boolean - atesta o preenchimento
	 */
	private boolean preencher(){
		try {
			errorCep.setVisible(false);
			ViaCEP vC = new ViaCEP(fieldCep.getText());
			fieldCity.setText(vC.getLocalidade());
			fieldDistrict.setText(vC.getBairro());
			fieldStreet.setText(vC.getLogradouro());
			return true;
		} catch (ViaCEPException e) {
			errorCep.setText(e.getMessage());
			errorCep.setVisible(true);
			return false;
		}
	}

	/**
	 * Valida os campos
	 */
	@FXML
	private void textValidate() {
		if (fieldCep.getText().length() == 8) {
			preencher();
		}
		if (registerValidateWithoutAdress())
			if (registerValidateAdress())
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

	/**
	 * Campos sem o endereço
	 * @return boolean verifica se os campos estao preenchidos
	 */
	private boolean registerValidateWithoutAdress(){
	    if (!fieldName.getText().isEmpty() && (fieldCpf.getText().length() == 14 || fieldCpf.getText().length() == 15)
				&& !fieldNickName.getText().isEmpty() && data.getValue() != null && cursesOnComboBox.getValue() != null)
	        return true;
	    return false;
    }

	/**
	 * Verifica o endereço
	 * @return boolean verifica se os campos estao preenchidos
	 */
	private boolean registerValidateAdress() {
        if (fieldCity.getText().isEmpty() && fieldCep.getText().isEmpty() && fieldDistrict.getText().isEmpty()
					&& fieldStreet.getText().isEmpty() && fieldNumber.getText().isEmpty())
            return true;
        return false;
    }

	/**
	 * Encaminha o registro do aluno
	 */
    @FXML
	private void register(){
		errorCpf.setVisible(false);
		errorName1.setVisible(false);
		errorName2.setVisible(false);
		errorDate.setVisible(false);
		errorNumber.setVisible(false);
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


			if(result[0] == 1) {
				if(!Validatefields.isOnlyNumbers(fieldNumber.getText()))
					throw new NumbersExeption("Número Inválido");
					
				registerWithAdressClassmate(result, name, cpf, date, course, aws);
			}else 
				registerWithoutAdressClassmate(result, name, cpf, date, course, aws);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Cadastro de alunos");
			alert.setHeaderText("O aluno foi cadastrado com sucesso!");
			alert.show();
			try {
				voltarMenu();
			}catch (Exception e){
				System.out.println(e);
			}
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
		}catch (NumbersExeption e) {
			errorNumber.setText(e.getMessage());
			errorNumber.setVisible(true);
		}catch (infoBancoExeption e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erro no banco de dados");
			alert.setHeaderText(e.getMessage());
			alert.show();
		}
	}

	/**
	 * Verifica as formas do construtor de alunos podendo ser:
	 * sendo 1 para se contem aquele campo e 0 para não contem
	 * @return int[] - array com imformações
	 */
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
		for(int a : result)
			System.out.println(a);
		return result;
	}

	/**
	 * Registra o aluno com endereço
	 * @param int[] array com as opcões de construção
	 * @param String o nome
	 * @param String cpf
	 * @param LocalDate nascimento
	 * @param Course curso
	 * @param Gender genero
	 * @throws infoBancoExeption
	 */
	private void registerWithAdressClassmate(@NotNull int[] op , String name, String cpf, LocalDate date, Course course, RadioButton gender) throws infoBancoExeption {
		Address address;
		Classmate classmate;
		SaveClassemate saveClassemate = SaveClassemate.getInstance();
		if(op[1]==1 && op[2]==1) {
			address = new Address(fieldCity.getText(), fieldDistrict.getText(), fieldStreet.getText(), 
				Integer.parseInt(fieldNumber.getText()), Integer.parseInt(fieldCep.getText()), fieldComplement.getText());

			ConnectJDCB.creatNewTable(ConnectJDCB.generateAdressTable());
			ConnectJDCB.insertAdress(address);
			SaveAdresses saveAdresses = SaveAdresses.getInstance();
			saveAdresses.add(address);
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course, address, fieldTel.getText());
			ConnectJDCB.creatNewTable(ConnectJDCB.generateClassmateTable());
			ConnectJDCB.insertClassmate(classmate);
			System.out.println(classmate.getRegistration());
			saveClassemate.add(classmate);
		}
		if(op[1]==1 && op[2]==0) {
			address = new Address(fieldCity.getText(), fieldDistrict.getText(), fieldStreet.getText(),
					Integer.parseInt(fieldNumber.getText()), Integer.parseInt(fieldCep.getText()), fieldComplement.getText());
			ConnectJDCB.creatNewTable(ConnectJDCB.generateAdressTable());
			ConnectJDCB.insertAdress(address);
			SaveAdresses saveAdresses = SaveAdresses.getInstance();
			saveAdresses.add(address);
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course, address, fieldTel.getText());
			ConnectJDCB.creatNewTable(ConnectJDCB.generateClassmateTable());
			ConnectJDCB.insertClassmate(classmate);
			System.out.println(classmate.getRegistration());
            saveClassemate.add(classmate);
		}
		
		if(op[1]==0 && op[2]==1) {
			address = new Address(fieldCity.getText(), fieldDistrict.getText(), fieldStreet.getText(), 
					Integer.parseInt(fieldNumber.getText()), Integer.parseInt(fieldCep.getText()));
			ConnectJDCB.creatNewTable(ConnectJDCB.generateAdressTable());
			ConnectJDCB.insertAdress(address);
			SaveAdresses saveAdresses = SaveAdresses.getInstance();
			saveAdresses.add(address);
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course, address, fieldTel.getText());
			ConnectJDCB.creatNewTable(ConnectJDCB.generateClassmateTable());
			ConnectJDCB.insertClassmate(classmate);
			System.out.println(classmate);
            saveClassemate.add(classmate);
		}
		
		if(op[1]==0 && op[2]==0) {
			address = new Address(fieldCity.getText(), fieldDistrict.getText(), fieldStreet.getText(), 
					Integer.parseInt(fieldNumber.getText()), Integer.parseInt(fieldCep.getText()));
			ConnectJDCB.creatNewTable(ConnectJDCB.generateAdressTable());
			ConnectJDCB.insertAdress(address);
			SaveAdresses saveAdresses = SaveAdresses.getInstance();
			saveAdresses.add(address);
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course, address);
			ConnectJDCB.creatNewTable(ConnectJDCB.generateClassmateTable());
			ConnectJDCB.insertClassmate(classmate);
			System.out.println(classmate);
            saveClassemate.add(classmate);
		}
	}

	/**
	 *Registra o aluno se endereço
	 * @param int[] array com as opcões de construção
	 * @param String o nome
	 * @param String cpf
	 * @param LocalDate nascimento
	 * @param Course curso
	 * @param Gender genero
	 * @throws infoBancoExeption
	 */
	private void registerWithoutAdressClassmate(int[] op ,String name, String cpf, LocalDate date, Course course, RadioButton gender) throws infoBancoExeption {
		Classmate classmate;
        SaveClassemate saveClassemate = SaveClassemate.getInstance();
		if(op[2] == 1) {
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course, fieldTel.getText());
			ConnectJDCB.creatNewTable(ConnectJDCB.generateClassmateTable());
			ConnectJDCB.insertClassmate(classmate);
            saveClassemate.add(classmate);
		}
		else {
			classmate = new Classmate(name, date, cpf, Gender.valueOf(gender.getId()), course);
			ConnectJDCB.creatNewTable(ConnectJDCB.generateClassmateTable());
			ConnectJDCB.insertClassmate(classmate);
            saveClassemate.add(classmate);
		}
	}
	/**
	 * Método que sobreescreve da interface Initialize, ela é executada quando a cena é carregada
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		register.setDisable(true);
		CourseList();
		}

}