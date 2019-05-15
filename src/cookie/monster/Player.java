package cookie.monster;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;



class Player extends ImageView {  
    private Image CM=new Image("file:Pictures\\s-l300.png");
    private Image i = new Image("file:Pictures\\lives.png");
    private static int score = 0;
    private int lives = 3;
    private static int spc = 5 , tim =20;
    private ImageView[] liv = new ImageView[3];
    private ArrayList<GameObject> toRemove = new ArrayList<>();
 
    
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
        
        ImageView iv = new ImageView(i);
        iv.setFitHeight(20);
        iv.setFitWidth(20);
        
        ImageView iv1 = new ImageView(i);
        iv1.setFitHeight(20);
        iv1.setFitWidth(20);
        
        ImageView iv2 = new ImageView(i);
        iv2.setFitHeight(20);
        iv2.setFitWidth(20);
        liv[0] = iv;
        liv[1] = iv1;
        liv[2] = iv2;
      
        
     
    }
    public boolean isAlive(){
        if(lives == 0){
            setLives(3);
            return false;
        }
        return true;
    }
    
    public void setScore(int x){
        score = x;
    }
    
    
    public void checkFood(ArrayList<Food> f, BorderPane root) {
        for(Food i : f){
        if(i.isCollided(this) == true) { 
            toRemove.add(i);
            root.getChildren().remove(i);
            score++; }
        }
        f.removeAll(toRemove);
    }
    
    public void checksuper(ArrayList<superFood> sp, ArrayList<Food> f, BorderPane root) {
        for(superFood i : sp) {
            if(i.isCollided(this) == true) {
                toRemove.add(i);
                root.getChildren().remove(i);
                i.collectFood(f,this);
                i.playSound(this);
            }
        }
        sp.removeAll(toRemove);
    }
    
    public void checkBomb(ArrayList<Bomb> b, BorderPane root,HBox h){
        for(Bomb bomb: b){
            if(bomb.isCollided(this) == true) {
                toRemove.add(bomb);
                root.getChildren().remove(bomb);
                lives--;
                h = setLiv(h);
            }
        }
        b.removeAll(toRemove);
    }
    
    public int getScore(){
        return score;
    }
    
    public void setLives(int no){
        lives = no;
    }
    
    public HBox setLiv(HBox h){
        if(lives != 3)
            h.getChildren().remove(liv[lives]);
        else {
            for(ImageView n : liv){
            h.getChildren().add(n);
            h.setMargin(n, new Insets(0,tim - spc,0,0));
            }
        }
        
        return h;
    }
    
   
   
}
