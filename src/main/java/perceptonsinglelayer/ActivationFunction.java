package perceptonsinglelayer;

public abstract class ActivationFunction {
    private double value;

    protected abstract double output(double result);
}
