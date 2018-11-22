package controllers;

import databases.SubjectDBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Subject;

import javax.swing.text.TabableView;
import java.awt.*;
import java.io.IOException;

public class ResetController {

    private Subject subject;
    @FXML
    TableView tableView;
    @FXML
    Button backButton,resetButton;
    public void initialize(){
        tableView.setItems(SubjectDBConnector.getSubject());
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public void resetOnAction(ActionEvent actionEvent) throws IOException {
        //check previous subject before add
        SubjectDBConnector.resetSubject();
        tableView.refresh();
        backButtonOnAction(actionEvent);
    }
    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        javafx.scene.control.Button button = (javafx.scene.control.Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        HomeController homeController = loader.getController();
        homeController.setSubject(subject);
        stage.show();
    }
}
