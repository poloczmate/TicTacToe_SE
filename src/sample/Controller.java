package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;

import java.awt.*;

public class Controller {
    @FXML
    private ChoiceBox bgcolor;
    @FXML
    private Button startButton;
    @FXML
    private Button quitButton;

    @FXML
    private void quit() { //quitbutton onmouseclicked
           quitButton.getScene().getWindow().hide();
    }

    @FXML
    private void startgame(ActionEvent event) { // startbutton onmouseclicked
        //start the game
    }
}
