package v_math;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import static v_math.FunctionToDisplay.ALL;
import static v_math.FunctionToDisplay.INTERPOLATION;
import static v_math.FunctionToDisplay.SIMPLE;

public class FindValue {

    private JPanel panel;
    private JTextField input;
    private Label label;
    private Label resultLabel;
   // private Controller controller;
    private Interpolation interpolation;
    private double y;
    private double x;
    //private DataGenerator data;

    public FindValue(Controller controller) {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50,30,50,0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(100, 20));
        panel.setMaximumSize(new Dimension(300, 130));
        label = new Label("Enter x");
        resultLabel = new Label("y(x) = ");
        input = new JTextField();
        panel.add(label);
        panel.add(input);
        panel.add(resultLabel);

        ActionListener listener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               try{
                   x = Double.parseDouble(input.getText());
                   switch (controller.getPointsSet()){
                       case FOUR:
                           interpolation = new Interpolation(new DataGenerator().getNPoints(4));
                           break;
                       case EIGHT:
                           interpolation = new Interpolation(new DataGenerator().getNPoints(8));
                           break;
                       case MANY:
                           interpolation = new Interpolation(new DataGenerator().getSpreadPoints());
                           break;
                       case WRONG:
                           interpolation = new Interpolation(new DataGenerator().getWrongPoints());
                           break;
                   }
                   y = interpolation.y(x, interpolation.getCoefficients());
                   resultLabel.setText("y(x) = " + y);
               }catch (Exception e)
               {
                   resultLabel.setText("y(x) = ???");
               }
                x = 0;
            }
        };

        input.addActionListener(listener);
    }

    public void clear(){
        input.setText("");
        resultLabel.setText("y(x) = ");
    }

    public JPanel getPanel() {
        return panel;
    }
}
