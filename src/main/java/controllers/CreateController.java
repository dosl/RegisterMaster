package controllers;

import databases.SubjectDBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Subject;

import java.io.IOException;
import java.util.Optional;

public class CreateController {
    private Subject subject;
    @FXML
    ComboBox yearCombobox;
    @FXML
    ComboBox termCombobox;
    @FXML
    ComboBox levelComboBox;
    @FXML
    Button enterButton, cancelButton;
    @FXML
    TextField subjectIDField, subjectNameField, previousSubjectField;
    private ObservableList<String> colorList = FXCollections.observableArrayList("hard", "normal", "easy");
    private ObservableList<String> termList = FXCollections.observableArrayList("1", "2");
    private ObservableList<String> yearList = FXCollections.observableArrayList("1", "2", "3", "4");
    Alert errorAlert = new Alert(Alert.AlertType.WARNING, "Please fill all text field", ButtonType.OK);

    public void initialize() {
        yearCombobox.setItems(yearList);
        termCombobox.setItems(termList);
        levelComboBox.setItems(colorList);
        levelComboBox.setValue("Please Select");
        yearCombobox.setValue("Please Select");
        termCombobox.setValue("Please Select");


    }

    public void enterOnAction(ActionEvent actionEvent) throws IOException {
        //check not null textField if null go to error page
        String isinDatabase = SubjectDBConnector.getId(subjectIDField.getText());
        if (termCombobox.getValue() == "Please Select" || yearCombobox.getValue() == "Please Select" || levelComboBox.getValue() == "Please Select" || subjectNameField.getText().equals("") || subjectIDField.getText().equals("")) {
//            Button button = (Button) actionEvent.getSource();
//////            Stage stage = new Stage();
//////            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Error.fxml"));
//////            stage.setScene(new Scene((Parent) loader.load()));
//////            ErrorController errorController = loader.getController();
//////            errorController.setLabel("Please input all field");
//////            stage.show();
            errorAlert.showAndWait();
        } else {
            if(!isinDatabase.equals(subjectIDField.getText())) {
                SubjectDBConnector.createSubject(subjectIDField.getText(), subjectNameField.getText(), yearCombobox.getValue().toString(), termCombobox.getValue().toString(), previousSubjectField.getText(), false, levelComboBox.getValue().toString());
                cancelOnAction(actionEvent);
            }
            else{
                Alert idindatabaseAlert = new Alert(Alert.AlertType.WARNING,"This ID is already in Database");
                idindatabaseAlert.show();
                //System.out.println("GG");
            }
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
