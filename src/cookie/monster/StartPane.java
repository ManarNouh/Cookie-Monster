package cookie.monster;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Manar Nouh
 */
public class StartPane extends BorderPane {
    private double Wlim, Hlim;
    private Player r;
    private ArrayList<Food> f = new ArrayList<>();
    private ArrayList<Bomb> b = new ArrayList<>();
    private ArrayList<superFood> sp = new ArrayList<>();
    private ArrayList<GameObject> toRemove = new ArrayList<>();
    private Text T = new Text();
    private static int score = 0;
    private Timeline timer ;
    Stage primaryStage; 
    Image imgchoice;
    Image i = new Image("file:Pictures\\Retry.png");
    Image j = new Image("file:Pictures\\Back.png");
    ImageView ret = new ImageView(i);
    ImageView back = new ImageView(j);
    
    private static double tf = 0;
    private Text T = new Text(),S = new Text();
    private int MaxScore = 20;
    private VBox v = new VBox();
    private HBox h = new HBox();

    public StartPane(double Wlim, double Hlim,Stage primaryStage, Image imgchoice, int choice) {
        this.Wlim = Wlim;
        this.Hlim = Hlim;   
        this.primaryStage = primaryStage;
        r = new Player(Wlim,Hlim);
        this.getChildren().add(r);
        r.setImage(imgchoice);
        this.imgchoice = imgchoice;
        this.setBottom(T);
        SwitchScenes(this);
        r.setLiv(h);
        timer = new Timeline(
            new KeyFrame(Duration.millis(5),e->{
                updateGame();}));
        timer.setCycleCount(6000);
        timer.play();
        r.requestFocus();
        
        T.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 14));
        S.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 14));
        v.getChildren().add(S);
        this.setRight(v);
        v.getChildren().add(h);
        this.setMargin(v,new Insets(5,0,0,0));
    }

    private void SwitchScenes(BorderPane p) {
        HBox v1 = new HBox();
        p.setLeft(v1);
        this.setMargin(v1,new Insets(5,0,0,0));
        v1.getChildren().addAll(ret,back);
        ret.setFitHeight(20);
        ret.setFitWidth(20);
        back.setFitHeight(20);
        back.setFitWidth(20);
        this.setMargin(v1,new Insets(5,0,0,0));
        
        p.setBackground(Background.EMPTY);
        primaryStage.setTitle("Cookie Monster");
        Scene s = new Scene(p,Wlim,Hlim);
        s.setFill(Color.CORNFLOWERBLUE);
        primaryStage.setScene(s);
        primaryStage.show();
    }


    private void cleanup() {
        for(Food i : f) {
            if(i.checkBoundries(Hlim) == true){
                toRemove.add(i);
                this.getChildren().remove(i);
            }}
        f.removeAll(toRemove);
        for(Bomb bomb : b){
            if(bomb.checkBoundries(Hlim) == true){
                toRemove.add(bomb);
                this.getChildren().remove(bomb);}
            }
        b.removeAll(toRemove);
        
        for(superFood s : sp){
            if(s.checkBoundries(Hlim) == true){
                toRemove.add(s);
                this.getChildren().remove(s);}
            }
        sp.removeAll(toRemove);    
        
    }
    
    private void generateObjects(){
        if(Math.random() < 0.001) {
            Bomb b1 = new Bomb((int)(1 + Math.random()*(Wlim- 30)), Hlim);
            b.add(b1);
            this.getChildren().add(b1);
        }
        if(Math.random() < 0.015){
            Food f1 = new Food((int)(1 + Math.random()*(Wlim - 30)), Hlim, choice);
            f.add(f1);
            this.getChildren().add(f1);
        }
        if(Math.random() < 0.0015) {
            superFood s1 = new superFood(1 + (int)(Math.random()*(Wlim - 45)), Hlim);
            sp.add(s1);
            this.getChildren().add(s1);
        }
    }
    
    private void updateGame(){
        generateObjects();
        r.checkFood(f,this);
        r.checksuper(sp, f, this);
        cleanup();
        r.checkBomb(b, this, h);
        if(r.isAlive() == false){
            endGame("Lost");
        }
        
        T.setText(" " + r.getScore());                
        timer.setOnFinished(e->{
            if(r.getScore() == MaxScore)
                endGame("Won");
            else endGame("Lost");
        
        });
        if(r.getScore() == MaxScore)
            endGame("Won");
        ret.setOnMouseClicked(e->{
            endGame("Retry");
            StartPane sp = new StartPane(500,500,primaryStage,imgchoice);
        });
        
        back.setOnMouseClicked(e->{
            endGame("GO BACK");
            CharactersPane cpane=new CharactersPane(primaryStage,500,500);
        });
        
        tf++;
        float time;
        time = ((float)((timer.getCycleCount())*0.005) - (float)(tf*0.005));
        DecimalFormat df = new DecimalFormat("#.0");
        S.setText("Time Left: " + " " + df.format(time));
    }
    
     private void endGame(String L){
        timer.stop();
        this.getChildren().clear();
        Text R = new Text();
        BorderPane p = new BorderPane();
        r.setLives(3);
        
        if(L.equals("Lost")){
            R.setText("Game Over" + "\n   Score:" + r.getScore() );
        }
        if(L.equals("Won")){
            R.setText("You Won");
        }
        R.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 36));
        p.setCenter(R);
        tf = 0;
        r.setScore(0);
        switchScenes(p);
    }
}

