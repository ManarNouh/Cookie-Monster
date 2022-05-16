package cookie.monster;

import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author Manar Nouh
 */
public class superFood extends Food {  
    private final AudioClip audio = new AudioClip(Paths.get("Sound\\eating.mp3").toUri().toString());
    public superFood(int r, double Hlim){
        super(r,Hlim);
        this.setFitHeight(70);
        this.setFitWidth(70);
        this.setImage(new Image("file:Pictures\\cks.png"));
    }  
    
    public void collectFood(ArrayList<Food> f, Player r){
        Timeline t = new Timeline(new KeyFrame(
                Duration.millis(20),e->{
                     for(Food i: f){
                    PathTransition collect= new PathTransition( 
                            Duration.millis(750), new Line(r.getX() + 50,
                            i.getBoundriesY(),
                            r.getX() + 50,550),i);
                        i.setPath(collect); 
                }
                }
        ));
        t.setCycleCount(300);
        t.play();
    }
    
    public void playSound(Player r){
        audio.setPriority(1);
        if(audio.isPlaying() == true){
            audio.stop();
        }
        audio.play(5);
    }
}
