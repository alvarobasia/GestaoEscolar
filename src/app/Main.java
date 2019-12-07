package app;





import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import entities.enums.Gender;
import entities.enums.Nomination;
import entities.exeptions.infoBancoExeption;
import entities.models.Course;
import entities.models.Supplies;
import entities.models.Teacher;
import entities.services.ConnectJDCB;
import entities.services.SaveSupplie;
import entities.services.SaveTeachers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            primaryStage.setX(bounds.getMinX());
            primaryStage.setY(bounds.getMinY());
            primaryStage.setWidth(bounds.getWidth());
            primaryStage.setHeight(bounds.getHeight());
            primaryStage.getIcons().add(new Image("file:C:\\Users\\alvar\\IdeaProjects\\trabalhofinal\\src\\images\\es.png"));
            Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("../view/Sample.fxml"));
            Scene scene = new Scene(root,primaryStage.getWidth(),primaryStage.getHeight());
            scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
            //primaryStage.initStyle(StageStyle.UNIFIED);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestão Escolar");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws infoBancoExeption, SQLException {
        String[] table = {ConnectJDCB.generateCourseTable(), ConnectJDCB.generateTeacherTable(), ConnectJDCB.generateClassmateTable(),
                ConnectJDCB.generateSuppliesTable(), ConnectJDCB.generateAdressTable(), ConnectJDCB.generateClassroomTable()};
        for (String s: table) {
            ConnectJDCB.creatNewTable(s);
        }
        try {
            ConnectJDCB.getAllTeachers();
        } catch (entities.exeptions.infoBancoExeption infoBancoExeption) {
            infoBancoExeption.printStackTrace();
        }
        ConnectJDCB.getAllAdress();
        ConnectJDCB.getAllCourses();
        ConnectJDCB.getAllSupplies();
        ConnectJDCB.getAllClassmates();
        for (Teacher R : SaveTeachers.getInstance().getRegister())
            System.out.println("qq"+R.getName());

        for (Supplies R : SaveSupplie.getInstance().getRegister())
            System.out.println("oo"+R.getSupplieID());
        launch(args);
    }
}
