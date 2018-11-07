package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Subject;

import javax.swing.text.TabableView;
import java.awt.*;
import java.io.IOException;

public class DeleteController {
    private Subject subject;
    @FXML
    TabableView tabableView;
    @FXML
    Button backButton,deleteButton;
    public void deleteOnAction(ActionEvent actionEvent){

    }
    public void backOnAction(ActionEvent actionEvent) throws IOException {
        javafx.scene.control.Button button = (javafx.scene.control.Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeView.fxml"));
        stage.setScene(new Scene((Parent) loader.load()));
        CreateController createController = loader.getController();
        createController.setSubject(subject);
        stage.show();
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
