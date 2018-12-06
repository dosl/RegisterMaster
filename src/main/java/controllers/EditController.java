package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    Button enterButton,cancelButton;
    @FXML
    TextField subjectIDField,subjectNameField;
    @FXML
    TableColumn colorColumn;
    public void initialize(){
        yearCombobox.getItems().add("1");
        yearCombobox.getItems().add("2");
        yearCombobox.getItems().add("3");
        yearCombobox.getItems().add("4");
        termCombobox.getItems().add("1");
        termCombobox.getItems().add("2");

        //set color failed
//        colorColumn.setCellFactory(column -> {
//            return new TableCell() {
//                @Override
//                protected void updateItem(LocalDate item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (item == null || empty) {
//                        setText(null);
//                        setStyle("");
//                    } else {
//                        // Format date.
//                        setText(myDateFormatter.format(item));
//
//                        // Style all dates in March with a different color.
//                        if (item.getMonth() == Month.MARCH) {
//                            setTextFill(Color.CHOCOLATE);
//                            setStyle("-fx-background-color: yellow");
//                        } else {
//                            setTextFill(Color.BLACK);
//                            setStyle("");
//                        }
//                    }
//                }
//            };
//        });
    }
    public void enterOnAction(ActionEvent actionEvent){

    }
    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        javafx.scene.control.Button button = (javafx.scene.control.Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        CreateController createController = loader.getController();
        createController.setSubject(subject);
        stage.show();
    }

    public void setSubject(Subject subject){this.subject=subject;}


}
