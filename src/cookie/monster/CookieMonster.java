package cookie.monster;

import java.nio.file.Paths;
import javafx.application.Application;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class CookieMonster extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Label s = new Label("Press to Start");
        AudioClip audio1 = new AudioClip(Paths.get("Sound\\HELLO.mp3").toUri().toString());
       s.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 36));
       s.setTextFill(Color.BURLYWOOD);
       Image image = new Image("file:Pictures\\bg.jpeg");
       ImageView i = new ImageView(image);
       i.setFitHeight(510);
       i.setFitWidth(510);
       root.getChildren().add(i);
       audio1.play();
       
       s.setOnMouseClicked(e->{
           StartPane sp = new StartPane(500,500,primaryStage);
       });
       primaryStage.setResizable(false);
       root.setTop(s);
       BorderPane.setMargin(s,new Insets(350,0,350,180));
       Scene scene = new Scene(root,500,500);
       primaryStage.setScene(scene);
       primaryStage.show();
       
        
        

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
