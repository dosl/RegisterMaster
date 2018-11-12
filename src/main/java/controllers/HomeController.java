package controllers;

import databases.SubjectDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Subject;

import java.io.IOException;

public class HomeController {
    private Subject subject;
    @FXML
    Button createButton, reportButton,deleteButton,addButton;
    @FXML
    TableView tableView;
    @FXML
    TableColumn IDSubject,nameSubject,year,term,previousSubject,status,color;


    public void initialize(){
        tableView.setItems(SubjectDBConnector.getSubject());
        System.out.println(SubjectDBConnector.getSubject());
    }

    public void createOnAction(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateView.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        CreateController createController = loader.getController();
        createController.setSubject(subject);
        stage.show();
    }

    public void reportOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReportView.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        ReportController reportController = loader.getController();
        reportController.setSubject(subject);
        stage.show();
    }
    public void deleteOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReportView.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        DeleteController deleteController = loader.getController();
        deleteController.setSubject(subject);
        stage.show();
    }
    public void addOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddView.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        AddController addController = loader.getController();
        addController.setSubject(subject);
        stage.show();
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
