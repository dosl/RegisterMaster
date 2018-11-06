package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Subject;

import java.io.IOException;

public class CreateController {
    private Subject subject;
    @FXML
    ComboBox yearCombobox = new ComboBox();
    @FXML
    ComboBox termCombobox = new ComboBox();
    @FXML
    Button enterButton,cancelButton;
    @FXML
    TextField subjectIDField,subjectNameField;
    public void initialize(){
        yearCombobox.getItems().add("1");
        yearCombobox.getItems().add("2");
        yearCombobox.getItems().add("3");
        yearCombobox.getItems().add("4");
        termCombobox.getItems().add("1");
        termCombobox.getItems().add("2");
    }
    public void enterOnAction(ActionEvent actionEvent){

    }
    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        HomeController homeController = loader.getController();
        homeController.setSubject(subject);
        stage.show();
    }



    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
