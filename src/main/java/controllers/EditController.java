package controllers;

import databases.SubjectDBConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import javax.swing.text.TabableView;
import java.io.IOException;

public class EditController {
    private Subject subject;
    @FXML
    ComboBox yearCombobox = new ComboBox();
    @FXML
    ComboBox termCombobox = new ComboBox();
    @FXML
    ComboBox levelComboBox = new ComboBox();
    @FXML
    Button enterButton, cancelButton;
    @FXML
    TextField subjectIDField, subjectNameField, preSubjectField;
    @FXML
    TableView tableView;
    private ObservableList<String> colorList = FXCollections.observableArrayList("hard", "normal", "easy");
    private ObservableList<String> termList = FXCollections.observableArrayList("1", "2");
    private ObservableList<String> yearList = FXCollections.observableArrayList("1", "2", "3", "4");
    @FXML
    private TableColumn<Subject, String> level;

    public void initialize() {
        subjectIDField.setVisible(false);
        level.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColor()));
        level.setCellFactory(column -> {
            return new TableCell<Subject, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);
                        setTextFill(Color.WHITE);
                        if (item.equalsIgnoreCase("hard")) {
                            setStyle("-fx-background-color: red");
                        } else if (item.equalsIgnoreCase("normal")) {
                            setStyle("-fx-background-color: blue");
                        } else if (item.equalsIgnoreCase("easy")) {
                            setStyle("-fx-background-color: green");
                        }
                    }
                }

            };

        });


        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    EditController.this.onEdit();
                }
            }
        });
        termCombobox.setItems(termList);
        termCombobox.setValue("Please Select");
        yearCombobox.setItems(yearList);
        yearCombobox.setValue("Please Select");
        levelComboBox.setItems(colorList);
        levelComboBox.setValue("Please Select");
        tableView.setItems(SubjectDBConnector.getSubject());
    }



    public void enterOnAction(ActionEvent actionEvent) throws IOException {
        if (termCombobox.getValue() == "Please Select" || yearCombobox.getValue() == "Please Select" || subjectNameField.getText().equals("") || subjectIDField.getText().equals("") || levelComboBox.getValue() =="Please Select") {
            Button button = (Button) actionEvent.getSource();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Error.fxml"));
            stage.setScene(new Scene((Parent) loader.load()));
            ErrorController errorController = loader.getController();
            errorController.setLabel("Please input all field");
            stage.show();
        } else {
            SubjectDBConnector.editSubject(subjectIDField.getText(),subjectNameField.getText(),yearCombobox.getValue().toString(),termCombobox.getValue().toString(),preSubjectField.getText(),levelComboBox.getValue().toString());
            tableView.refresh();
            cancelOnAction(actionEvent);
//            System.out.println(SubjectDBConnector.getPreviousId(subjectIDField.getText()));
//            System.out.println(SubjectDBConnector.isPreviousPassed(subjectIDField.getText()));
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
    public void onEdit() {
        // check the table's selected item and get selected item
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            Subject subject = (Subject) tableView.getSelectionModel().getSelectedItem();
            //System.out.println(subject.getPreviousSubject());
            subjectIDField.setText(subject.getSubjectID());
            subjectNameField.setText(subject.getSubjectName());
            yearCombobox.setValue(subject.getYear());
            termCombobox.setValue(subject.getTerm());
            levelComboBox.setValue(subject.getColor());
            preSubjectField.setText(subject.getPreviousSubject());


        }
    }


}
