package perceptonsinglelayer;


import java.util.ArrayList;

public class InputLayer {
    private final ArrayList<Double> inputs;
    private double weight;

    public InputLayer(ArrayList<Double> inputs) {
        this.inputs = inputs;
        this.weight = 0;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ArrayList<Double> getInputs() {
        return this.inputs;
    }
}

