/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author M.zubair
 */
public class Model {
        
    int rows, columns, winCount;
    cell gameState[][];
    int gameStatus;
    int activePlayer;
    String playerMarks[]= {"X", "O"};
    int markPositions[];
    
    public Model(int rows, int columns, int winCount){
        this.rows = rows;
        this.columns = columns;
        this.winCount = winCount;
        
        gameState = new cell[rows][columns];
        markPositions =  new int [columns];
        gameStatus = 0;
        activePlayer = 0;
        
        for(int j=0; j< columns; j++ ){
            markPositions[j]=0;
            for(int i=0; i< rows; i++ ){
                gameState[i][j]= new cell();
            }
        }
    }
    
    private boolean rowComplete(int r, int c, int player){
        int start = c;
        while( start >= 0 && gameState[r][start].text.equals(playerMarks[player]) ){
            start--;
        }
        start++;
        int count=0;
         while( start < columns && count < winCount && gameState[r][start].text.equals(playerMarks[player]) ){
             start++;
             count++;
         }
         
         if (count < winCount)
             return false;
         
        return true;
    }
    
    private boolean columnComplete(int r, int c, int player){
        int start = r;
        while( start >= 0 && gameState[start][c].text.equals(playerMarks[player]) ){
            start--;
        }
        start++;
        int count=0;
         while( start < rows && count < winCount && gameState[start][c].text.equals(playerMarks[player]) ){
             start++;
             count++;
         }
         
         if (count < winCount)
             return false;
         
        return true;
    }

    private boolean diagonalComplete(int r, int c, int player){

// Diagonal 1
        int startr = r;
        int startc = c;
        
        while( startr >= 0 && startc >= 0 && gameState[startr][startc].text.equals(playerMarks[player]) ){
            startr--;
            startc--;
        }
        startr++;
        startc++;
                
        int count=0;
        while( startr < rows && startc < columns && count < winCount && gameState[startr][startc].text.equals(playerMarks[player]) ){
            startr++;
            startc++;
            count++; 
         }
        
         if (count >= winCount)
             return true;
// Diagonal 2
        
        startr = r;
        startc = c;
        while( startr < rows && startc >= 0 && gameState[startr][startc].text.equals(playerMarks[player]) ){
            startr++;
            startc--;    
        }
        
        startr--;
        startc++;
        
        count=0;
//        System.out.println(startr + " & " + startc + "\n\n");
        while( startr >= 0 && startc < columns && count < winCount && gameState[startr][startc].text.equals(playerMarks[player]) ){ 
            startr--;
            startc++;
            count++;
             
         }
         if (count >= winCount)
             return true;         
//         
         return false;
    }

    private int gameWon(int r, int c, int player){
        
        if (rowComplete(r,c,player) || columnComplete(r,c,player) || diagonalComplete (r, c, player) ){
            System.out.println("Won " + (player+1));
            return player+1;
        }
        
        return 0;
    }
    
    private boolean gameDraw(){

        boolean GameDraw = true;
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                if(gameState[i][j].open){
                    GameDraw = false;
                    break;
                }
                if(GameDraw == false){
                    break;
                }
            }
        }
        return GameDraw;
    }
    
    private void updateGameStatus(int r, int c, int player){
        gameStatus = gameWon(r, c, player);
        if (gameStatus > 0){
            System.out.println("Winner Returning ");
            return;
        }
        
        if ( gameDraw()){
            gameStatus = -1;
        }
        return;
        
    }
    public void mark(int column){
        if (markPositions[column]< rows){
            gameState[markPositions[column]][column].text = playerMarks[activePlayer];
            gameState[markPositions[column]][column].open = false;
            updateGameStatus(markPositions[column], column, activePlayer);
            activePlayer = (activePlayer + 1)%2;
            markPositions[column]++;
        }
        
    }
}
