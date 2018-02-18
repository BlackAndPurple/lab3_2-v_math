package v_math;


import java.util.ArrayList;

public class Interpolation {

   // private double[] coefficients = null;
    private ArrayList<Point> points;
    private double[] result;
    private double[] x;
    private double[] fx;
    private double[] diffs;
    //double[] diffs = new double[s];

    public Interpolation(ArrayList<Point> points) {
        this.points = points;
        result = new double[points.size()];
        x = new double[points.size()];
        fx = new double[points.size()];
        for (int i = 0; i < points.size(); i++){
            fx[i] = points.get(i).getY();
            x[i] = points.get(i).getX();
        }
    }

    public double y(double x, double[] coeffs){
        double y = 0;
        for (int i = 0; i < coeffs.length; i++)
            y+=coeffs[i]*Math.pow(x, i);
        return y;
    }

    private void getDiffs(){
        int s = x.length;
        double[][] newton = new double[(2*s)-1][s+1];
        for(int i=0, z=0;i<s;i++,z+=2){
            newton[z][0]=x[i];
            newton[z][1]=fx[i];
        }
        int i=1, ii=2, j=2, ss=s;
        for(int z=0;z<s-1;z++,j++,ss-=1,ii++){
            for(int y=0;y<ss-1;y++,i+=2){
                newton[i][j]=(newton[i+1][j-1]-newton[i-1][j-1])/(newton[i+(ii-1)][0]-newton[i-(ii-1)][0]);
            }
            i=ii;
        }
        s = x.length;
        diffs = new double[s];
        int n = s;
        for (int m = s-1; m >= 0; m-- ){
            diffs[n - 1] = newton[m][n];
            n--;
        }

    }

    public static double[] getPoly(double[] values, int i) {
        // Start poly: 1.0, neutral value for multiplication
        double[] coefs = {1.0};

        // Accumulate values of products
        for (int j = 0; j <= i; ++j) {
            // 'coefsLocal' represent polynom of 1st degree (x - values[j])
            double[] coefsLocal = {-values[j], 1.0};
            coefs = getPolyProduct(coefs, coefsLocal);
        }

        return coefs;
    }

    public static double[] getPolyProduct(double[] coefs1, double[] coefs2) {
        // Get lengths and degree
        int s1 = coefs1.length - 1;
        int s2 = coefs2.length - 1;
        int degree = s1 + s2;

        // Initialize polynom resulting from product, with null values
        double[] coefsProduct = new double[degree + 1];
        for (int k = 0; k <= degree; ++k) {
            coefsProduct[k] = 0.0;
        }

        // Compute products
        for (int i = 0; i <= s1; ++i)   {
            for (int j = 0; j <= s2; ++j)   {
                coefsProduct[i + j] += coefs1[i] * coefs2[j];
            }
        }
        return coefsProduct;
    }


    public double[] getCoefficients() {
        getDiffs();
        for (int i = 0; i < x.length; ++i) {
            result[i] = 0.0;
        }

        for (int i = 0; i < x.length; ++i) {
            // 'poly' has a degree 'i'. We use 'i - 1' because only terms
            // from 0 to 'i - 1' are used
            double[] poly = getPoly(x, i - 1);

            // Now add to result, do not forget to multiply by the divided
            // difference !
            for (int j = 0; j < poly.length; ++j) {
                result[j] += poly[j] * diffs[i];
            }
        }

        return result;
    }


}
