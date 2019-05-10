package cookie.monster;

import java.nio.file.Paths;
import javafx.application.Application;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;



public class CookieMonster extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Label s = new Label("Press to Start");
       s.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 36));
       s.setTextFill(Color.BURLYWOOD);
       Image image = new Image(Paths.get("C:\\Users\\maria\\Desktop\\Pictures\\bg.jpeg").toUri().toString());
       BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
       BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
       Background background = new Background(backgroundImage);
       root.setBackground(background); 
       
       s.setOnMouseClicked(e->{
           StartPane sp = new StartPane(500,500,e);
       });
       primaryStage.setResizable(false);
       root.setTop(s);
        root.setMargin(s,new Insets(350,0,350,180));
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
