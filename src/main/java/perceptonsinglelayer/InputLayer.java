package perceptonsinglelayer;


import java.util.ArrayList;

public class InputLayer {
    private final ArrayList<Input> inputs;

    public InputLayer(ArrayList<Input> inputs) {
        this.inputs = inputs;
    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }


}