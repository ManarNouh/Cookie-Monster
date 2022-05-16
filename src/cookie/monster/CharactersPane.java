/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookie.monster;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Noha uwu
 */
public class CharactersPane extends BorderPane{
  
    HBox hb;
    ImageView CM=new ImageView(new Image("file:Pictures\\s-l300.png"));
    ImageView Elmo=new ImageView(new Image("file:Pictures\\Elmo.png"));
    ImageView Bird=new ImageView(new Image("file:Pictures\\Bird.png"));
    ImageView Abby=new ImageView(new Image("file:Pictures\\Abby.png"));
    ImageView[] Characters=new ImageView[4];
    int choice;
    Image imgchoice = CM.getimage();;
    Button go;
    Stage primaryStage;
    Text R = new Text("Choose your puppet");
        
    public CharactersPane(Stage primaryStage,int Wlim, int Hlim){
    this.primaryStage=primaryStage;
    this.Wlim = Wlim;
    this.Hlim = Hlim;
    SwitchScenes(this);
    hb=new HBox();
    this.setCenter(hb);
    hb.setAlignment(Pos.CENTER);
    hb.setSpacing(8);
 
    go=new Button("Let's go!");
    
   go.setPrefSize(200, 80);
    go.setOnAction(e->{
        StartPane start=new StartPane(500,500,primaryStage,imgchoice);
    });
    this.setBottom(go);
    this.setAlignment(go,Pos.BOTTOM_CENTER);
    
    R.setFont(Font.font("Algerian", FontWeight.BOLD, FontPosture.ITALIC, 45));
    this.setTop(R);
    this.setAlignment(R,Pos.TOP_CENTER);
    
Characters[0]=CM;
Characters[1]=Elmo;
Characters[2]=Bird;
Characters[3]=Abby;

for(int i=0; i<Characters.length;i++){
    final ImageView thischar=Characters[i];
    thischar.setFitHeight(100);
    thischar.setFitWidth(100);
    if(i==3) thischar.setFitWidth(120);
    hb.getChildren().add(thischar);
    final int x=i;
    
    thischar.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            imgchoice=thischar.getImage();
            thischar.setFitWidth(140);
            thischar.setFitHeight(140);
            choice=x;
            smallOthers(x);
        }
    });
}


}
    public Image getCharChoice(){
        return imgchoice;
    }
    
    void smallOthers(int x){
        for(int j=0; j<Characters.length;j++){
            if(j==x);
            else{ Characters[j].setFitHeight(100);
            Characters[j].setFitWidth(100);
        } }
    }
  private void SwitchScenes(BorderPane p) {
        p.setBackground(Background.EMPTY);
        primaryStage.setTitle("Cookie Monster");
        Scene k = new Scene(p,Wlim,Hlim);
        k.setFill(Color.CORNFLOWERBLUE);
        primaryStage.setScene(k);
        primaryStage.show();
    }
}
