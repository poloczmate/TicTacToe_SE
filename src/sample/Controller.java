package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.List;

public class Controller {
    private static String whoseNext = "X";
    private static javafx.scene.control.Label msgLabel = new Label();
    private static MapElement[][] Map = new MapElement[3][3];
    private static Map<String, String> map = new HashMap<String,String>();
    private static List<Highscore> highscore = new ArrayList<>();

    private static class MapElement extends StackPane {
        private Text text = new Text("");
        public MapElement(double x, double y, double TextSize, ChoiceBox bgcolor) {
            javafx.scene.shape.Rectangle r = new Rectangle(x,y);
            r.setStroke(Color.BLACK);
            if (bgcolor.getValue().toString().equals("White")) {
                r.setFill(null);
            } else if (bgcolor.getValue().toString().equals("Blue")) {
                r.setFill(Color.BLUE);
            } else if (bgcolor.getValue().toString().equals("Red")) {
                r.setFill(Color.RED);
            } else if (bgcolor.getValue().toString().equals("Green")){
                r.setFill(Color.GREEN);
            } else if (bgcolor.getValue().toString().equals("Yellow")) {
                r.setFill(Color.YELLOW);
            } else if(bgcolor.getValue().toString().equals("Gray")) {
                r.setFill(Color.GRAY);
            }

            text.setFont(javafx.scene.text.Font.font(TextSize));
            getChildren().addAll(r,text);
            setOnMouseClicked(event-> {
                if (!whoseNext.equals("")) {
                    if (getText().equals("")) {
                        setText(whoseNext);
                        if (!hasWinner()) {
                            if (!isDraw()){
                                if (whoseNext.equals("X")) {
                                    whoseNext = "O";
                                } else if (whoseNext.equals("O")) whoseNext = "X";
                                printMsg(map.get(whoseNext) + "'s turn!");
                            } else {
                                whoseNext = "";
                                printMsg("The game is a draw. Press the button to exit!");
                                //highscore draw
                                Highscore.addPointDraw(highscore,map);
                            }

                        } else {
                            printMsg(map.get(whoseNext) + " has won! Press the button to exit!");
                            //highscore
                            Highscore.addPointWin(whoseNext,highscore,map);
                            whoseNext = "";
                        }
                    } else {
                        printMsg("This field is not free! " + map.get(whoseNext) +"'s turn!");
                    }
                } else {
                    printMsg("The game is over! Press the button to exit!");
                }
            });
        }

        public void setText(String s) {
            text.setText(s);
        }

        public String getText(){
            return text.getText();
        }
    }

    public static boolean hasWinner() {
        for (int i = 0; i <3;i++) {
            if (Map[i][0].getText().equals(whoseNext)
                    && Map[i][1].getText().equals(whoseNext)
                    && Map[i][2].getText().equals(whoseNext)) return true;
        }

        for (int i = 0; i <3;i++) {
            if (Map[0][i].getText().equals(whoseNext)
                    && Map[1][i].getText().equals(whoseNext)
                    && Map[2][i].getText().equals(whoseNext)) return true;
        }
        if (Map[0][0].getText().equals(whoseNext)
                && Map[1][1].getText().equals(whoseNext)
                && Map[2][2].getText().equals(whoseNext)) return true;
        if (Map[0][2].getText().equals(whoseNext)
                && Map[1][1].getText().equals(whoseNext)
                && Map[2][0].getText().equals(whoseNext)) return true;
        return false;
    }

    public static void printMsg(String msg) {
        msgLabel.setText(msg);
    }

    public static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j<3;j++) {
                if (Map[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void startgame(RadioButton p1x, RadioButton p1o, RadioButton p2x, RadioButton p2o, TextField p1name, TextField p2name, ChoiceBox bgcolor) { // startbutton onmouseclicked
        if (!p1name.getText().equals("") || !p2name.getText().equals("")) {
            if ((p1o.isSelected() || p1x.isSelected()) && (p2o.isSelected() || p2x.isSelected())) {
                String r = (String) bgcolor.getValue();
                if (r != null) {
                    Highscore.importHighscore(highscore);
                    GridPane gp = new GridPane();
                    gp.setMinSize(600,700);
                    gp.setMaxSize(600,700);
                    msgLabel.setMinSize(600,40);
                    msgLabel.setAlignment(Pos.CENTER);
                    msgLabel.setFont(Font.font(30));
                    gp.add(msgLabel,0,0,3,1);

                    if (p1x.isSelected()) {
                        map.put("X",p1name.getText());
                        map.put("O",p2name.getText());
                    } else{
                        map.put("O",p1name.getText());
                        map.put("X",p2name.getText());
                    }

                    for (int i =0; i< 3;i++) {
                        MapElement m1 = new MapElement(200,200,72, bgcolor);
                        MapElement m2 = new MapElement(200,200,72, bgcolor);
                        MapElement m3 = new MapElement(200,200,72, bgcolor);
                        gp.addRow(i+2,m1,m2,m3);
                        Map[i][0] = m1;
                        Map[i][1] = m2;
                        Map[i][2] = m3;
                    }

                    Button button = new Button("Exit");
                    button.setMinSize(100,40);
                    button.setFont(Font.font(20));
                    gp.addRow(5,button);
                    Scene scene = new Scene(gp);
                    Stage gameStage = new Stage();
                    gameStage.setScene(scene);
                    gameStage.setTitle("TICTACTOE");
                    msgLabel.setText(map.get(whoseNext) + "'s turn!");
                    gameStage.show();
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                        for (int i = 0; i < 3; i++){
                            for (int j = 0; j< 3; j++){
                                Map[i][j].setText("");
                            }
                        }
                        whoseNext = "X";
                        printMsg(map.get(whoseNext) + "'s turn!");
                        gameStage.hide();
                        Highscore.exportHighscore(highscore);
                        }
                    });
                } else {
                    GUI.throwAlert("No choosen background!");
                }
            } else {
                GUI.throwAlert("No choosen figure!");
            }
        } else {
            GUI.throwAlert("No name entered!");
        }
    }
}