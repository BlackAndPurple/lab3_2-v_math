package v_math;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static v_math.PointsSet.*;

public class SwitchPointsSet {
    private ButtonGroup group;
    private JPanel buttonPanel;

    private DataGenerator data;
    SwitchPointsSet(DrawPlot plot, Controller controller, FindValue findValue){

        data = new DataGenerator();
        group = new ButtonGroup();
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50,30,50,50));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        JRadioButton radio4Points = new JRadioButton("4 points", true);
        group.add(radio4Points);
        buttonPanel.add(radio4Points);
        JRadioButton radio8Points = new JRadioButton("8 points", false);
        group.add(radio8Points);
        buttonPanel.add(radio8Points);
        JRadioButton radioWrongPoints = new JRadioButton("One wrong point", false);
        group.add(radioWrongPoints);
        buttonPanel.add(radioWrongPoints);
        JRadioButton radioManyPoints = new JRadioButton("Many points", false);
        group.add(radioManyPoints);
        buttonPanel.add(radioManyPoints);
        ActionListener listener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if (radio4Points.isSelected())
                    controller.setPointsSet(FOUR);
                if (radio8Points.isSelected())
                    controller.setPointsSet(EIGHT);
                if (radioWrongPoints.isSelected())
                    controller.setPointsSet(WRONG);
                if (radioManyPoints.isSelected())
                    controller.setPointsSet(MANY);
                plot.repaint();
                findValue.clear();
            }
        };
        radio4Points.addActionListener(listener);
        radio8Points.addActionListener(listener);
        radioWrongPoints.addActionListener(listener);
        radioManyPoints.addActionListener(listener);
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

}
