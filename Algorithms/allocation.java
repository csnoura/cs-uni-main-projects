/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dp;

/**
 *
 * @author noura
 */
public class allocation implements Cloneable   {
  

    asset assets[];
    double PortfolioReturn;
    double RiskLevel;
    int totalQuantity;

    public allocation(asset asset1, asset asset2, asset asset3,int i,int amount) {
        assets = new asset[3];
        assets[0] = new asset (asset1);
        assets[1] = new asset (asset2);
        assets[2] = new asset (asset3);
        if(i==0)
            assets[0].quantity+=amount;
        if(i==1)
            assets[1].quantity+=amount;
        if(i==2)
            assets[2].quantity+=amount;
        //for cells that are not A[0][j] or A[i][0]
        if(i!=-1){
        TotalQuan();
        setPortfolioReturn();
        setRiskLevel();
      }

    }
 
    public asset getAsset1() {
        return assets[0];
    }

    public void setAsset1(asset asset1) {
        this.assets[0] = asset1;
    }

    public asset getAsset2() {
        return assets[1];
    }

    public void setAsset2(asset asset2) {
        this.assets[1] = asset2;
    }

    public asset getAsset3() {
        return assets[2];
    }

    public void setAsset3(asset asset3) {
        this.assets[2] = asset3;
    }

    public double getPortfolioReturn() {
        return PortfolioReturn;
    }

    public void setPortfolioReturn() {
        //calculate the Portfolio Return formula of the allocation
        if (totalQuantity==0)
            PortfolioReturn=0;
        else{
        PortfolioReturn = assets[0].getExpectedReturn() * ((double) assets[0].getQuantity() / (double)totalQuantity)
                + assets[1].getExpectedReturn() * ((double) assets[1].getQuantity() / (double) totalQuantity)
                + assets[2].getExpectedReturn() * ((double) assets[2].getQuantity() /(double) totalQuantity);
        }

    }

    public double getRiskLevel() {
        return RiskLevel;
    }

    public void setRiskLevel() {
         //calculate the Portfolio risk formula of the allocation
        if (totalQuantity==0)
            RiskLevel=0;
        else{
         RiskLevel = ((double)assets[0].getQuantity() / (double) totalQuantity) * assets[0].getRisk()+
                                ((double)assets[1].getQuantity() /(double) totalQuantity )* assets[1].getRisk()+
                                ((double)assets[2].getQuantity() / (double) totalQuantity) * assets[2].getRisk();
        
        }
    }

    public void print() {
        //method to print the allocation info
        for (asset asset : assets) {
            System.out.println(asset.ID + ": " + asset.quantity + " units");
        }
        System.out.printf("Expected Portfolio Return: %.3f\n", PortfolioReturn);
        System.out.printf("Portfolio Risk Level: %.3f\n", RiskLevel);

    }
    public void setAssets() {
        //for cell that are  A[0][j] or A[i][0]
       assets[0].quantity=0;
       assets[1].quantity=0;
       assets[2].quantity=0;
       totalQuantity=0;
       
      
    }
    public void TotalQuan(){
         //calculate the Portfolio total Quantity of the allocation
         totalQuantity = assets[0].getQuantity() + assets[1].getQuantity() + assets[2].getQuantity();
    }
 
}


