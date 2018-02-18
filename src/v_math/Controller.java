package v_math;

//import static v_math.FunctionToDisplay.SIMPLE;
import static v_math.FunctionToDisplay.SIMPLE;
import static v_math.PointsSet.FOUR;

public class Controller {
    private DrawPlot plot;
    private FunctionToDisplay functionToDisplay = SIMPLE;

    private PointsSet pointsSet = FOUR;

    public Controller() {
    }
    public void setPointsSet(PointsSet pointsSet) {
        this.pointsSet = pointsSet;
    }

    public PointsSet getPointsSet() {
        return pointsSet;
    }

    public FunctionToDisplay getFunctionToDisplay() {
        return functionToDisplay;
    }

    public void setFunctionToDisplay(FunctionToDisplay functionToDisplay) {
        this.functionToDisplay = functionToDisplay;
    }

}
