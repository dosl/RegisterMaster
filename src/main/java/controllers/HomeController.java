package controllers;

import databases.SubjectDBConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
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
    ComboBox yearCombobox;
    @FXML
    ComboBox termCombobox;
    @FXML
    Button createButton, reportButton,deleteButton,addButton,editButton,goButton;
    @FXML
    TableView<Subject> tableView;
    @FXML
    TableColumn IDSubject,nameSubject,year,term,previousSubject,status,color;
    @FXML
    private TableColumn<Subject,String> level;
//    @FXML
//    private MenuItem createMenuItems,editMenuItems;
    @FXML
    private Parent parent;

    private ObservableList<String> termList = FXCollections.observableArrayList("1", "2");
    private ObservableList<String> yearList = FXCollections.observableArrayList("1", "2", "3", "4");
    Alert errorAlert = new Alert(Alert.AlertType.WARNING, "You need to pass the previous subject", ButtonType.OK);
    Alert addAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to add this subject", ButtonType.OK, ButtonType.CANCEL);
    Alert resetAlert = new Alert(Alert.AlertType.CONFIRMATION,"All subject is reset",ButtonType.OK);
    Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this subject", ButtonType.OK, ButtonType.CANCEL);
    Alert comboBoxAlert = new Alert(Alert.AlertType.WARNING,"Please fill all combo box",ButtonType.OK);
    Alert addSameSubjectAlert = new Alert(Alert.AlertType.WARNING,"You have already regis this subject");
    public void initialize(){
        yearCombobox.setItems(yearList);
        termCombobox.setItems(termList);
        yearCombobox.setValue("Please Select");
        termCombobox.setValue("Please Select");
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
       // replaceBooleanToString();
    }
    public void showAllOnAction(ActionEvent actionEvent){
        tableView.setItems(SubjectDBConnector.getSubject());
        tableView.refresh();
        yearCombobox.setValue("Please select");
        termCombobox.setValue("Please select");
    }
    public void goOnAction(ActionEvent actionEvent){
        if (termCombobox.getValue() == "Please Select" || yearCombobox.getValue() == "Please Select"){
            comboBoxAlert.show();
        }else{

            tableView.setItems(SubjectDBConnector.getSelectYearTerm(Integer.parseInt(yearCombobox.getSelectionModel().getSelectedItem().toString()),Integer.parseInt(termCombobox.getSelectionModel().getSelectedItem().toString())));
            tableView.refresh();
        }
//        SubjectDBConnector.getSelectYearTerm(Integer.parseInt(yearCombobox.getSelectionModel().getSelectedItem().toString()),Integer.parseInt(termCombobox.getSelectionModel().getSelectedItem().toString()));
    }

//    public void createOnActionMenuItems(ActionEvent actionEvent) throws IOException{
//       // MenuItem menuItem = (MenuItem) actionEvent.getSource();
//        Stage stage = (Stage) parent.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateView.fxml"));
//        stage.setScene(new Scene((Parent) loader.load()));
//        CreateController createController = loader.getController();
//        createController.setSubject(subject);
//        stage.show();
//
//    }

//    public void editOnActionMenuItems(ActionEvent actionEvent) throws IOException{
//        Stage stage = (Stage) parent.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditView.fxml"));
//        stage.setScene(new Scene((Parent) loader.load()));
//        EditController editController = loader.getController();
//        editController.setSubject(subject);
//        stage.show();
//    }
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
        deleteButton.setDisable(true);
        addButton.setDisable(true);
    }
    public void addOnAction(ActionEvent event) throws IOException {

        Subject selectedSubject = tableView.getSelectionModel().getSelectedItem();
        String status = selectedSubject.isStatus();
        String preId = SubjectDBConnector.getPreviousId(selectedSubject.getSubjectID());
        boolean prestatus = SubjectDBConnector.isPreviousPassed(preId);

        Optional<ButtonType> result = addAlert.showAndWait();


        if (result.get().equals(ButtonType.OK)) {
            if (prestatus == false) {
                Optional<ButtonType> acceptError = errorAlert.showAndWait();
                if(acceptError.get().equals(ButtonType.OK)){
                    return;
                }

            } else {
                Subject subject = (Subject) tableView.getSelectionModel().getSelectedItem();
                SubjectDBConnector.addSubject(selectedSubject.getSubjectID(), selectedSubject.getSubjectName(), selectedSubject.getYear(), selectedSubject.getTerm());
                tableView.setItems(SubjectDBConnector.getSubject());
                tableView.refresh();
            }
        }

        if(status.equals("Pass") ) {
            Optional<ButtonType> addSameSubjectResult = addSameSubjectAlert.showAndWait();
            if (addSameSubjectResult.get().equals(ButtonType.OK)) {
                return;
            }

        }


        replaceBooleanToString();
        addButton.setDisable(true);
        deleteButton.setDisable(true);

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

    public void replaceBooleanToString(){
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            if (tableView.getSelectionModel().getSelectedItem().isStatus().equals("Pass")) {
                tableView.getSelectionModel().getSelectedItem().setStatus(false);
                tableView.refresh();

            } else {
                tableView.getSelectionModel().getSelectedItem().setStatus(true);
                tableView.refresh();
            }
        }
    }

    public void replaceBooleanWithString(){
        if(tableView.getSelectionModel().getSelectedItem() != null){

        }
    }

}
