package v_math;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class DrawPlot extends JPanel {

    private double a = 1;
    private double b = 0;
    private ArrayList<Point> points = null;
   // private ArrayList<Point> rightPoints = null;
    private Controller controller;
    private DataGenerator data;
    private Interpolation interpolation;
    private double[] coeffs;
   // private Approximation approximation;
    //private Table table;

    private double y(double x){
        double y = 0;
        for (int i = 0; i < coeffs.length; i++)
            y+=coeffs[i]*Math.pow(x, i);
        return y;
    }

    public DrawPlot(Controller controller/*, Table table*/) {
        this.controller = controller;
        data = new DataGenerator();
        points = data.getNPoints(4);
       /* this.table = table;
        table.setTableData(points);
        */
    }

    public void setCoeff(double a, double b){
        this.a = a;
        this.b = b;
    }

        double f(double x) {
            return a*Math.sin(x) + b;
        }


        private void updatePoints(){
            switch (controller.getPointsSet()){
                case FOUR:
                    points = data.getNPoints(4);
                    break;
                case EIGHT:
                    points = data.getNPoints(8);
                    break;
                case WRONG:
                    points = data.getWrongPoints();
                    break;
                case MANY:
                    points = data.getSpreadPoints();
                    break;
            }
        }
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            //оси координат
            g.drawLine(10, 300, 600, 300);
            g.drawLine(305, 50, 305, 550);

            g.drawLine(10, 50, 10, 550);
            g.drawLine(600, 50, 600, 550);
            g.drawLine(10, 50, 600, 50);
            g.drawLine(10, 550, 600, 550);
            //---------------------------------
            updatePoints();
            if (points != null)
                for (Point point : points)
                    g.drawOval((int)(100/Math.PI*point.getX() + 303), (int)(300 - point.getY()*100 - 1), 4, 4);

            switch (controller.getFunctionToDisplay()){
                case SIMPLE:
                    drawFunction(g, Color.red, false, false);
                    break;
                case INTERPOLATION:
                    interpolation = new Interpolation(points);
                    coeffs = interpolation.getCoefficients();
                    drawFunction(g, Color.blue, false, true);
                    break;
                case ALL:
                    drawFunction(g, Color.red, false, false);
                    interpolation = new Interpolation(points);
                    coeffs = interpolation.getCoefficients();
                    drawFunction(g, Color.blue, false, true);
                    break;
            }
            g.setColor(Color.black);
            g.drawString("2\u03c0", 510, 315);
            g.drawString("0", 310, 315);

        }


        private void drawFunction(Graphics g, Color color, boolean bold, boolean interpolation){
            Polygon p = new Polygon();
            if(!interpolation){
                for (int x = -295; x <= 295; x++) {
                    p.addPoint(x + 305, 300 - (int) (100 * f((x / 200.0) * 2
                            * Math.PI)));
                }
                if (bold)
                    for (int x = -295; x <= 295; x++) {
                        p.addPoint(x + 306, 300 - (int) (50 * f((x / 100.0) * 2
                                * Math.PI)));
                    }
            }else {
                for (int x = -295; x <= 295; x++) {
                    int y = 300 - (int) (100 * y((x / 200.0) * 2 * Math.PI));
                    if ((y <= 550) && (y >= 50))
                        p.addPoint(x + 305, y);
                }
            }



            g.setColor(color);
            g.drawPolyline(p.xpoints, p.ypoints, p.npoints);


        }
    }


