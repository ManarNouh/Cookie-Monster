package javafxapplication6;


import java.io.FileInputStream;
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
import javafx.scene.text.Text;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;

class Player extends Circle {  
    public Player( double Wlim, double Hlim) {
        super(Wlim/2,Hlim - Hlim/8,50);
        
        setOnKeyPressed(
                e -> {
                    double newX = getBoundsInParent().getMaxX();
                    double newX1 = getBoundsInParent().getMinX();

                    switch(e.getCode()) {
                        case RIGHT: {
                        if (newX < 500) {
                           setCenterX(getCenterX() + 10);} 
                        else setCenterX(getCenterX() - 1);break;}
                        case LEFT: {
                       if (newX1 > 0) {
                            setCenterX(getCenterX() - 10); }
                    else setCenterX(getCenterX() + 1); break;}}
        });              
    }
    
    public void getBigger() {
        setRadius(getRadius() + 10);
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
    public Food(int r) throws Exception {
        super(r,0,60,60);
        this.setFill(cookie());
        p = new PathTransition(Duration.millis(1500), new Line(r,0,r,550),this);
        p.setCycleCount(1);
        p.play();
        
    } 
    
    public void setPath(PathTransition p1){
        p1.setNode(this);
        p1.setCycleCount(1);
        p1.play();
    }
     public ImagePattern  cookie() throws Exception{
        Image image1 = new Image(new FileInputStream("C:\\Users\\Laptop\\Pictures\\ck.jpg"));
            ImagePattern imagePattern = new ImagePattern(image1);
            return imagePattern;
    }
    
}



class superFood extends Food {
    
    public superFood(int r) throws Exception {
        super(r);
        this.setFill(supercookie());
        this.setWidth(70);
        this.setHeight(70);
        p = new PathTransition(Duration.millis(1500),
                new Line(r,0,r,550),this);
        p.setCycleCount(1);
        p.play();
       
    }
     public ImagePattern  supercookie() throws Exception{
        Image image1 = new Image(new FileInputStream("C:\\Users\\Laptop\\Pictures\\cks.jpg"));
            ImagePattern imagePattern = new ImagePattern(image1);
            return imagePattern;
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
            AnimationTimer collect=collectFood();
            collect.start();
            Timeline t = new Timeline(
            new KeyFrame(Duration.seconds(10), e->{
            r.setWidth(r.getWidth() - 10);
            collect.stop();
            }));
            t.play();
            }
        }
    }
    
       private AnimationTimer collectFood(){
        AnimationTimer t=new AnimationTimer(){
        
            @Override
            public void handle(long now) {
              for(Food i: f){
            PathTransition collect= new PathTransition(Duration.millis(750), new Line(r.getX()+r.getWidth()/2,i.getBoundsInParent().getMaxY(),r.getX()+r.getWidth()/2,550),i);
        i.setPath(collect);}
            }
       
        };
        return t;
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
                if(Math.random() < 0.005) {
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
                    double newX = getBoundsInParent().getMaxX();
                    double newX1 = getBoundsInParent().getMinX();

                    switch(e.getCode()) {
                        case RIGHT: {
                        if (newX < 500) {
                           setX(getX() + 10);} 
                        else setX(getX() - 1);break;}
                        case LEFT: {
                       if (newX1 > 0) {
                            setX(getX() - 10); }
                    else setX(getX() + 1); break;}}}    
        );    
        
        
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
