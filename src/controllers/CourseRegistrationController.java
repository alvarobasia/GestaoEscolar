package controllers;

import entities.exeptions.NumbersExeption;
import entities.services.Validatefields;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class CourseRegistrationController implements Initializable {
    @FXML
    private Label texts;

    @FXML
    private Label course;

    @FXML
    private Label seme;

    @FXML
    private Label erroName;

    @FXML
    private Label erroNum;

    @FXML
    private TextField fieldcourse;

    @FXML
    private TextField fieldseme;

    @FXML
    private Button cadastro;

    @FXML
    private Button back;

    @FXML
    private void textValidate(){
        System.out.println(validator());
        if(validator())
            cadastro.setDisable(false);
    }

    private boolean validator(){
        return !fieldseme.getText().isEmpty() && !fieldcourse.getText().isEmpty();
    }

    @FXML
    private void register(){
        erroName.setVisible(false);
        erroNum.setVisible(false);
        try {
            if(Validatefields.isOnlyNumbers(fieldseme.getText()))
                throw new NumbersExeption("Digite apenas números");
        }catch (NumbersExeption e ) {
            erroNum.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        erroName.setVisible(false);
        erroNum.setVisible(false);
        cadastro.setDisable(true);
    }
}
