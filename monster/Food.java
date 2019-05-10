/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookie.monster;

import java.io.FileInputStream;
import java.nio.file.Paths;
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
    
     public ImagePattern  cookie() {
        Image image1 = new Image(Paths.get("C:\\Users\\maria\\Desktop\\Pictures\\ck.png").toUri().toString());
            ImagePattern imagePattern = new ImagePattern(image1);
            return imagePattern;
    }
}
