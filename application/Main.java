package application;





import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.models.Course;
import entities.models.Supplies;
import entities.models.Teacher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setX(0);
			primaryStage.setY(0);
			primaryStage.setWidth(800);
			primaryStage.setHeight(850);
			Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("../view/Sample.fxml"));
			Scene scene = new Scene(root,primaryStage.getWidth(),primaryStage.getHeight());
			scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.UNIFIED);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Gestão Escolar");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static List<Course> ComboBoxUpdate() {
		Teacher t = new Teacher("AA", new Date(), 18445d, "454", 'M');
		List<Course> c = new ArrayList<Course>();
		Supplies s = new Supplies("QQ", new Date(), new Date(), t);
		List<Supplies> SD= new ArrayList<Supplies>();
		SD.add(s);
		Course course = new Course("si", SD);
		c.add(course);
		return c;
	}
}
