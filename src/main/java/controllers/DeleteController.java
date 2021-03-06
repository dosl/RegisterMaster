package controllers;

import databases.SubjectDBConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Subject;

import java.io.IOException;

public class DeleteController {
    private Subject subject;
    @FXML
    ComboBox yearCombobox = new ComboBox();
    @FXML
    ComboBox termCombobox = new ComboBox();
    @FXML
    Button enterButton,cancelButton;
    @FXML
    TextField subjectIDField,subjectNameField;
    @FXML
    TableView tableView;
    @FXML
    private TableColumn<Subject,String> level;

    public void initialize(){
        level.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColor()));
        level.setCellFactory(column -> {
            return new TableCell<Subject, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty){
                        setText(null);
                        setStyle("");
                    }else {
                        setText(item);
                        setTextFill(Color.WHITE);
                        if (item.equalsIgnoreCase("hard")) {
                            setStyle("-fx-background-color: red");
                        }else if(item.equalsIgnoreCase("normal")){
                            setStyle("-fx-background-color: blue");
                        }else if(item.equalsIgnoreCase("easy")){
                            setStyle("-fx-background-color: green");
                        }
                    }
                }

            };

        });
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    DeleteController.this.onEdit();
                }
            }
        });
        yearCombobox.getItems().add("1");
        yearCombobox.getItems().add("2");
        yearCombobox.getItems().add("3");
        yearCombobox.getItems().add("4");
        termCombobox.getItems().add("1");
        termCombobox.getItems().add("2");
        tableView.setItems(SubjectDBConnector.getSubject());
    }
    public void onEdit(){
        // check the table's selected item and get selected item
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Subject subject = (Subject) tableView.getSelectionModel().getSelectedItem();
            subjectIDField.setText(subject.getSubjectID());
            subjectNameField.setText(subject.getSubjectName());
            yearCombobox.setValue(subject.getYear());
            termCombobox.setValue(subject.getYear());
        }
    }
    public void deleteOnAction(ActionEvent actionEvent) throws IOException {
        //check not null textField if null go to error page
        if (termCombobox.getValue() == "Please Select" || yearCombobox.getValue() == "Please Select" || subjectNameField.getText().equals("") || subjectIDField.getText().equals("")){
            Button button = (Button) actionEvent.getSource();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Error.fxml"));
            stage.setScene(new Scene((Parent) loader.load()));
            ErrorController errorController = loader.getController();
            errorController.setLabel("Please double click subject");
            stage.show();
        }else{
            SubjectDBConnector.deleteSubject(subjectIDField.getText());
            tableView.refresh();
            cancelOnAction(actionEvent);
        }

    }
    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        javafx.scene.control.Button button = (javafx.scene.control.Button) actionEvent.getSource();
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
