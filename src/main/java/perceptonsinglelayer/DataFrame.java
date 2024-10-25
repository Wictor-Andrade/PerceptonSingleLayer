/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perceptonsinglelayer;

import java.util.ArrayList;
public class DataFrame {
    private ArrayList<Double> inputs;
    private ArrayList<Double> outputs;

    public DataFrame(ArrayList<Double> inputs, ArrayList<Double> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    
    public ArrayList<Double> getInputs() {
        return inputs;
    }

    public ArrayList<Double> getOutputs() {
        return outputs;
    }


    public void printDataFrame() {
        for (int i = 0; i < inputs.size(); i++) {
            System.out.println("(Input): " + inputs.get(i) + " (Output): " + outputs.get(i));
        }
    }
    

}
