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

    public static void main(String[] args) throws infoBancoExeption {
        String[] table = {ConnectJDCB.generateTeacherTable(), ConnectJDCB.generateClassmateTable(),
                ConnectJDCB.generateSuppliesTable(), ConnectJDCB.generateAdressTable()};
        for (String s: table) {
            ConnectJDCB.creatNewTable(s);
        }
        teacher();
        launch(args);
    }

    @NotNull
    public static List<Course> ComboBoxUpdate() {
        SaveTeachers saveTeachers = SaveTeachers.getInstance();
        Teacher t = saveTeachers.getTeacher(0);
        List<Course> c = new ArrayList<Course>();
        Supplies s = new Supplies("QQ","q", t, 50, 8);
        List<Supplies> SD= new ArrayList<Supplies>();
        SD.add(s);
        Course course = new Course("Sistemas de informação", SD);
        c.add(course);
        return c;
    }

    public static void teacher() throws infoBancoExeption {
        Teacher t = new Teacher("AA", LocalDate.now(), 18445f, "454", Gender.valueOf("FEMININO"), Nomination.BACHARELADO);
        SaveTeachers saveTeachers = SaveTeachers.getInstance();
        ConnectJDCB.insertTeacher(t);
        saveTeachers.add(t);
    }


}
