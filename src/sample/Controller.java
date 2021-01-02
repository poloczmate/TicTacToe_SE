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
import java.util.HashMap;
import java.util.Map;

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
    private TextField p1name;
    @FXML
    private TextField p2name;
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    private String whoseNext = "X";
    private javafx.scene.control.Label msgLabel = new Label();
    private MapElement[][] Map = new MapElement[3][3];
    private Map<String, String> map = new HashMap<String,String>();

    private class MapElement extends StackPane {
        private Text text = new Text("");
        public MapElement(double x, double y, double TextSize){
            javafx.scene.shape.Rectangle r = new Rectangle(x,y);
            r.setStroke(Color.BLACK);
            if (bgcolor.getValue().toString().equals("White")){
                r.setFill(null);
            }else if(bgcolor.getValue().toString().equals("Blue")){
                r.setFill(Color.BLUE);
            }else if(bgcolor.getValue().toString().equals("Red")){
                r.setFill(Color.RED);
            }else if(bgcolor.getValue().toString().equals("Green")){
                r.setFill(Color.GREEN);
            }else if(bgcolor.getValue().toString().equals("Yellow")){
                r.setFill(Color.YELLOW);
            }else if(bgcolor.getValue().toString().equals("Gray")){
                r.setFill(Color.GRAY);
            }
            text.setFont(javafx.scene.text.Font.font(TextSize));
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
                                printMsg(map.get(whoseNext) + "'s turn!");
                            }else {
                                whoseNext = "";
                                printMsg("The game is a draw. Press the button to exit!");
                            }
                        }else{
                            printMsg(map.get(whoseNext) + " has won! Press the button to exit!");
                            whoseNext = "";
                        }
                    }else{
                        printMsg("The field is not free! " + map.get(whoseNext) +"'s turn!");
                    }
                }else printMsg("The game is over! Press the button to exit!");
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

    @FXML
    private void startgame() { // startbutton onmouseclicked
        if (!p1name.getText().equals("") || !p2name.getText().equals("")){
            if ((p1o.isSelected() || p1x.isSelected()) && (p2o.isSelected() || p2x.isSelected())){
                String r = (String) bgcolor.getValue();
                if (r != null){
                    //here comes the code!
                    GridPane gp = new GridPane();
                    gp.setMinSize(600,700);
                    gp.setMaxSize(600,700);
                    msgLabel.setMinSize(600,40);
                    msgLabel.setAlignment(Pos.CENTER);
                    msgLabel.setFont(Font.font(30));
                    gp.add(msgLabel,0,0,3,1);
                    if (p1x.isSelected()){
                        map.put("X",p1name.getText());
                        map.put("O",p2name.getText());
                    }else{
                        map.put("O",p1name.getText());
                        map.put("X",p2name.getText());
                    }
                    for (int i =0; i< 3;i++){
                        MapElement m1 = new MapElement(200,200,72);
                        MapElement m2 = new MapElement(200,200,72);
                        MapElement m3 = new MapElement(200,200,72);
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
                        }
                    });
                }else{
                    alert.setContentText("No choosen background!");
                    alert.show();
                }
            }else {
                alert.setContentText("No choosen figure!");
                alert.show();
            }
        }else{
            alert.setContentText("No name entered!");
            alert.show();
        }
    }
}
