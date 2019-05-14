package cookie.monster;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Manar Nouh
 */
public class StartPane {
    private double Wlim, Hlim;
    private Player r;
    private List<Food> f = new ArrayList<>();
    private List<Bomb> b = new ArrayList<>();
    private List<superFood> sp = new ArrayList<>();
    private final Text T = new Text(" "+ score);
    Text time= new Text();
    private static int score = 0;
    BorderPane root = new BorderPane();
    PauseTransition delay = new PauseTransition(Duration.seconds(30));
    AnimationTimer timer ;
    Stage primaryStage; 
    
    private void playSound(){
    AudioClip audioClip = new AudioClip(Paths.get("C:\\Users\\maria\\Downloads\\Cookie Monster Eating Cookie! Om nom nom!.mp3").toUri().toString());
        audioClip.play(5);
    }
    

    public StartPane(double Wlim, double Hlim,MouseEvent event) {
        this.Wlim = Wlim;
        this.Hlim = Hlim;   
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        r = new Player(Wlim,Hlim);
        root.getChildren().add(r);
        root.setBottom(T);
        SwitchScenes(root);
        timer = new AnimationTimer() {
            @Override
            public void handle(long CurrentNanoTime) {
                updateGame();
            }};  
        timer.start();
        delay.setOnFinished( event -> {if(checkWin()){
                    timer.stop();
                    root.getChildren().remove(r);
                    root.setCenter(win);}
                
                    else{
                             Text o = new Text(100,100,"You lose");
                            o.setFont(new Font(80));
                            o.setFill(Color.RED);
                            timer.stop();
                            root.getChildren().remove(r);
                            root.setCenter(o);
                            
                            }  });
            delay.play();
        r.requestFocus();
    }

    private void SwitchScenes(BorderPane p) {
        p.setBackground(Background.EMPTY);
        
        primaryStage.setTitle("Cookie Monster");
        Scene s = new Scene(p,Wlim,Hlim);
        s.setFill(Color.ANTIQUEWHITE);
        primaryStage.setScene(s);
        primaryStage.show();
    }

    private void checkbomb(){
        for(Bomb bomb: b){
            if(bomb.isCollided(r) == true) {
                timer.stop();
                r.setState(false);
            }
        }
    }

    private void checksuper() {
        for(superFood i : sp) {
            if(i.isCollided(r) == true) { 
            f.remove(i);
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
    }

    private void cleanup() {
        for(Food i : f) {
            if(i.checkBoundries(Hlim) == true){
                f.remove(i);
                root.getChildren().remove(i);
            }}
        for(Bomb bomb : b){
            if(bomb.checkBoundries(Hlim) == true){
                b.remove(bomb);
                root.getChildren().remove(bomb);}
            }
        for(superFood s : sp){
            if(s.checkBoundries(Hlim) == true){
                sp.remove(s);
                root.getChildren().remove(s);}
            }
    }
    
    private void fed() {
        for(Food i : f){
        if(i.isCollided(r) == true) { 
            f.remove(i);
            root.getChildren().remove(i);
            score++; }
        }
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
            Bomb b1;
            b1 = new Bomb((int)(Math.random()*Wlim), Hlim);
            b.add(b1);
            root.getChildren().add(b1);
        }
        if(Math.random() < 0.015){
            Food f1;
            f1 = new Food((int)(Math.random()*Wlim), Hlim);
            f.add(f1);
            root.getChildren().add(f1);
        }
        if(Math.random() < 0.0015) {
            superFood s1;
            s1 = new superFood((int)(Math.random()*Wlim), Hlim);
            sp.add(s1);
            root.getChildren().add(s1);
        }
    }
    
    private void updateGame(){
        generateObjects();
        fed();
        cleanup();
        checkbomb();
        time.setText("00:"+(30-(int)delay.getCurrentTime().toSeconds())+" ");
        time.setFont(new Font(25));
        time.setFill(Color.RED);
        time.setLayoutY(530);
        time.setLayoutX(450);
        T.setText(" " + score);
        checksuper();
    }
}

