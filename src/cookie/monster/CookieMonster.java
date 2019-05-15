package cookie.monster;

import javafx.application.Application;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.Scene;


public class CookieMonster extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
       primaryStage.setResizable(false);
 
       
//       Stage charac=new Stage();
//       CharactersPane noha=new CharactersPane();
//       Scene s2=new Scene(noha,500,500);
       StartPage spage=new StartPage(primaryStage,500,500);
       Scene scene = new Scene(spage,500,500);
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
