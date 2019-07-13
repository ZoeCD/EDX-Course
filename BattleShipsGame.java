/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microsoftedx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author zoe
 */
public class  BattleShipsGame{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Grid
        String [][] oceanGrid = new String[10][10];
        
        //Start
        System.out.println("**** Welcome to Battle Ships Game ****");
        System.out.println("Right now, the sea is empty");
        System.out.println(" ");
        
        //Print ocean 4 firt time
        printOcean(oceanGrid);
        
        //Deploy player's ships
        System.out.println(""
                + "Deploy your ships");
        playerShips(oceanGrid);
        
        //Update and print ocean
        printOcean(oceanGrid);
        
        //Deploy computer's ships
        System.out.println("The computer will deploy its ships"
                + "");
        computerShips(oceanGrid);
        
        //Update and print ocean
        printOcean(oceanGrid);
        
        //Battle
        ArrayList<int[]> playerGuess = new ArrayList<int[]>();
        ArrayList<int[]> computerGuess = new ArrayList<int[]>();
        int compShips = 5;
        int plyrShips = 5;
        while (compShips > 0 || plyrShips > 0){
            int playerTurn = playerGuess(oceanGrid, playerGuess);
            switch (playerTurn){
                case 1: 
                    compShips --;
                    break;
                case 2: 
                    plyrShips --;
                    break;
                default: 
                    break;
              
            }
            int computerTurn = computerGuess(oceanGrid, computerGuess);
            switch (computerTurn){
                case 1: 
                    compShips --;
                    break;
                case 2: 
                    plyrShips --;
                    break;
                default: 
                    break;
              
            }
            printOcean(oceanGrid);
            System.out.println("Your ships: "+plyrShips+" | "+ "Computer ships: "+compShips);
            System.out.println("-----------------------------------------------");
            
        }
        
        //GAME OVER
        if(plyrShips > compShips){
            System.out.println("Hooray! You win the battle :)");
        }else{
            System.out.println("Sorry, maybe next time :(");
        }
          
                  
        
    }
    
    public static void printOcean(String[][] oceanGrid){
        System.out.println("   0123456789   ");
        
        // null OR 2  OR 8= ""
        // 1 "@"
        // 3 OR 7"!"
        // 4 OR 6"x"
        // 5 "-"
        // 
        
        //print ocean
        for(int row = 0; row < oceanGrid.length; row ++){
            System.out.print(row + " |");
            for(int col = 0; col < oceanGrid[row].length; col ++){
                if (col == oceanGrid[row].length-1){
                    if((oceanGrid[row][col] == null)||(oceanGrid[row][col] == "2")||(oceanGrid[row][col] == "8")){
                       System.out.println(" " + "| " + row); 
                    }else if (oceanGrid[row][col] == "1"){
                       System.out.println("@" + "| " + row);
                    }else if ((oceanGrid[row][col] == "3")||(oceanGrid[row][col] == "7")){
                       System.out.println("!" + "| " + row);
                    }else if ((oceanGrid[row][col] == "4")||(oceanGrid[row][col] == "6")){
                       System.out.println("x" + "| " + row);
                    }else if (oceanGrid[row][col] == "5"){
                       System.out.println("-" + "| " + row);
                    }
                    /* TO CHECH COMPUTER'S POSITIONS
                    else if (oceanGrid[row][col] == "8"){
                       System.out.println("." + "| " + row);
                    }else if (oceanGrid[row][col] == "2"){
                       System.out.println("*" + "| " + row);
                    }*/
                      
                }else{
                    if((oceanGrid[row][col] == null)||(oceanGrid[row][col] == "2")||(oceanGrid[row][col] == "8")){
                       System.out.print(" "); 
                    }else if(oceanGrid[row][col] == "1"){
                        System.out.print("@");
                    }else if((oceanGrid[row][col] == "3")||(oceanGrid[row][col] == "7")){
                        System.out.print("!");
                    }else if((oceanGrid[row][col] == "4")||(oceanGrid[row][col] == "6")){
                        System.out.print("x");
                    }else if(oceanGrid[row][col] == "5"){
                        System.out.print("-");
                    }
                    /* TO CHECH COMPUTER'S POSITIONS
                    else if(oceanGrid[row][col] == "8"){
                        System.out.print(".");
                    }else if(oceanGrid[row][col] == "2"){
                        System.out.print("*");
                    }*/
                    
                }
            }
        }
        System.out.println("   0123456789   ");
    }  
    
    public static void playerShips (String[][] oceanGrid){
        Scanner input = new Scanner(System.in);
        
        int numPos=0;
        int ship=0;
        int[] posXY = new int [2];
        
        while(numPos<5){
            boolean  tryAgain = false; 
            System.out.print("Enter X coordinate for your ship " + (ship+1) + " : ");
            int posX =  input.nextInt();
            
            if (posX >= 0 && posX <= 9){ 
                  posXY[1]= posX;
            }else{
                System.out.println("Invalid coordinate. Enter a number between 0 and 9");
                continue;
            }
            
            // COORD Y
            System.out.print("Enter Y coordinate for your ship " + (ship+1) + " : ");
            int posY =  input.nextInt();
            
            if (posY >= 0 && posY <= 9){ 
                  posXY[0]= posY;
            }else{
                System.out.println("Invalid coordinate. Enter a number between 0 and 9");
                continue;
            }
            /*PRINT ARRAY TO CHECK
            for (int d=0; d < posXY.length; d++){
                System.out.print(Arrays.toString(posXY[d])+"  .");
            }*/
            
            if (oceanGrid[posY][posX]==null){
                oceanGrid[posY][posX] = "1";
            }else{
                System.out.println("Oops, there is a ship here! Try another location");
                continue;
            }
            numPos++;
            ship ++;
            
        }
       
    }
    
    public static void computerShips(String [][] oceanGrid){
        // Range
        int max= 9;
        int min=0;
        int range = max-min + 1;
        
        int numPos=0;
        int ship=0;
        int[][] posXY = new int[5][2];
        
        while(numPos<5){
            
            int posX = (int)(Math.random() * range) + min;
            posXY [ship][1]= posX;
 
            int posY = (int)(Math.random() * range) + min;
            posXY [ship][0]= posY;
            
            /*PRINT ARRAY TO CHECK
            for (int d=0; d < posXY.length; d++){
                System.out.print(Arrays.toString(posXY[d])+"  .");
            }*/
            
            
            if (oceanGrid[posY][posX]== null){
                oceanGrid[posY][posX] = "2";
            }else{
                continue;
            }
            System.out.println((ship+1)+". ship DEPLOYED");
            numPos++;
            ship ++;
            
        }
    }
    
    public static int playerGuess(String[][] ocean, ArrayList<int[]> guesses){
        Scanner input = new Scanner(System.in);
        boolean valid = true;
        int[] posXY = new int[2];
        int posX = 0;
        int posY = 0;
        int result= 0;
        
        System.out.println("YOUR TURN!");
        
        while(valid){
 
             
            System.out.print("Enter X coordinate: ");
            posX =  input.nextInt();
            
            if (posX >= 0 && posX <= 9){ 
                  posXY[1]= posX;
            }else{
                System.out.println("Invalid coordinate. Enter a number between 0 and 9");
                continue;
            }
            
            System.out.print("Enter Y coordinate: ");
            posY =  input.nextInt();
            
            if (posY >= 0 && posY <= 9){ 
                  posXY[0]= posY;
            }else{
                System.out.println("Invalid coordinate. Enter a number between 0 and 9");
                continue;
            }
            
            if((ocean[posY][posX] == "3")||(ocean[posY][posX] == "4")||(ocean[posY][posX] == "5")){
                System.out.println("You already tried there! Pick another location");
                continue;
            }

            valid = false;
        } // while
        
        if (ocean[posY][posX] == "2"){
            System.out.println("Boom! You sunk the ship!");
            ocean[posY][posX] = "3";
            result = 1;
        }else if(ocean[posY][posX] == "1"){
            System.out.println("Oh no, you sunk your own ship :(");
            ocean[posY][posX] = "4";
            result = 2;
        }else if(ocean[posY][posX] == null){
            System.out.println("Sorry, you missed");
            ocean[posY][posX] = "5";
        }else if(ocean[posY][posX] == "8"){
            System.out.println("Sorry, you missed");
            ocean[posY][posX] = "5";
        }
        
        return result;
    }
    
    public static int computerGuess(String[][] ocean, ArrayList<int[]> guesses){
        //Range
        int max= 9;
        int min=0;
        int range = max-min + 1;
        
        boolean valid = true;
        int[] posXY = new int[2];
        int posX = 0;
        int posY = 0;
        int result= 0;
        
        System.out.println("COMPUTER'S TURN!");
        
        while(valid){
 
            posX = (int)(Math.random() * range) + min;
            posXY[1]= posX;
 
            posY = (int)(Math.random() * range) + min;
            posXY[0]= posY;
            
            
            if((ocean[posY][posX] == "6")||(ocean[posY][posX] == "7")||(ocean[posY][posX] == "8")||(ocean[posY][posX] == "3")||(ocean[posY][posX] == "4")){
                continue;
            }

            valid = false;
        } // while
        
        if (ocean[posY][posX] == "2"){
            System.out.println("The Computer sunk one of its own ship");
            ocean[posY][posX] = "7";
            result = 1;
        }else if(ocean[posY][posX] == "1"){
            System.out.println("The Computer sunk one of your ships!");
            ocean[posY][posX] = "6";
            result = 2;
        }else if(ocean[posY][posX] == null){
            System.out.println("Computer missed");
            ocean[posY][posX] = "8";
        }
        
        return result;
    }
        
}

