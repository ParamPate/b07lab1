
public class Polynomial{
    private double coeffients[];
    private int exp[]; 
    public Polynomial(){
        coeffients = new double[]{0.0};
        exp = new int[]{0};
    }
    public Polynomial(double arr[]){
        coeffients = arr;
        int j = 0; 
        int n = 0; 
        for(int i =0; i < coeffients.length; i++){
            if(coeffients[i] != 0){
                j++; 
            }
        }
        exp = new int[j];

        for(int i = 0; i < coeffients.length; i++){
            if(coeffients[i] != 0){
                exp[n] = n;
                n++;  
            }
        }       


    }
    public Polynomial add(Polynomial x){
        int length = Math.max(x.coeffients.length, coeffients.length); 
        double[] addition = new double[length];
        for(int i = 0; i <length; i++){
            double y; 
            double z; 
            if(i >= x.coeffients.length){
                y = 0.0; 
            }
            else{
                y = x.coeffients[i]; 
            }
            if(i >= coeffients.length){
                z = 0.0; 
            }
            else{
                z = coeffients[i]; 
            }
            addition[i] = y + z; 
        }
        return new Polynomial(addition);
    }

    public double evaluate(double x){
        double val = 0.0; 
        for(int i = 0; i <coeffients.length; i++){
            val += coeffients[i] * Math.pow(x,i); 
        }
        return val; 

    }
    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}
