
package cookie.monster;

import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
class ChangeFill implements EventHandler<MouseEvent>
{
    private Label tochange;
    public ChangeFill(Label tochange)
    {
        this.tochange = tochange;
    }
    @Override
    public void handle(MouseEvent event)
    {
        if(tochange.getTextFill().equals(Color.BLACK))
        tochange.setTextFill(Color.WHITE);
        else tochange.setTextFill(Color.BLACK);
    }

}

class HelpEv extends ChangeFill{
       private Label helpInfo=new Label("Food adds to your score\n Bombs take away lives\n You have three lives before you die\n Finish 3 levels to win");
       BorderPane p;
       private boolean visible=false;
       FlowPane v;
       Rectangle box=new Rectangle(120,80);
    public HelpEv(Label tochange, FlowPane v) {
        super(tochange);
        this.v=v;
        box.setArcHeight(15);
        box.setArcWidth(15);
        helpInfo.setFont(Font.font("Agency FB",20));
        helpInfo.setTextFill(Color.WHITE);
        helpInfo.setShape(box);
        helpInfo.setTextAlignment(TextAlignment.CENTER);
        helpInfo.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.p=p;
    }
    
    public void handle(MouseEvent event){
        super.handle(event);
        if(visible==true) {
            v.getChildren().remove(helpInfo);
            visible=false;
        }
        else{
            v.getChildren().add(helpInfo);
            p.setAlignment(helpInfo,Pos.CENTER_RIGHT);
            visible=true;
        }
    }
}


public final class StartPage extends BorderPane {
    
    private int Wlim,Hlim;
    Label s = new Label("PLAY");
    Label h=new Label("HELP");
    Stage st;
    
    public StartPage(Stage primaryStage, int Wlim, int Hlim) {
      
        this.Wlim=Wlim;
        this.Hlim=Hlim;
        st=primaryStage;
        
        ImageView bg=new ImageView(new Image("file:Pictures\\begin.jpg"));
        this.getChildren().add(bg);
        bg.setFitHeight(Hlim+10);
        bg.setFitWidth(Wlim+15);
        
    
        VBox options=new VBox();
        options.getChildren().addAll(s,h);
        
        FlowPane help=new FlowPane();
        this.getChildren().add(help);
        help.setLayoutX(300);
        help.setLayoutY(420);
        
        
        s.setBackground(Background.EMPTY);
        s.setTextFill(Color.BLACK);
         s.setFont(Font.font("Agency FB", FontWeight.BOLD, FontPosture.ITALIC, 80));
        
         ChangeFill sFill=new ChangeFill(s);
        s.setOnMouseEntered(sFill);
        s.setOnMouseExited(sFill);
        
        HelpEv evh=new HelpEv(h,help);
        
        h.setFont(s.getFont());
        h.setTextFill(Color.BLACK);
        ChangeFill hFill=new ChangeFill(h);
        h.setOnMouseEntered(evh);
        h.setOnMouseExited(evh);
        
        
        this.setCenter(options);
        options.setAlignment(Pos.CENTER);
        
       s.setOnMouseClicked(e->{
           CharactersPane cpane=new CharactersPane(st,Wlim,Hlim);
          
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
