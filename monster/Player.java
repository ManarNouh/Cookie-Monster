package cookie.monster;

import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


class Player extends Circle { 
    private boolean state = true;
    
    public Player( double Wlim, double Hlim) throws Exception {
        super(Wlim/2,Hlim - Hlim/8,50);
        this.setFill(CM());
        this.setOnKeyPressed(
                e -> {
                    double newX = getBoundsInParent().getMaxX();
                    double newX1 = getBoundsInParent().getMinX();

                    switch(e.getCode()) {
                        case RIGHT: {
                        if (newX < Wlim) {
                           setCenterX(getCenterX() + 10);} 
                        else setCenterX(getCenterX() - 1);break;}
                        case LEFT: {
                       if (newX1 > 0) {
                            setCenterX(getCenterX() - 10); }
                    else setCenterX(getCenterX() + 1); break;}}
        });
    }
        
    public boolean isAlive(){
        return state;
    }
    
    public void setState(boolean state){
        this.state = state;
    }
    
    public ImagePattern  CM() throws Exception{
        FileInputStream i = new FileInputStream("file:\\s-l300.png");
        Image image1 = new Image(i);
            ImagePattern imagePattern = new ImagePattern(image1);
            return imagePattern;
    }
}


