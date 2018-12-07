package controllers;

import databases.SubjectDBConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Subject;

import java.io.IOException;

public class HomeController {
    private Subject subject;
    @FXML
    Button createButton, reportButton,deleteButton,addButton,editButton;
    @FXML
    TableView tableView;
    @FXML
    TableColumn IDSubject,nameSubject,year,term,previousSubject,status,color;
    @FXML
    private TableColumn<Subject,String> level;


    public void initialize(){
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
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ResetView.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        ResetController resetController = loader.getController();
        resetController.setSubject(subject);
        stage.show();
    }
    public void deleteOnAction(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DeleteView.fxml"));
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
