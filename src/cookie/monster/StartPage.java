
package cookie.monster;

import java.io.FileInputStream;
import java.nio.file.Paths;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Manar Nouh
 */
public final class StartPage {
    private final BorderPane bp = new BorderPane();
    private final Label Lb = new Label("Cookie Monster");
    private final Button b1 = new Button("Press to Play ");
    private final VBox vb = new VBox();
    Scene s = new Scene(bp,300,300);

    
    public StartPage() {
        
    }
    
    public Scene getScene() {
        return s;
    }
}
