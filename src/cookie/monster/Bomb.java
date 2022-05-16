/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookie.monster;

import javafx.scene.image.Image;
/**
 *
 * @author Manar Nouh
 */
public class Bomb extends GameObject {       
    public Bomb(int r, double Hlim) {
        super(r,Hlim,new Image("file:Pictures\\bomb.png"));
    }  
    
}
