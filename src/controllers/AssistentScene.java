package controllers;

import entities.interfaces.SceneOperations;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author Alvaro Basilio
 * Metodo que carrega as cenas com o clik de botoes
 */
public class AssistentScene implements SceneOperations {
    public static void getScene(Button button, Parent root){
        Stage sc = (Stage) button.getScene().getWindow();
        sc.setHeight(button.getScene().getWindow().getHeight());
        sc.setWidth(button.getScene().getWindow().getWidth());
        Scene scene = new Scene(root, sc.getWidth(), sc.getHeight());
        sc.setScene(scene);
        sc.show();
    }
}

