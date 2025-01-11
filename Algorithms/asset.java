/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dp;

/**
 *
 * @author noura
 */
public class asset {
  
    String ID;
    double getExpectedReturn;
    double risk;
    int quantity;

    public asset(String ID, double EXPreturn, double risk, int quantity) {
        this.ID = ID;
        this.getExpectedReturn = EXPreturn;
        this.risk = risk;
        this.quantity= quantity;
    }
      public asset(asset a) {
        this.ID = a.ID;
        this.getExpectedReturn = a.getExpectedReturn;
        this.risk = a.risk;
        this.quantity= a.quantity;
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getExpectedReturn() {
        return getExpectedReturn;
    }

    public void setEXPreturn(double EXPreturn) {
        this.getExpectedReturn = EXPreturn;
    }

    public double getRisk() {
        return risk;
    }

    public void setRisk(double risk) {
        this.risk = risk;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
} 

