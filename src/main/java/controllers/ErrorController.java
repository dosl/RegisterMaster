package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ErrorController {
    @FXML
    Button okButton;
    @FXML
    Label statusLabel;
//    public void initialize(){
//        statusLabel.setText("Please try again");
//    }
    public void okOnAction(ActionEvent actionEvent) throws IOException {
        javafx.scene.control.Button button = (javafx.scene.control.Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TimeView0.fxml"));
//        stage.setScene(new Scene((Parent) loader.load()));
//        TimeController timeController = loader.getController();
        stage.close();
    }
    public void setLabel(String msg){
        statusLabel.setText(msg);
    }

}
