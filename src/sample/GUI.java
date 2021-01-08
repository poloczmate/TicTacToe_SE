package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI {
    @FXML
    private ChoiceBox bgcolor;
    @FXML
    private javafx.scene.control.Button quitButton;
    @FXML
    private RadioButton p1x;
    @FXML
    private RadioButton p1o;
    @FXML
    private RadioButton p2x;
    @FXML
    private RadioButton p2o;
    @FXML
    private javafx.scene.control.TextField p1name;
    @FXML
    private TextField p2name;
    private static Alert alert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private void quit() { //quitbutton onmouseclicked
        quitButton.getScene().getWindow().hide();
    }

    public void startgame(){
        Controller.startgame(p1x, p1o, p2x, p2o, p1name, p2name, bgcolor);
    }

    public static void throwAlert(String alertMsg){
        alert.setContentText(alertMsg);
        alert.show();
    }

    @FXML
    private void highscore() {
        try {
            File file = new File("hs.csv");
            Desktop.getDesktop().open(file);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void radiobuttonp1x() {
        if (p1x.isSelected()) {
            p2x.setSelected(false);
            p1o.setSelected(false);
            p2o.setSelected(true);
        }
    }

    @FXML
    private void radiobuttonp1o() {
        if (p1o.isSelected()) {
            p2x.setSelected(true);
            p2o.setSelected(false);
            p1x.setSelected(false);
        }
    }

    @FXML
    private void radiobuttonp2x() {
        if (p2x.isSelected()) {
            p1x.setSelected(false);
            p2o.setSelected(false);
            p1o.setSelected(true);
        }
    }

    @FXML
    private void radiobuttonp2o() {
        if (p2o.isSelected()) {
            p2x.setSelected(false);
            p1o.setSelected(false);
            p1x.setSelected(true);
        }
    }
}
