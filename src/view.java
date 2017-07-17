/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;

/**
 *
 * @author M.zubair
 */
class positionAwareJButton extends JButton{
    public int row = 0;
    public int col = 0;
}
public class view extends JFrame implements ActionListener{
    GridLayout Lay;
    Controller controllerReference;
    
    public positionAwareJButton[][] grid;
      
        
    public void actionPerformed(ActionEvent e){
         controllerReference.update(e);
    }
    
    public view(Model model, Controller controllerReference) {

        //System.out.println("view");
        this.controllerReference = controllerReference;
        
        getContentPane().setLayout(new GridLayout(model.rows, model.columns));
        
        grid= new positionAwareJButton[model.rows][model.columns];
        
        for(int i=0;i<model.rows;i++)
        { 
            for(int j=0;j<model.columns;j++)
            {  grid[i][j] = new positionAwareJButton();
                ((JButton) grid[i][j]).setBackground(Color.black);
                 grid[model.rows - i - 1][j]= new positionAwareJButton();
                 grid[model.rows - i - 1][j].row = i;
                 grid[model.rows - i - 1][j].col = j;
                 grid[model.rows - i - 1][j].addActionListener(this);
                 add(grid[model.rows - i - 1][j]);
                 grid[model.rows - i - 1][j].setVisible(true);
            }
        }
        
        this.setVisible(true);
        this.setSize(450, 590);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    void show(Model Grid){
        for(int i=0;i<Grid.rows;i++)
        { 
            for(int j=0;j< Grid.columns;j++)
            {  
                if (!Grid.gameState[i][j].open){
                    ((JButton) grid[i][j]).setBackground(Color.white);
                    grid[i][j].setText(Grid.gameState[i][j].text);
    //                grid[i][j].setEnabled(false);
                }
            }
        }
        
    }
    
    public void viewUpdate(Model Grid) {
        show(Grid);
        if (Grid.gameStatus > 0){
            JOptionPane.showMessageDialog(this, "Player " + (Grid.activePlayer +1) + " Has Won (:-) ");
            System.exit(Grid.gameStatus);                   
        }
        else if(Grid.gameStatus < 0){
            JOptionPane.showMessageDialog(this, "Game Draw");
            System.exit(Grid.gameStatus);        
            

        }
    }
}
