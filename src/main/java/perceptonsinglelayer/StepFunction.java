package perceptonsinglelayer;

public class StepFunction extends ActivationFunction {

    @Override
    protected double output(double result) {
        if (result >= 1) {
            return 1;
        }
        return 0;
    }

}
