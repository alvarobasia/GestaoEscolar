package controllers;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfVersion;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.pdf.action.PdfTarget;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import entities.enums.Situation;
import entities.models.*;
import entities.services.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.w3c.dom.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CollegeController implements Initializable {
    @FXML
    private AnchorPane ba;

    @FXML
    private Label texts;

    @FXML
    private TableView<Grades> tabela;

    @FXML
    private TableColumn<Grades, String> nome;

    @FXML
    private TableColumn<Grades, String> matricula;

    @FXML
    private TableColumn<Grades, Integer> gep;

    @FXML
    private TableColumn<Grades, Float> nota;

    @FXML
    private TableColumn<Grades, Situation> sit;

    @FXML
    private Button registrar;

    @FXML
    private Label texts1;

    @FXML
    private ComboBox<Classroom> disci;

    @FXML
    private Label texts11;

    @FXML
    private Button deletar;

    @FXML
    private Pane lateralBar;

    @FXML
    private Button backButton;

    @FXML
    private Button info;

    private ObservableList<Grades> list;

    private ObservableList<Classroom> sup;

    private void fillList(String sala){
        List<Grades> c = SaveGrades.getInstance().getRegister();
        list =  FXCollections.observableArrayList(c);
        for(Grades g: list) {
            if(g.getSala().equals(sala))
                tabela.getItems().add(g);
        }
    }

    private void fillListView(){
        SaveClassrooms saveClassrooms = SaveClassrooms.getInstance();
        List<Classroom> t =  saveClassrooms.getRegister();
        sup  = FXCollections.observableArrayList(t);
        for(Classroom s : sup){
            disci.getItems().add(s);
        }
    }

    @FXML
    void textValidate( ) {

    }

    @FXML
    void validate(){
        if(disci.getValue() != null)
            initTable(disci.getValue().getID());
    }
    @FXML
    void initTable(String sala){
        fillList(sala);
        nome.setCellValueFactory(new PropertyValueFactory<>("name"));
        matricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        gep.setCellValueFactory(new PropertyValueFactory<>("gap"));
        nota.setCellValueFactory(new PropertyValueFactory<>("grade"));
        sit.setCellValueFactory(new PropertyValueFactory<>("situation"));
        editCols();
    }

    void editCols(){
        nome.setCellFactory(TextFieldTableCell.forTableColumn());
        nome.setEditable(false);
        matricula.setCellFactory(TextFieldTableCell.forTableColumn());
        matricula.setEditable(false);
        gep.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        gep.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setGap(e.getNewValue());
        });
        nota.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        nota.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setGrade(e.getNewValue());
        });
    }

    @FXML
    void voltarMenu() throws IOException {
        Parent root = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Sample.fxml"));
        AssistentScene.getScene(backButton,root);
    }

    @FXML
    void register( ) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillListView();
        tabela.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        deletar.setOnAction(e ->{
            ObservableList<Grades> list = tabela.getSelectionModel().getSelectedItems();
            for (Grades c : list) {
                tabela.getItems().remove(c);
                SaveGrades.getInstance().getRegister().remove(c);
                try {
                    ConnectJDCB.deleteGrade(c);
                } catch (entities.exeptions.infoBancoExeption infoBancoExeption) {
                    infoBancoExeption.printStackTrace();
                }
                SaveGrades.getInstance().getRegister().remove(c);
            }
            tabela.getSelectionModel().clearSelection();
        });
        registrar.setOnAction(e ->{
            Grades grades = tabela.getSelectionModel().getSelectedItem();
            ObservableList<TablePosition> tp = tabela.getSelectionModel().getSelectedCells();
            List<Grades> L = SaveGrades.getInstance().getRegister();
            for (TablePosition d : tp) {
                Integer gap = tabela.getItems().get(d.getRow()).getGap();
                Float nota = tabela.getItems().get(d.getRow()).getGrade();
                for(Grades g: L){
                    if(g.equals(grades)){
                        grades.setGap(gap);
                        grades.setGrade(nota);
                        if(gap < grades.getDisciplina().getMaxGap() && nota > grades.getDisciplina().getAprovedGrade())
                            grades.setSituation(Situation.APROVADO);
                        else
                            grades.setSituation(Situation.REPROVADO);
                        sit.setEditable(true);
                        sit.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Situation>() {
                            @Override
                            public String toString(Situation s) {
                                return s.toString();
                            }

                            @Override
                            public Situation fromString(String s) {
                                return Situation.valueOf(s);
                            }

                        }));
                        sit.setOnEditStart(y ->{
                            y.getTableView().getItems().get(y.getTablePosition().getRow()).setSituation(grades.getSituation());
                        });
                        try {
                            ConnectJDCB.updateGrade(grades);
                        } catch (entities.exeptions.infoBancoExeption infoBancoExeption) {
                            infoBancoExeption.printStackTrace();
                        }
                        break;
                    }
                }
            }

        });
        info.setOnAction(e->{
            Classmate c = null;
            Grades grades = tabela.getSelectionModel().getSelectedItem();
            List<Classmate> li = SaveClassemate.getInstance().getRegister();
            List<Grades> gra = SaveGrades.getInstance().getRegister();
            List<Supplies> st = SaveSupplie.getInstance().getRegister();
            Supplies de = null;
            for(Classmate d: li){
                if(d.getRegistration().equals(grades.getMatricula())){
                    c = d;
                    break;
                }
            }
            try {
                PdfDocument pdfDocument = new PdfDocument(new PdfWriter("C:\\Users\\alvar\\Documents\\info\\"+c.getName()+".pdf"));
                pdfDocument.setTagged();
                Document doc = new Document(pdfDocument);
                Paragraph p = new Paragraph("INFORMAÇÕES DO ALUNO");
                p.setFontSize(18);
                doc.add(p);
                doc.add(new Paragraph("---------------------------"));
                doc.add(new Paragraph("Nome: "+ c.getName()));
                doc.add(new Paragraph("Matrícula: "+ c.getRegistration()));
                doc.add(new Paragraph("Nascimento: "+ c.getBirthDate().toString()));
                float [] pointColumnWidths = {90F, 90F, 90F, 90f, 90f, 90f};
                Table table = new Table(pointColumnWidths);
                Cell mat = new Cell();
                mat.add(new Paragraph("Materia"));
                table.addCell(mat);
                Cell turma = new Cell();
                turma.add(new Paragraph("Turma"));
                table.addCell(turma);
                Cell professor = new Cell();
                professor.add(new Paragraph("Professor"));
                table.addCell(professor);
                Cell notas = new Cell();
                notas.add(new Paragraph("Notas"));
                table.addCell(notas);
                Cell faltas = new Cell();
                faltas.add(new Paragraph("Faltas"));
                table.addCell(faltas);
                Cell situacao = new Cell();
                situacao.add(new Paragraph("Situação"));
                table.addCell(situacao);
                for(Grades g: gra){
                    System.out.println(g.getMatricula() + c.getRegistration());
                    if(g.getMatricula().equals(c.getRegistration())){
                        System.out.println("qq");
                        Cell c1 = new Cell();
                        for(Supplies s : st){
                            if(s == g.getDisciplina()){
                                de = s;
                            }
                        }
                        c1.add(new Paragraph(de.getSupplieName()));
                        table.addCell(c1);
                        Cell c2 = new Cell();
                        c2.add(new Paragraph(g.getSala()));
                        table.addCell(c2);
                        Cell c3 = new Cell();
                        c3.add(new Paragraph(de.getTeacher().getName()));
                        table.addCell(c3);
                        Cell c4 = new Cell();
                        c4.add(new Paragraph(g.getGrade().toString()));
                        table.addCell(c4);
                        Cell c5 = new Cell();
                        c5.add(new Paragraph(g.getGap().toString()));
                        table.addCell(c5);
                        Cell c6 = new Cell();
                        c6.add(new Paragraph(g.getSituation().toString()));
                        table.addCell(c6);
                    }
                }
                doc.add(table);
                doc.close();
                Desktop.getDesktop().open(new File("C:\\Users\\alvar\\Documents\\info\\"+c.getName()+".pdf"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        });
    }
}
