package app;




import java.sql.SQLException;
import entities.exeptions.infoBancoExeption;
import entities.services.ConnectJDCB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * Esta classe carrega as informações do lauch do java fx
 * @see javafx.application.Application
 * @author alvaro Basilio
 */
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
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestão Escolar");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Este é p clinete , pega o lauch do java fx e exibe na tela alem de carregar os bancos e gerar as tabelas
     * utilizando o JDBC
     * @throws infoBancoExeption
     */
    public static void main(String[] args) throws infoBancoExeption, SQLException {
        String[] table = {ConnectJDCB.generateCourseTable(), ConnectJDCB.generateTeacherTable(), ConnectJDCB.generateClassmateTable(),
                ConnectJDCB.generateSuppliesTable(), ConnectJDCB.generateAdressTable(), ConnectJDCB.generateClassroomTable(),
        ConnectJDCB.generateGradeTable(), ConnectJDCB.generateRelashipTable()};
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
        ConnectJDCB.getAllClassrooms();
        ConnectJDCB.getAllGrades();
        launch(args);
    }
}
