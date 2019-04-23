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
import javafx.scene.text.Text;

class Bomb extends Circle {       
    public Bomb(int r){
        super(r,0,10);
        PathTransition p= new PathTransition(Duration.millis(1500),
                new Line(r,0,r,450),this);
        p.setCycleCount(1);
        p.play();  
    }  
}

class Food extends Rectangle {   
    public Food(int r) {
        super(r,0,10,10);
        PathTransition p= new PathTransition(Duration.millis(1500),
                new Line(r,0,r,450),this);
        p.setCycleCount(1);
        p.play();
    }    
}

public class JavaFXApplication6 extends Application {
    
    private List<Food> f = new ArrayList<>();
    private List<Bomb> b = new ArrayList<>();
    private final Rectangle r = new Rectangle(10,400,100,20);
    private final Text T = new Text();
    private static int score = 0;
    BorderPane root = new BorderPane();
      
    private void checkbomb(){
        for(Bomb bomb: b){
            if(bomb.getBoundsInParent().intersects(r.getBoundsInParent())){
                r.setFill(Color.RED);
            timer.stop();
            root.getChildren().remove(r);
            root.getChildren().add(new Text(100,100,"LOSE"));
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
                cleanup();
                fed();
                checkbomb();
                T.setText(Score());
            }};
    
    
    
    private void cleanup() {
        for(Food i : f) {
            if(i.getBoundsInParent().getMaxY() >= 455){
                f.remove(i);
                root.getChildren().remove(i);}
            }
        for(Bomb bomb : b){
            if(bomb.getBoundsInParent().getMaxY() >= 455){
                b.remove(bomb);
                root.getChildren().remove(bomb);}
            }
    }
    
    
    public String Score() {
        return " " + score;
    }
    
    private void fed() {
        for(Food i : f){
        if(r.getBoundsInParent().intersects(i.getBoundsInParent())) { 
            f.remove(i);
            root.getChildren().remove(i);
            score++;
                        
        } }
    }
    
    @Override
    public void start(Stage primaryStage) {         
        r.setOnKeyPressed(
                e -> {
                    switch(e.getCode()) {
                        case RIGHT: r.setX(r.getX() + 10); break;
                        case LEFT: r.setX(r.getX() - 10); break;
                    }
        });           
        
        
        timer.start();
        root.setBottom(T);
        
        primaryStage.setResizable(false);
        root.getChildren().addAll(r);
        Scene scene = new Scene(root, 500, 550);
        primaryStage.setTitle("Cookie Monster");
        scene.setFill(Color.ANTIQUEWHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
        r.requestFocus();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
