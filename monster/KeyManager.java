/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookie.monster;

import javafx.scene.input.KeyCode;

/**
 *
 * @author Manar Nouh
 */
public class KeyManager {
    //represent states of my game keys
    //UP,DOWN,LEFT,RIGHT
    private boolean [] keystates;

    public KeyManager()
    {
        keystates = new boolean[8];
    }
    
    private int keycodeToIndx(KeyCode k)
    {
        switch(k)
        {
            case UP:
                return 0;
            case DOWN:
                return 1;
            case LEFT:
                return 2;
            case RIGHT:
                return 3;
            default:
                return -1;
        }
    }
    
    
    public void setkeystate(KeyCode k , boolean state)
    {
        int i = keycodeToIndx(k);
        keystates[i] = state;
    }
    
    public boolean getkeystate(KeyCode k)
    {
        int i = keycodeToIndx(k);
        return keystates[i];
    }
    
    
}
