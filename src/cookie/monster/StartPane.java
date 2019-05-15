package cookie.monster;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
public class StartPane extends Pane {
    private double Wlim, Hlim;
    private Player r;
    private List<Food> f = new ArrayList<>();
    private List<Bomb> b = new ArrayList<>();
    private List<superFood> sp = new ArrayList<>();
    private List<GameObject> toRemove = new ArrayList<>();
    Button B = new Button("Retry");
    private Text T = new Text();
    private static int score = 0;
    private int lives=3;
    BorderPane root = new BorderPane();
    Timeline timer ;
    Stage primaryStage; 
    
    private void playSound(){
        AudioClip audio = new AudioClip(Paths.get("Sound\\eating.mp3").toUri().toString());
        audio.setPriority(1);
        if(audio.isPlaying() == true){
            audio.stop();
        }
        audio.play(5);
    }
    

    public StartPane(double Wlim, double Hlim,Stage primaryStage, Image imgchoice) {
        this.Wlim = Wlim;
        this.Hlim = Hlim;   
        this.primaryStage = primaryStage;
        r = new Player(Wlim,Hlim);
         r.setImage(imgchoice);
        root.getChildren().add(r);
        root.setBottom(T);
        this.getChildren().add(root);
        SwitchScenes(this);
        timer = new Timeline(
            new KeyFrame(Duration.millis(20),e->{
                updateGame();}));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        r.requestFocus();
    }

    private void SwitchScenes(BorderPane p) {
        p.setBackground(Background.EMPTY);
        primaryStage.setTitle("Cookie Monster");
        Scene s = new Scene(p,Wlim,Hlim);
        s.setFill(Color.CORNFLOWERBLUE);
        primaryStage.setScene(s);
        primaryStage.show();
    }

    private void checkbomb(){
        for(Bomb bomb: b){
            if(bomb.isCollided(r) == true) {
                lives--;
                if(lives==0){
                timer.stop();
                r.setState(false);
                BorderPane p = new BorderPane();
                Text R = new Text("Game Over" + "\n   Score:" + score );
                R.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 36));
                
                p.setCenter(R);
                SwitchScenes(p);}
            }
        }
    }

    private void checksuper() {
        for(superFood i : sp) {
            if(i.isCollided(r) == true) {
                toRemove.add(i);
                root.getChildren().remove(i);
                AnimationTimer collect=collectFood();
                collect.start();
                playSound();
                Timeline t = new Timeline(
                new KeyFrame(Duration.seconds(6), e->{
                    collect.stop();
                }));
                t.play();
            }
        }
        sp.removeAll(toRemove);
    }

    private void cleanup() {
        for(Food i : f) {
            if(i.checkBoundries(Hlim) == true){
                toRemove.add(i);
                root.getChildren().remove(i);
            }}
        f.removeAll(toRemove);
        for(Bomb bomb : b){
            if(bomb.checkBoundries(Hlim) == true){
                toRemove.add(bomb);
                root.getChildren().remove(bomb);}
            }
        b.removeAll(toRemove);
        
        for(superFood s : sp){
            if(s.checkBoundries(Hlim) == true){
                toRemove.add(s);
                root.getChildren().remove(s);}
            }
        sp.removeAll(toRemove);    
        
    }
    
    private void fed() {
        for(Food i : f){
        if(i.isCollided(r) == true) { 
            toRemove.add(i);
            root.getChildren().remove(i);
            score++; }
        }
        f.removeAll(toRemove);
    }
    
    private AnimationTimer collectFood(){
        AnimationTimer t=new AnimationTimer(){
            @Override
            public void handle(long now) {
                for(Food i: f){
                    PathTransition collect= new PathTransition( 
                            Duration.millis(750), new Line(r.getCenterX(),
                            i.getBoundriesY(),
                            r.getCenterX(),550),i);
                        i.setPath(collect); 
                }
            }
        };
        return t; 
    }
    
    private void generateObjects(){
        if(Math.random() < 0.001) {
            Bomb b1 = new Bomb((int)(1 + Math.random()*(Wlim- 30)), Hlim);
            b.add(b1);
            root.getChildren().add(b1);
        }
        if(Math.random() < 0.015){
            Food f1 = new Food((int)(1 + Math.random()*(Wlim - 30)), Hlim);
            f.add(f1);
            root.getChildren().add(f1);
        }
        if(Math.random() < 0.0015) {
            superFood s1 = new superFood(1 + (int)(Math.random()*(Wlim - 45)), Hlim);
            sp.add(s1);
            root.getChildren().add(s1);
        }
    }
    
    private void updateGame(){
        generateObjects();
        fed();
        cleanup();
        checkbomb();
        T.setText(" " + score);
        checksuper();
    }
}

