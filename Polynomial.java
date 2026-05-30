import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Polynomial{
    private double coeffients[];
    private int exp[]; 
    public Polynomial(){
        coeffients = new double[]{0.0};
        exp = new int[]{0};
    }
    public Polynomial(double arr[]){
        int j = 0; 
        int n = 0; 
        for(int i =0; i < arr.length; i++){
            if(arr[i] != 0){
                j++; 
            }
        }
        coeffients = new double[j];
        exp = new int[j];

        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0){
                exp[n] = i;
                coeffients[n] = arr[i];
                n++;  
            }
        }       
    }
    public Polynomial(double[]arr, int[] x){
        coeffients = arr; 
        exp = x; 
    }
    public Polynomial(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file); 
        String line = ""; 
        if(scanner.hasNextLine()){
            line = scanner.nextLine(); 
        }
        scanner.close(); 
        if(line.isEmpty() == true){
            coeffients = new double[0];
            exp = new int[0]; 
            return; 
        }
        line = line.replace("-", "+-"); 
        String [] split = line.split("\\+"); 
        int valid = 0; 
        double[] tempC = new double[split.length]; 
        int[] tempE = new int[split.length]; 
        for(int i = 0; i < split.length; i++){
            if(split[i].isEmpty()){
                continue; 
            }
            if(split[i].contains("x") != true){
                tempC[valid] = Double.parseDouble(split[i]); 
                tempE[valid] = 0; 
            }
            else{
                int x = split[i].indexOf("x"); 
                String left = split[i].substring(0,x); 
                String right = split[i].substring(x+1);
                if(left.equals("") || left.equals("+")){
                    tempC[valid] = 1.0; 
                }
                else if(left.equals("-")){
                    tempC[valid] = -1.0; 
                }
                else{
                    tempC[valid] = Double.parseDouble(left);
                }
                if(right.equals("")){
                    tempE[valid] = 1; 
                }
                else{
                    tempE[valid] = Integer.parseInt(right); 
                }
            
            }
            valid++; 
    }
        coeffients = Arrays.copyOf(tempC, valid);
        exp = Arrays.copyOf(tempE, valid);
}
    public Polynomial add(Polynomial x){
        int length = x.coeffients.length + coeffients.length; 
        double[] addition = new double[length];
        int[] exps = new int[length];

        int i = 0; // c
        int j = 0; //x
        int size = 0; 
        while(i < coeffients.length && j < x.coeffients.length){
             if(exp[i] == x.exp[j]){
                double sum = coeffients[i] + x.coeffients[j]; 
                if(sum != 0){
                    addition[size] = sum; 
                    exps[size] = exp[i]; 
                    size++; 
                }
                i++; 
                j++; 
            }
            else if(exp[i] < x.exp[j]){
                addition[size] = coeffients[i]; 
                exps[size] = exp[i]; 
                size++; 
                i++; 
            } 
            else{
                addition[size] = x.coeffients[j]; 
                exps[size] = x.exp[j]; 
                size++; 
                j++;   
            }
        }
        while(i < coeffients.length){
            addition[size] = coeffients[i]; 
            exps[size] = exp[i]; 
            size++; 
            i++;  
        }
        while(j <x.coeffients.length){
            addition[size] = x.coeffients[j]; 
            exps[size] = x.exp[j]; 
            size++; 
            j++;           
        }
        double []addition_final = Arrays.copyOf(addition, size); 
        int [] exps_final = Arrays.copyOf(exps,size); 
        
        return new Polynomial(addition_final, exps_final); 
    }

    public double evaluate(double x){
        double val = 0.0; 
        for(int i = 0; i <coeffients.length; i++){
            val += coeffients[i] * Math.pow(x,exp[i]); 
        }
        return val; 

    }
    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
    public Polynomial multiply(Polynomial x){
        if(coeffients.length == 0 || x.coeffients.length == 0){
            return new Polynomial(); 
        }
        int maxE = 0; 
        for(int i = 0; i<exp.length; i++){
            for(int j =0; j<x.exp.length; j++){
                int max = exp[i] + x.exp[j];
                if(max > maxE){
                    maxE = max; 
                }
            }
        }
        double[] values = new double [maxE + 1]; 
        for(int i = 0; i <coeffients.length; i++){
            for(int j = 0; j < x.coeffients.length; j++){
                values[exp[i]+x.exp[j]] += coeffients[i] * x.coeffients[j]; 
            }
        }
        return new Polynomial(values); 
    }

}
