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
import java.util.Optional;

public class HomeController {
    private Subject subject;
    @FXML
    Button createButton, reportButton,deleteButton,addButton,editButton;
    @FXML
    TableView<Subject> tableView;
    @FXML
    TableColumn IDSubject,nameSubject,year,term,previousSubject,status,color;
    @FXML
    private TableColumn<Subject,String> level;
    Alert errorAlert = new Alert(Alert.AlertType.WARNING, "You need to pass the previous subject", ButtonType.OK);
    Alert addAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to add this subject", ButtonType.OK, ButtonType.CANCEL);
    Alert resetAlert = new Alert(Alert.AlertType.CONFIRMATION,"All subject is reset",ButtonType.OK);
    Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this subject", ButtonType.OK, ButtonType.CANCEL);

    public void initialize(){
        Subject selectedSubject = tableView.getSelectionModel().getSelectedItem();
        addButton.setDisable(true);
        deleteButton.setDisable(true);
        tableView.setItems(SubjectDBConnector.getSubject());
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
                    if (tableView.getSelectionModel().getSelectedItem() != null) {
                        addButton.setDisable(false);
                        deleteButton.setDisable(false);
                    }
                }
            }
        });

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

    public void resetOnAction(ActionEvent event) throws IOException {
        resetAlert.show();
        SubjectDBConnector.resetSubject();
        tableView.setItems(SubjectDBConnector.getSubject());
        tableView.refresh();
    }
    public void deleteOnAction(ActionEvent event) throws IOException {
        Optional<ButtonType> result = deleteAlert.showAndWait();
        Subject selectedSubject = tableView.getSelectionModel().getSelectedItem();
        if (result.get().equals(ButtonType.OK)) {
            if (selectedSubject != null) {
                SubjectDBConnector.deleteSubject(selectedSubject.getSubjectID());
                tableView.setItems(SubjectDBConnector.getSubject());
            }
        }
        tableView.refresh();
    }
    public void addOnAction(ActionEvent event) throws IOException {

        Subject selectedSubject = tableView.getSelectionModel().getSelectedItem();
        String preId = SubjectDBConnector.getPreviousId(selectedSubject.getSubjectID());
        boolean prestatus = SubjectDBConnector.isPreviousPassed(preId);

        Optional<ButtonType> result = addAlert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            if(prestatus == false){
                errorAlert.showAndWait();
            }else {
                Subject subject = (Subject) tableView.getSelectionModel().getSelectedItem();
                SubjectDBConnector.addSubject(selectedSubject.getSubjectID(), selectedSubject.getSubjectName(), selectedSubject.getYear(), selectedSubject.getTerm());
                tableView.setItems(SubjectDBConnector.getSubject());
                tableView.refresh();
            }
        }

    }
    public void editOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditView.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        EditController editController = loader.getController();
        editController.setSubject(subject);
        stage.show();
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
