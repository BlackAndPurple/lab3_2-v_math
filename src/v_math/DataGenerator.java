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
        points.add(new Point(Math.PI - 1, 2));
        return  points;
    }

    public ArrayList<Point> getManyWrongPoints(){
        ArrayList<Point> points = getNPoints(5);
        points.add(new Point(Math.PI, 1.5));
        points.add(new Point(Math.PI+3, 1.4));
        points.add(new Point(Math.PI + 0.5, 0.2));
        return  points;
    }

    public ArrayList<Point> getRightPoints(ArrayList<Point> points) {
        ArrayList<Point> rightPoints = (ArrayList)points.clone();
        double max_offset = 0;
        Point point_to_delete = null;
        for (Point point : rightPoints){
            double offset = Math.pow(point.getY() - f(point.getX()), 2);
            if (offset > max_offset){
                max_offset = offset;
                point_to_delete = point;
            }
        }
        if (point_to_delete != null)
            rightPoints.remove(point_to_delete);
        return rightPoints;

    }
}
