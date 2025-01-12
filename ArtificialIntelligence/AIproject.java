/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aiproject;

import static aiproject.AIproject.COLUMN;
import static aiproject.AIproject.ROW;
import java.util.Random;

/**
 *
 * @author noura
 */
public class AIproject {

    final static int ROW = 3;
    final static int COLUMN = 10;
    private static Domin[][] grid = new Domin[ROW][COLUMN];
    static int consistency = 0;
    static int assignments = 0;
    static int consistencychecksFC = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        randomGenerater();

        System.out.println("\n --------Initial State---------");
        print();

        //for (int m = 0; m < ROW - 1; m++) {
            //for (int n = 0; n < COLUMN; n++) {
               // grid[m][n].ReAssignDominValue();

            //}
       // }

        //if (ForwardcheckingMVR()){
        //System.out.println("\n --------solved State---------");
        //print();
        //}
    }

    static void randomGenerater() {
        Random random = new Random();
        boolean solvable;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                grid[i][j] = new Domin();

            }
        }
        solvable=BacktrackingGenrator(0, 0);
        print();
          for (int i = 0; i < COLUMN; i++) {
                for (int j = 0; j < ROW - 1; j++) {
                    //should i add if the cell has -1(empty) then i should not sum it with the column other values?
                    if (j == 0) {
                        grid[ROW - 1][i].setValue(0);
                    }
                    if(grid[j][i].getValue()!=-1)
                    grid[ROW - 1][i].setValue(grid[ROW - 1][i].getValue() + grid[j][i].getValue());

                }
            }
        if (solvable) {
            
            System.out.println("\n --------Full solvable Initial State---------");
            print();
        }
         
         else {
            
            System.out.println("\n --------Full unsolvable State---------");
          print();
        }
        boolean AlreadyEmpty=false;
          int randomRow= random.nextInt(ROW - 1);
          int randomCol= random.nextInt(COLUMN);
          grid[randomRow][randomCol].setValue(-1);
           int randomEmptySpots = random.nextInt(10)+5;
            for (int i = 1; i <= randomEmptySpots; i++) {
               if( grid[randomRow][randomCol].getValue()==-1 && AlreadyEmpty)
                   i--;
               
                randomRow = random.nextInt(ROW - 1);
                randomCol = random.nextInt(COLUMN);
                 if(grid[randomRow][randomCol].getValue()==-1){
                     AlreadyEmpty=true;
                     continue;
                 }
                 else
                      AlreadyEmpty=false;
                grid[randomRow][randomCol].setValue(-1);

            }
    }

//for consistency we might but a if else for the actual solving and the random genrator
    static boolean isValid(int row, int col,int value) {
        for (int i = 0; i < COLUMN; i++) {
            if (i != col) {
                consistency++;
                if (grid[row][i].getValue() != -1) {
                    
                    if (grid[row][i].getValue() == value) {
                        return false;
                    }
                }
            }
        }
        
           
            if(row-1!=-1){
                 consistency++;
            if (grid[row - 1][col].getValue() != -1 && grid[row - 1][col].getValue() == value) {
                return false;
            }
            }
       
        
            
            if (row + 1 != ROW - 1) {
                 consistency++;
                if (grid[row + 1][col].getValue() != -1 && grid[row + 1][col].getValue() == value) {
                    return false;
                }
            }
      

        
           
            if (row + 1 != ROW - 1 && col+1<COLUMN) {
                 consistency++;
                if (grid[row + 1][col + 1].getValue() != -1 && grid[row + 1][col + 1].getValue() == value) {
                    return false;
                }
            }
      
           if(col-1!=-1 && row - 1 != -1){
            consistency++;
            if (grid[row - 1][col - 1].getValue() != -1 && grid[row - 1][col - 1].getValue() == value) {
                return false;
            }
           }
        
            
            if (row + 1 != ROW - 1 && col-1!=-1) {
                consistency++;
            
                if (grid[row + 1][col - 1].getValue() != -1 && grid[row + 1][col - 1].getValue() == value) {
                    return false;
                }
            }

       
            if(row-1!=-1 && col+1<COLUMN){
            consistency++;
            if (grid[row - 1][col + 1].getValue() != -1 && grid[row - 1][col + 1].getValue() == value) {
                return false;
            }
            }
        return true;
    }

    static boolean BacktrackingGenrator(int row, int col) {
        Random random = new Random();

        if (row == ROW - 2 && col == COLUMN) {
            return true;
        }

        if (col == COLUMN) {
            row++;
            col = 0;
        }
          
     Domin d=new Domin();

        for (int randomValue = random.nextInt(10); randomValue < 10; randomValue = random.nextInt(10)) {
            if (!d.allChecked()) 
            {
                 if (grid[row][col].getDominValue(randomValue) &&d.getDominValue(randomValue)==true)  {
                    d.setDominValue(randomValue, false);
                 }
                
             else 
                continue;
                
            }
            else
                break;

            
            if (isValid(row, col,randomValue)) {
                grid[row][col].setValue(randomValue);
                //5
                updatePossibilities(row, col, false, randomValue);
                if (BacktrackingGenrator(row, col + 1)) {
                    return true;
                }
                
                
            }
            if ( grid[row][col].getValue()!=-1)
               updatePossibilities(row, col, true, randomValue);
               grid[row][col].setValue(-1);  
        }
       

        return false;
    }

    public static boolean ForwardcheckingMVR() {//minVar[0]=row , minVar[1]=column
        if (isComplete()) //if the end is reached return true
        {
            return true;
        }

        //since we only go and choose unasinged varibles so we dont need this conditon
        int[] minVar = MRV();//Calls method MRV to get cell with least number of values possible 

        for (int num = 0; num < 10; num++) {//loop through all values
            if (grid[minVar[0]][minVar[1]].getDominValue(num)) {//if the num is in the domin

                //grid[minVar[0]][minVar[1]].setValue(num);
                //assignments++;
                if (isValid(minVar[0], minVar[1],num) && checkColumeTotal(minVar[0], minVar[1],num)) {
                    assignments++;
                    grid[minVar[0]][minVar[1]].setValue(num);
                    //check if value violates any constraints

                    updatePossibilities(minVar[0], minVar[1], false,num);//domains of cells that have constrains are updated

                    if (ForwardcheckingMVR())//forward check next cell
                    {
                        return true;
                    }
                }

                updatePossibilities(minVar[0], minVar[1], true,num); //return values to domain
                grid[minVar[0]][minVar[1]].setValue(-1);

            }
        }

        return false;
    }

    static void updatePossibilities(int row, int col, boolean removeORadd,int value) {
        if(value!=-1){
        for (int j = 0; j < COLUMN; j++) {
            if (j != col) {
                if (grid[row][j].getValue() != -1) {
                    grid[row][j].setDominValue(value, removeORadd);
                }
            }
        
                if(row-1!=-1){
                if (grid[row - 1][col].getValue() != -1) {
                    grid[row - 1][col].setDominValue(value, removeORadd);
                }

                }
            
                if (row + 1 != ROW - 1 && grid[row + 1][col].getValue() != -1) {
                    grid[row + 1][col].setDominValue(value, removeORadd);
                }

           
            
                if (col-1!=-1 &&grid[row][col - 1].getValue() != -1) {
                    grid[row][col - 1].setDominValue(value, removeORadd);
                }

           
         
                if (col+1<COLUMN && grid[row][col + 1].getValue() != -1) {
                    grid[row][col + 1].setDominValue(value, removeORadd);
                }

           
          
                if (col+1<COLUMN && row + 1 != ROW - 1 && grid[row + 1][col + 1].getValue() != -1) {
                    grid[row + 1][col + 1].setDominValue(value, removeORadd);
                }

           

           
                if (row-1!=-1&&col-1!=-1 && grid[row - 1][col - 1].getValue() != -1) {
                    grid[row - 1][col - 1].setDominValue(value, removeORadd);
                }

          
        
                if (row + 1 != ROW - 1 && col-1!=-1 && grid[row + 1][col - 1].getValue() != -1) {
                    grid[row + 1][col - 1].setDominValue(value, removeORadd);
                }

                if (col+1<COLUMN&&row-1!=-1 && grid[row - 1][col + 1].getValue() != -1) {
                    grid[row - 1][col + 1].setDominValue(value, removeORadd);
                }

          
        }
        }
    }

    static boolean checkColumeTotal(int row, int col,int value) {
        int sum = 0;
        int count = 0;
        consistency++;
        sum=value;
        for (int i = 0; i < ROW - 1; i++) {
            if (grid[i][col].getValue() != -1) {
                count++;
                sum += grid[i][col].getValue();
            }
        }
        if (sum > grid[ROW - 1][col].getValue()) {
            return false;
        }
        if (count == 2 && sum != grid[ROW - 1][col].getValue()) {
            return false;
        }
        return true;

    }

    static boolean isComplete() {
        for (int i = 0; i < ROW - 1; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (grid[i][j].getValue() == -1) {
                    return false;
                }
            }
        }
        return true;

    }

    static int[] MRV() {
        int min = 10;
        int[] mindim = new int[2];
        for (int i = 0; i < ROW - 1; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (grid[i][j].getValue() == -1 && grid[i][j].DominSize() < min) {
                    min = grid[i][j].DominSize();
                    mindim[0] = i;
                    mindim[1] = j;
                }

            }
        }
        return mindim;
    }

    static void print() {
        System.out.println(" ------------------------------");
        for (int i = 0; i < ROW; i++) {
            System.out.print("|");
            for (int j = 0; j < COLUMN; j++) {
                if (grid[i][j].getValue() != -1) {
                    System.out.printf("%2d ", grid[i][j].getValue());
                } else {
                    System.out.printf("%2s ", " ");
                }
            }
            System.out.println("|");
        }
        System.out.println(" ------------------------------");
    }
    public static void Finalvariableassignment() {
		for (int i = 0; i  < ROW-1; i++) {
            for (int j = 0; j < COLUMN; j++) {
            	System.out.println("row: "+i+" colume: "+j+" assignment "+grid[i][j].getValue()); }
            
		}}
}
    

    

