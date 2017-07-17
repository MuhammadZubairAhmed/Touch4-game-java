/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author M.zubair
 */
public class Controller {
    
    Model Grid;
    view View;
    
    public Controller(int r, int c, int limit)
    {
        System.out.println("controller");
        Grid = new Model(r,c, limit);
        View = new view(Grid, this);
    }
    
    public void update(ActionEvent e){
        positionAwareJButton eventSource = (positionAwareJButton)e.getSource();
        Grid.mark(eventSource.col);
        View.viewUpdate(Grid);
    }
    
    
    public static void main(String args[]){
      //  new cont();
        Controller mainController = new Controller(5, 6, 4);
    }
     
}
