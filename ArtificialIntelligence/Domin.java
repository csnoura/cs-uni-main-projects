/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aiproject;

/**
 *
 * @author noura
 */
public class Domin {
 private boolean dominValues [] = new boolean [10];
 private int value;
 

  // true for an index menas that the value or the index number is valid as an assigment
    public Domin() {
    //1
       for ( int i =0;i<10;i++)
           dominValues [i]=true;
     value=-1;
    }
    //2
    public void ReAssignDominValue(){
          for (int i =0;i<10;i++)
           dominValues [i]=true; 
          
       
    }
    public void setDominValue(int i,boolean answer) {
        dominValues[i]=answer;
    }
    
    public int getDominLength() {
        return dominValues.length;
    }
    //3
       public boolean allChecked() {
        
        for ( int i =0;i<10;i++){
           if(dominValues [i]!=false)
               return false;
    }
            return true;
}
       public boolean getDominValue(int i) {
        return dominValues[i];
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
      public int DominSize() {
          int counter=0;
             for (int i =0;i<10;i++)
           if(dominValues [i]==true) 
             counter++;
          
       return counter;
    }

   
    }
       
