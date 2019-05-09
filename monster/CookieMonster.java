package cookie.monster;

import javafx.application.Application;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.Scene;



public class CookieMonster extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
//        StartPage sp = new StartPage();
//        Scene s = sp.getScene();
//        s.setFill(s.getFill());
//        primaryStage.setScene(s);
//        primaryStage.show();
        
        StartGame sg = new StartGame(500,500,primaryStage);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
