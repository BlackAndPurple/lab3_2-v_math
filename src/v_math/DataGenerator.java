package v_math;

import java.util.ArrayList;

public class DataGenerator {

    private double f(double x){
        return Math.sin(x);
    }
    public ArrayList<Point> getNPoints(int n){
        double step = 2*Math.PI / n;
        ArrayList<Point> points = new ArrayList<>();
        for (double x = 0; x < 2*Math.PI; x+=step){
            points.add(new Point(x, f(x)));
        }
        return  points;
    }

    public ArrayList<Point> getWrongPoints(){

        ArrayList<Point> points = getNPoints(5);
        //points.add(new Point(Math.PI, 3));
        points.add(new Point(Math.PI - 1, 1));
        return  points;
    }

    public ArrayList<Point> getSpreadPoints(){
        double step = 10*Math.PI / 8;
        ArrayList<Point> points = new ArrayList<>();
        for (double x = -5*Math.PI; x < 5*Math.PI; x+=step){
            points.add(new Point(x, f(x)));
        }
        return  points;
    }

    public ArrayList<Point> getTestPoints(){

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1,1));
        points.add(new Point(2,7));
        points.add(new Point(3,6));
        return points;
    }
}
