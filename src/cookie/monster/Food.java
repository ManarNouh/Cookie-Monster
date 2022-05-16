package cookie.monster;

import javafx.scene.image.Image;


class Food extends GameObject {  
  private final Image elmoFood=new Image("file:Pictures\\apple.png");
  private final Image birdFood=new Image("file:Pictures\\burger.png");
  private final Image abbyFood=new Image("file:Pictures\\cupcake.png");
  private final Image cmFood=new Image("file:Pictures\\ck.png");
  private final Image[] fimgs=new Image[4];
  
    public Food(int r,double Hlim,final int x) {
        super(r,Hlim,new Image("file:Pictures\\ck.png"));
        
        fimgs[0]=cmFood;
        fimgs[1]=elmoFood;
        fimgs[2]=birdFood;
        fimgs[3]=abbyFood;
        
        this.setImage(fimgs[x]);
        this.setFitHeight(70);
        if (x==2) setFitWidth(85);
        this.setFitWidth(70);

    }  
    
}
