/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author noura
 */
public class DP {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //list for the assets to store them 
        List<asset> list = new ArrayList<>();
        allocation A[][];
        try {
            //opening a file to read the assets 
            File asset = new File("assets2.txt");
            Scanner read = new Scanner(asset);
            //loop to read and create asset object until the end of the file
            while (read.hasNextLine()) {
                String data = read.nextLine().trim();
                String[] arr = data.split(":");
                String name = arr[0];
                double value1 = Double.parseDouble(arr[1]);
                double value2 = Double.parseDouble(arr[2]);
                int number = Integer.parseInt(arr[3].trim());
                //create an asset obj
                asset val = new asset(name, value1, value2, number);
                //add the asset obj to the list 
                list.add(val);
            }
            // close the scanner
            read.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            System.out.println(e);
        }
        Scanner scanner = new Scanner(System.in);
        //read total investment and risk tolerance from user
        System.out.println("Enter the total investment: ");
        int invest = scanner.nextInt();
        System.out.println("Enter the risk tolerance: ");
        double tolerance = scanner.nextDouble();
        //create a dp table of optiaml  Allocations of the subproblems such that each cell riskLevel<=risk tolerance
        A = new allocation[list.size() + 1][invest + 1];
        allocation currentAloc;
        for (int i = 0; i <= list.size(); i++) {
            for (int j = 0; j <= invest; j++) {
                //if the A[0][j] or A[i][0] then cell will have an empty alloctaion 
                if (i == 0 || j == 0) {
                    A[i][j] = new allocation(list.get(0), list.get(1), list.get(2), -1, 0);
                    //to make all assets quantity 0
                    A[i][j].setAssets();
                } else {
                    //first A[i][j]=A[i-1][j] means we will not include any quantity of the asset i
                    A[i][j] = new allocation(A[i - 1][j].getAsset1(),
                            A[i - 1][j].getAsset2(), A[i - 1][j].getAsset3(), i - 1, 0);
                    //we try diff quantity of the asset i (we try fractions of asset i)
                    for (int k = 1; k <= Math.min(j, list.get(i - 1).quantity); k++) {
                        //we create obj with the  A[i-1][j-k] with K amount from asset i
                        currentAloc = new allocation(A[i - 1][j - k].getAsset1(),
                                A[i - 1][j - k].getAsset2(), A[i - 1][j - k].getAsset3(), i - 1, k);
                        //check if the current alocation is whitn the risk tolerance and if its profit > prev profit
                        if (currentAloc.RiskLevel <= tolerance && currentAloc.getPortfolioReturn() > A[i][j].getPortfolioReturn()) {
                            //update A[i][j] with current aloc
                            A[i][j] = currentAloc;

                        }

                    }

                }
            }

        }

//print the optiaml alocation
        A[list.size()][invest].print();
    }

}
