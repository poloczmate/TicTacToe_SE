package sample;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class Main extends Application {
    private String whoseNext = "X";
    private Label msgLabel = new Label("X's turn!");
    private MapElement[][] Map = new MapElement[3][3];


    @Override
    public void start(Stage primaryStage) throws Exception{
        /*GridPane gp = new GridPane();
        gp.setMinSize(600,700);
        gp.setMaxSize(600,700);
        msgLabel.setMinSize(600,40);
        msgLabel.setAlignment(Pos.CENTER);
        msgLabel.setFont(Font.font(30));
        gp.add(msgLabel,0,0,3,1);
        //gp.addRow(1,msgLabel);
        for (int i =0; i< 3;i++){
            MapElement m1 = new MapElement(200,200,72);
            MapElement m2 = new MapElement(200,200,72);
            MapElement m3 = new MapElement(200,200,72);
            gp.addRow(i+2,m1,m2,m3);
            Map[i][0] = m1;
            Map[i][1] = m2;
            Map[i][2] = m3;
        }
        Button button = new Button("Restart");
        button.setMinSize(100,40);
        button.setFont(Font.font(20));
        gp.addRow(5,button);*/
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TICTACTOE");
        primaryStage.show();
        /*button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j< 3; j++){
                        Map[i][j].setText("");
                    }
                }
                whoseNext = "X";
                printMsg("New game started! X's turn!");
            }
        });*/
    }

    private class MapElement extends StackPane {
        private Text text = new Text("");
        public MapElement(double x, double y, double TextSize){
            Rectangle r = new Rectangle(x,y);
            r.setStroke(Color.BLACK);
            r.setFill(null);
            text.setFont(Font.font(TextSize));
            getChildren().addAll(r,text);
            setOnMouseClicked(event->{
                if (!whoseNext.equals("")){
                    if (getText().equals("")){
                        setText(whoseNext);
                        if(!hasWinner()){
                            if (!isDraw()){
                                if (whoseNext.equals("X")){
                                    whoseNext = "O";
                                }else if(whoseNext.equals("O")) whoseNext = "X";
                                printMsg(whoseNext + "'s turn!");
                            }else {
                                whoseNext = "";
                                printMsg("The game is a draw. With restart button you can start a new game!");
                            }
                        }else{
                            printMsg(whoseNext + " has won! New game with restart button!");
                            whoseNext = "";
                        }
                    }else{
                        printMsg("The field is not free! " + whoseNext +"'s turn!");
                    }
                }else printMsg("The game is over! You can start a new game with restart button!");
            });
        }
        public void setText(String s){
            text.setText(s);
        }
        public String getText(){
            return text.getText();
        }
    }

    public boolean hasWinner(){
        for (int i = 0; i <3;i++){
            if (Map[i][0].getText().equals(whoseNext)
                    && Map[i][1].getText().equals(whoseNext)
                    && Map[i][2].getText().equals(whoseNext)) return true;
        }
        for (int i = 0; i <3;i++){
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
    public void printMsg(String msg){
        msgLabel.setText(msg);
    }
    public boolean isDraw(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j<3;j++){
                if (Map[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
