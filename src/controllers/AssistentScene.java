package controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AssistentScene {
    public static void getScene(Button button, Parent root){
        Stage sc = (Stage) button.getScene().getWindow();
        sc.setHeight(button.getScene().getWindow().getHeight());
        sc.setWidth(button.getScene().getWindow().getWidth());
        Scene scene = new Scene(root, sc.getWidth(), sc.getHeight());
        sc.setScene(scene);
        sc.show();
    }
}

