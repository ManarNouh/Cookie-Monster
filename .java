package javafxapplication6;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import static javafx.application.Application.launch;
import javafx.scene.text.Text;

class Player extends Rectangle {
    
    public Player( double Wlim, double Hlim) {
        double x = Wlim/2;
        double y = Hlim - Hlim/8;
        setX(x);
        setY(y);
        setWidth(50);
        setHeight(20);
    }
    
    public void getBigger() {
        setWidth(getWidth() + 10);
    }
}

class Bomb extends Circle {       
    public Bomb(int r){
        super(r,0,10);
        PathTransition p= new PathTransition(Duration.millis(1500),
                new Line(r,0,r,550),this);
        p.setCycleCount(1);
        p.play();  
    }  
}

class Food extends Rectangle {  
    public PathTransition p;
    public Food(int r) {
        super(r,0,10,10);
        p = new PathTransition(Duration.millis(1500),
                new Line(r,0,r,550),this);
        p.setCycleCount(1);
        p.play();
    } 
    

    
}

class superFood extends Food {
    
    public superFood(int r) {
        super(r);
        p = new PathTransition(Duration.millis(1500),
                new Line(r,0,r,550),this);
        p.setCycleCount(1);
        p.play();
        setWidth(70);
    }
    
}

public class JavaFXApplication6 extends Application {
    private List<Food> f = new ArrayList<>();
    private List<Bomb> b = new ArrayList<>();
    private List<superFood> sp = new ArrayList<>();
    Player r = new Player(500,550);
    private final Text T = new Text();
    private static int score = 0;
    BorderPane root = new BorderPane();
    int k = 10;
      
    private void checkbomb(){
        Text o = new Text(100,100,"Game Over");
              
        for(Bomb bomb: b){
            if(bomb.getBoundsInParent().intersects(r.getBoundsInParent())){
                r.setFill(Color.RED);
            timer.stop();
            root.getChildren().removeAll(r,bomb);
            root.setCenter(o);
                    }
        }
    }
    
    private void checksuper() {
        for(superFood i : sp) {
            if(i.getBoundsInParent().intersects(r.getBoundsInParent())) { 
            f.remove(i);
            root.getChildren().remove(i);
            r.getBigger();
            Timeline t = new Timeline(
            new KeyFrame(Duration.seconds(10), e->{
            r.setWidth(r.getWidth() - 10);}));
            t.play();
            }
        }
    }
    
    
    private final AnimationTimer timer = new AnimationTimer() {
            
            @Override
            public void handle(long CurrentNanoTime) {
                if(Math.random() < 0.001) {
                    Bomb b1 = new Bomb((int)(Math.random()*500));
                    b.add(b1);
                    root.getChildren().add(b1);                    
                }
                if(Math.random()<0.015){
                    Food f1 = new Food((int)(Math.random()*500));
                    f.add(f1);
                    root.getChildren().add(f1);}
                if(Math.random() < 0.01) {
                    superFood s1 = new superFood((int)(Math.random()*500));
                    sp.add(s1);
                    root.getChildren().add(s1);                    
                }
                fed();
                cleanup();
                checkbomb();
                T.setText(Score());
                checksuper();
            }};
    
    
    
    private void cleanup() {
        for(Food i : f) {
            if(i.getBoundsInParent().getMaxY() >= 555){
                f.remove(i);
                root.getChildren().remove(i);
            }}
        for(Bomb bomb : b){
            if(bomb.getBoundsInParent().getMaxY() >= 555){
                b.remove(bomb);
                root.getChildren().remove(bomb);}
            }
        for(superFood s : sp){
            if(s.getBoundsInParent().getMaxY() >= 555){
                sp.remove(s);
                root.getChildren().remove(s);}
            }
    }
    
    
    public String Score() {
        return " " + score;
    }
    
    private void fed() {
        for(Food i : f){
        if(i.getBoundsInParent().intersects(r.getBoundsInParent())) { 
            f.remove(i);
            root.getChildren().remove(i);
            score++; }
        }
    }
    
    @Override
    public void start(Stage primaryStage) {         
        r.setOnKeyPressed(
                e -> {
                    switch(e.getCode()) {
                        case RIGHT: r.setX(r.getX() + k); break;
                        case LEFT: r.setX(r.getX() - k); break;
                    }
        });           
        
        
        timer.start();
        root.setBottom(T);
        T.setFill(Color.CORNFLOWERBLUE);
        primaryStage.setResizable(false);
        root.getChildren().addAll(r);
        Scene scene = new Scene(root, 500, 550);
        primaryStage.setTitle("Cookie Monster");
        scene.setFill(Color.ANTIQUEWHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
        r.requestFocus();
    }



    public static void main(String[] args) {
        launch(args);
    }
    
}
