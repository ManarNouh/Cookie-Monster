package cookie.monster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


class Player extends ImageView { 
    private boolean state = true;
 
    private Image CM=new Image("file:Pictures\\s-l300.png");
 
    
    public Player( double Wlim, double Hlim) {
        this.setImage(CM);
        setFitWidth(100);
        setFitHeight(100);
        setX(Wlim/2);
        setY(Hlim-Hlim/4);
        this.setOnKeyPressed(
                e -> {
                    double newX = getBoundsInParent().getMaxX();
                    double newX1 = getBoundsInParent().getMinX();

                    switch(e.getCode()) {
                        case RIGHT: {
                        if (newX < Wlim) {
                           setX(getX() + 25);} 
                        else setX(getX() - 1);break;}
                        case LEFT: {
                       if (newX1 > 0) {
                            setX(getX() - 25); }
                    else setX(getX() + 1); break;}}
        });
        
      
        
     
    }
    
        
    public boolean isAlive(){
        return state;
    }
    
    public void setState(boolean state){
        this.state = state;
    }
    
   
   
}
