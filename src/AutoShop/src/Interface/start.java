/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author LeoFuking
 */
public class start {
    
    start(){
        Login L = new Login(start.this);
        L.setVisible(true);
    }
    
    public void permission(int[] s){
        MainFrame mf = new MainFrame(s);
        mf.setVisible(true);
    }
}
