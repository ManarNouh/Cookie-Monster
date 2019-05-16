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


public class StartPane extends BorderPane{
    private Stage primaryStage;
    private double Wlim, Hlim;
    private Player r;
    private ArrayList<Food> f = new ArrayList<>();
    private ArrayList<Bomb> b = new ArrayList<>();
    private ArrayList<superFood> sp = new ArrayList<>();
    private ArrayList<GameObject> toRemove = new ArrayList<>();
    private Timeline timer;
    Image imgchoice;
    Image i = new Image("file:Pictures\\Retry.png");
    Image j = new Image("file:Pictures\\Back.png");
    Image k = new Image("file:Pictures\\Next.png");
    
    ImageView ret = new ImageView(i);
    ImageView back = new ImageView(j);
    ImageView next = new ImageView(k);
    
    private static double tf = 0;
    private Text T = new Text(),S = new Text();
    private int MaxScore = 20;
    private VBox v = new VBox();
    private HBox h = new HBox();

    public StartPane(double Wlim, double Hlim,Stage primaryStage, Image imgchoice)  {
        // set scene and Stage
        this.Wlim = Wlim;
        this.Hlim = Hlim;   
        this.primaryStage = primaryStage;
        switchScenes(this);
        // add player and lives
        r = new Player(Wlim,Hlim);
        getChildren().add(r);
        r.setImage(imgchoice);
        this.imgchoice = imgchoice;
        
        r.setLiv(h);
        // start game
//        Timeline t = new Timeline(new KeyFrame(Duration.millis(300),e->{
//            timer.stop();
//        }));
//        t.play();
        
        timer = new Timeline( 
                new KeyFrame(Duration.millis(5),e->{
                updateGame();}));
        timer.setCycleCount(6080);
        timer.play();
        r.requestFocus();
        // set retry, timer, score & lives 
        this.setBottom(T);
        T.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 22));
        S.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 22));
        v.getChildren().add(S);
        this.setRight(v);
        v.getChildren().add(h);
        this.setMargin(v,new Insets(5,0,0,0));
        
        
        }

    private void switchScenes(BorderPane p) {
        p.setBackground(Background.EMPTY);
        
        HBox v1 = new HBox();
        p.setLeft(v1);
        this.setMargin(v1,new Insets(5,0,0,0));
        v1.getChildren().addAll(ret,back);
        ret.setFitHeight(35);
        ret.setFitWidth(35);
        back.setFitHeight(35);
        back.setFitWidth(35);
        this.setMargin(v1,new Insets(5,0,0,0));
        
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
        if(Math.random() < 0.002) {
            Bomb b1 = new Bomb((int)(1 + Math.random()*(Wlim- 30)), Hlim);
            this.getChildren().add(b1);
            b.add(b1);
        }
        if(Math.random() < 0.004){
            Food f1 = new Food((int)(1 + Math.random()*(Wlim - 30)), Hlim);
            this.getChildren().add(f1);
            f.add(f1);
        }
        if(Math.random() < 0.00015) {
            superFood s1 = new superFood(1 + (int)(Math.random()*(Wlim - 45)), Hlim);
            this.getChildren().add(s1);
            sp.add(s1);
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
        
        T.setText(" " + r.getScore() + "/" + MaxScore);                
        timer.setOnFinished(e->{
            if(r.getScore() >= MaxScore)
                endGame("Won");
            else endGame("Lost");
        
        });
        if(r.getScore() >= MaxScore)
            endGame("Won");
        ret.setOnMouseClicked(e->{
            endGame("Retry");
            StartPane sp = new StartPane(Wlim,Hlim,primaryStage,imgchoice);
        });
        
        back.setOnMouseClicked(e->{
            endGame("GO BACK");
            CharactersPane cpane=new CharactersPane(primaryStage,Wlim,Hlim);
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
            p.setCenter(R);
        }
        if(L.equals("Won")){
            VBox v2 = new VBox();
            Button next = new Button("Next Level");
            next.setStyle("-fx-background-color: Beige");
            R.setText("You Won");
            next.setPrefSize(150,50);
            v2.getChildren().addAll(R,next);
            v2.setMargin(next,new Insets(20,0,0,30));
            p.setCenter(v2);
            p.setMargin(v2,new Insets(Hlim/2 - Hlim/8 ,0,0,Wlim/2 - Wlim/5));
            
            
        }
        R.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 56));
        
        tf = 0;
        r.setScore(0);
        switchScenes(p);
    }
}

