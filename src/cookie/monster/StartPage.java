package cookie.monster;

import java.nio.file.Paths;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Manar Nouh
 */
public final class StartPage extends BorderPane {
    
    
    Label s = new Label("Press to Start");
    AudioClip audio1 = new AudioClip(Paths.get("Sound\\HELLO.mp3").toUri().toString());
    Stage st;
    int Wlim, Hlim;
    public StartPage(Stage primaryStage, int Wlim, int Hlim) {
        this.Wlim=Wlim;
        this.Hlim=Hlim;
        st=primaryStage;
       s.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 36));
       s.setTextFill(Color.BURLYWOOD);
       Image image = new Image("file:Pictures\\bg.jpeg");
       ImageView i = new ImageView(image);
       i.setFitHeight(510);
       i.setFitWidth(510);
       this.getChildren().add(i);
       this.setTop(s);
       BorderPane.setMargin(s,new Insets(350,0,350,180));
       audio1.play();
       s.setOnMouseClicked(e->{
           CharactersPane cpane=new CharactersPane(st);
           SwitchScenes(cpane);
       });
       
    }
    
    private void SwitchScenes(BorderPane p) {
        p.setBackground(Background.EMPTY);
        st.setTitle("Cookie Monster");
        Scene k = new Scene(p,Wlim,Hlim);
        k.setFill(Color.CORNFLOWERBLUE);
        st.setScene(k);
        st.show();
    }
}
