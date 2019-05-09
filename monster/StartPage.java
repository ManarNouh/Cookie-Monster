
package cookie.monster;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

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
    private final Button b1 = new Button("Play");
    private final VBox vb = new VBox();
    Scene s = new Scene(bp,300,300);

    
    public StartPage() throws Exception{
        Lb.setAlignment(Pos.CENTER_LEFT);
        Lb.setFont(Font.font("Arial", 24));
        b1.setPrefSize(100, 50);
        vb.getChildren().addAll(Lb,b1);
        vb.setAlignment(Pos.CENTER);
        bp.setCenter(vb);
        s.setFill(Color.BLACK);       
    }
    
    public Scene getScene() {
        return s;
    }
    
    public Paint getFill() {
        return s.getFill();
    }
}
