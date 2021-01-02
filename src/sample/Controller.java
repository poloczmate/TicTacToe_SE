package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

import java.awt.*;

public class Controller {
    @FXML
    private ChoiceBox bgcolor;
    @FXML
    private Button startButton;
    @FXML
    private Button quitButton;
    @FXML
    private RadioButton p1x;
    @FXML
    private RadioButton p1o;
    @FXML
    private RadioButton p2x;
    @FXML
    private RadioButton p2o;

    @FXML
    private void quit() { //quitbutton onmouseclicked
           quitButton.getScene().getWindow().hide();
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
            p2o.setSelected(false);
            p1o.setSelected(true);
            p2o.setSelected(false);
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

    @FXML
    private void startgame() { // startbutton onmouseclicked
        //start the game
    }
}
