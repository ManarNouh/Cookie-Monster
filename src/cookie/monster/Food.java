/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookie.monster;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Manar Nouh
 */


class Food extends GameObject {  
    public Food(int r,double Hlim) {
        super(r,Hlim);
        this.setFill(cookie());
    }  
    
     private ImagePattern  cookie() {
        Image image1 = new Image("file:Pictures\\ck.png");
            ImagePattern imagePattern = new ImagePattern(image1);
            return imagePattern;
    }
}
