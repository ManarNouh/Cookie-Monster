/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookie.monster;

import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Manar Nouh
 */
public class superFood extends Food {  
        
    public superFood(int r, double Hlim) throws Exception {
        super(r,Hlim);
        this.setFill(supercookie());
        setRadius(45);
    }  
    
    public ImagePattern  supercookie() throws Exception{
        Image image1 = new Image(new FileInputStream("cks.jpg"));
            ImagePattern imagePattern = new ImagePattern(image1);
            return imagePattern;
    }
}

