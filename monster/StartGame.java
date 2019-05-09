/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookie.monster;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Manar Nouh
 */
public class StartGame {
    private double Wlim, Hlim;
    private Player r;
    private List<Food> f = new ArrayList<>();
    private List<Bomb> b = new ArrayList<>();
    private List<superFood> sp = new ArrayList<>();
    private final Text T = new Text(Score());
    private static int score = 0;
    BorderPane root = new BorderPane();
    Stage primaryStage; 

    public StartGame(double Wlim, double Hlim, Stage primaryStage) throws Exception {
        this.Wlim = Wlim;
        this.Hlim = Hlim;
        this.primaryStage = primaryStage;      
        r = new Player(Wlim,Hlim);
        root.getChildren().add(r);
        root.setBottom(T);
        SwitchScenes(root);
        timer.start();
        r.requestFocus();
    }

    private void SwitchScenes(Pane p) {
        primaryStage.setTitle("Cookie Monster");
        primaryStage.setScene(new Scene(p,Wlim,Hlim));
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
            Timeline t = new Timeline(
            new KeyFrame(Duration.seconds(10), e->{
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

    public String Score() {
        return " " + score;
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
                            Duration.millis(750), new Line(r.getCenterX()+r.getRadius()/2,
                            i.getBoundsInParent().getMaxY(),
                            r.getCenterX()+r.getRadius()/2,550),i);
                        i.setPath(collect); 
                }
            }
        };
        return t; 
    }

    public final AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long CurrentNanoTime) {
                if(Math.random() < 0.001) {
                    Bomb b1;
                    try {
                        b1 = new Bomb((int)(Math.random()*Wlim), Hlim);
                        b.add(b1);
                        root.getChildren().add(b1);
                    } catch (Exception ex) {
                        Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if(Math.random() < 0.015){
                    Food f1;
                    try {
                        f1 = new Food((int)(Math.random()*Wlim), Hlim);
                        f.add(f1);
                        root.getChildren().add(f1);
                    } catch (Exception ex) {
                        Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    }
                if(Math.random() < 0.0015) {
                    superFood s1;
                    try {
                        s1 = new superFood((int)(Math.random()*Wlim), Hlim);
                        sp.add(s1);
                    root.getChildren().add(s1);   
                    } catch (Exception ex) {
                        Logger.getLogger(StartGame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                fed();
                cleanup();
                checkbomb();
                T.setText(Score());
                checksuper();

            }};   
}

