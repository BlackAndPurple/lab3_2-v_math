package v_math;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import static com.sun.glass.ui.Cursor.setVisible;

public class Main extends JFrame{

    private DrawPlot plot;
    private SwitchPointsSet pointsSet;
    private SwitchFunctionToDisplay functionSwitcher;
    private Controller controller;
    private FindValue valueFinder;

    public Main(){
        super("Lab3 | Newton polynomial");
        Container c = getContentPane();
        c.setLayout(new BorderLayout()); // установка менеджера размещения

        controller = new Controller();
        valueFinder = new FindValue(controller);
        plot = new DrawPlot(controller/*, table*/);
        JPanel switchersPanel = new JPanel();
        switchersPanel.setLayout(new BoxLayout(switchersPanel, BoxLayout.Y_AXIS));
        pointsSet = new SwitchPointsSet(plot, controller, valueFinder);
        functionSwitcher = new SwitchFunctionToDisplay(plot, controller);
        switchersPanel.add(pointsSet.getButtonPanel());
        switchersPanel.add(functionSwitcher.getButtonPanel());
        switchersPanel.add(valueFinder.getPanel());
        c.add(switchersPanel,BorderLayout.WEST);
        c.add(plot, BorderLayout.CENTER);
        setSize(880,630); // задание размеров
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);


    }
    public static void main(String[] args) {
        new Main();


    }

}
