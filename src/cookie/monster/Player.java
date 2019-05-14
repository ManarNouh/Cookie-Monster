package cookie.monster;

import java.io.FileInputStream;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


class Player extends Circle { 
    private boolean state = true;
    
    public Player( double Wlim, double Hlim) {
        super(Wlim/2,Hlim - Hlim/8,50);
        this.setFill(CM());
        this.setOnKeyPressed(
                e -> {
                    double newX = getBoundsInParent().getMaxX();
                    double newX1 = getBoundsInParent().getMinX();

                    switch(e.getCode()) {
                        case RIGHT: {
                        if (newX < Wlim) {
                           setCenterX(getCenterX() + 25);} 
                        else setCenterX(getCenterX() - 1);break;}
                        case LEFT: {
                       if (newX1 > 0) {
                            setCenterX(getCenterX() - 25); }
                    else setCenterX(getCenterX() + 1); break;}}
        });
    }
        
    public boolean isAlive(){
        return state;
    }
    
    public void setState(boolean state){
        this.state = state;
    }
    
    private ImagePattern  CM() {
       
        Image image1 = new Image("file:Pictures\\s-l300.png");
            ImagePattern imagePattern = new ImagePattern(image1);
            return imagePattern;
    }
}


