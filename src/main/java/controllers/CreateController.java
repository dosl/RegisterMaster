package controllers;

import databases.SubjectDBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ComboBox yearCombobox;
    @FXML
    ComboBox termCombobox;
    @FXML
    ComboBox colorBox;
    @FXML
    Button enterButton, cancelButton;
    @FXML
    TextField subjectIDField, subjectNameField, previousSubjectField;
    private ObservableList<String> colorList = FXCollections.observableArrayList("red", "yellow", "green");

    public void initialize() {
        yearCombobox.getItems().add("1");
        yearCombobox.getItems().add("2");
        yearCombobox.getItems().add("3");
        yearCombobox.getItems().add("4");
        termCombobox.getItems().add("1");
        termCombobox.getItems().add("2");
        colorBox.setItems(colorList);
        colorBox.setValue("Please Select");

    }

    public void enterOnAction(ActionEvent actionEvent) throws IOException {
        //check not null textField if null go to error page
        if (subjectIDField.getText() != null && subjectNameField.getText() != null && yearCombobox.getValue().toString() != null && termCombobox.getValue().toString() != null) {
            SubjectDBConnector.createSubject(subjectIDField.getText(), subjectNameField.getText(), yearCombobox.getValue().toString(), termCombobox.getValue().toString(), previousSubjectField.getText(), false, colorBox.getValue().toString());
            cancelOnAction(actionEvent);
        } else {
            Button button = (Button) actionEvent.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Error.fxml"));
            stage.setScene(new Scene((Parent) loader.load()));
            ErrorController errorController = loader.getController();
            stage.show();
        }


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
