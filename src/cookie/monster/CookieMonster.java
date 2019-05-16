package cookie.monster;

import javafx.application.Application;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.Scene;


public class CookieMonster extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       StartPage start = new StartPage(primaryStage, 800, 650);
       primaryStage.setResizable(false);
       Scene scene = new Scene(start,800,650);
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
