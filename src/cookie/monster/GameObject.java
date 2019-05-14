/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookie.monster;

import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class GameObject extends Circle {
    private PathTransition p;
    
    public GameObject(int r, double Hlim){
        setCenterX(r);
        setCenterY(0);
        setRadius(30);
        p = new PathTransition(Duration.millis(2500),
                new Line(r,0,r,Hlim),this);
        p.play();
   }
    
    public void setPath(PathTransition p1) {
        p1.setNode(this);
        p1.setCycleCount(1);
        p1.play();
    }
    
    public boolean isCollided(Node m) {
        if(this.getBoundsInParent().intersects(m.getBoundsInParent())){
            return true;
        }
        return false;
    }
    
    public boolean checkBoundries(double limit) {
        if(this.getBoundsInParent().getMaxY() >= limit) {
            return true;
        }
        return false;
    }
    
    public double getBoundriesX() {
        return this.getBoundsInParent().getMaxX();
    }
    
    public double getBoundriesY() {
        return this.getBoundsInParent().getMaxY();
    }
    
    public PathTransition getPath(){
        return p;
    }
}

