package perceptonsinglelayer;

import org.decimal4j.util.DoubleRounder;

import java.util.ArrayList;
import java.util.Objects;

public class Neuron {

    public final static String LINEAR = "LINEAR";
    public final static String STEP = "STEP";
    private final InputLayer inputLayer;
    private final ArrayList<Double> weight;
    private final double learningRate;
    private final double minimumAcuracy;
    private final ArrayList<Double> y;
    private final double bias;
    private ActivationFunction function;

    public Neuron(InputLayer il, double learningRate, double minimumAcuracy, ArrayList<Double> y, String activationFunction, double bias) {
        this.inputLayer = il;
        this.learningRate = learningRate;
        this.weight = new ArrayList<Double>();
        this.weightInitialization();
        this.activationInitialization(activationFunction);
        this.minimumAcuracy = minimumAcuracy;
        this.y = y;
        this.bias = bias;
    }

    private void weightInitialization() {
        for (Object il : this.inputLayer.getInputs()) {
            this.weight.add(1.0);
        }
    }

    public double Training() {
        double accuracy = 0.0;
        ArrayList<Double> outPuts;

        while (true) {
            outPuts = new ArrayList<>();

            for (int i = 0; i < this.inputLayer.getInputs().get(0).getInput().size(); i++) {
                ArrayList<Double> row = new ArrayList<>();
                for (int j = 0; j < this.inputLayer.getInputs().size(); j++) {
                    row.add(this.inputLayer.getInputs().get(j).getInput().get(i));
                }

                double sum = this.sumFunction(row);
                double output = this.function.output(sum);

                outPuts.add(output);
            }

            accuracy = this.calculateAcuracy(outPuts);

            if (accuracy >= this.minimumAcuracy) {
                return DoubleRounder.round(this.weight.get(0), 3);
            }

            double totalError = 0;
            for (int i = 0; i < outPuts.size(); i++) {
                double output = outPuts.get(i);
                double target = this.y.get(i);
                if (Double.isNaN(totalError += target - output)) {
                    i = outPuts.size() - 1;
                    break;
                } else {
                    totalError += target - output;
                }
            }

            double meanError = totalError / outPuts.size();

            for (int j = 0; j < this.weight.size(); j++) {
                double inputSum = 0;

                for (int i = 0; i < outPuts.size(); i++) {
                    double input = this.inputLayer.getInputs().get(j).getInput().get(i);
                    inputSum += input;
                }
                double meanInput = inputSum / outPuts.size();

                if (Double.isNaN(meanInput) || Double.isNaN(meanError) || Double.isNaN(this.weight.get(j))) {
                    System.err.println("Erro: valor NaN detectado. Debug:");
                    System.err.println("Input Médio: " + meanInput + ", Erro Médio: " + meanError + ", Peso Atual: " + this.weight.get(j));
                    return Double.MIN_VALUE;
                }

                double newWeight = this.weight.get(j) + (this.learningRate * meanInput * meanError);
                this.weight.set(j, newWeight);
            }
        }
    }


    private double sumFunction(ArrayList<Double> inputs) {
        double outPut = 0;
        for (int i = 0; i < inputs.size(); i++) {
            outPut += (inputs.get(i) * this.weight.get(i));
        }
        outPut += this.bias;
        return outPut;
    }

    private void activationInitialization(String activationFunction) {
        switch (activationFunction) {
            case LINEAR -> {
                this.function = new LinearFunction();
            }
            case STEP -> {
                this.function = new StepFunction();
            }
            default -> {
                System.out.println("Please, select a valid activation function.");
                System.exit(0);
            }
        }
    }

    private double calculateAcuracy(ArrayList<Double> outPuts) {
        double accuracy = 0.0;
        for (int i = 0; i < outPuts.size(); i++) {
            double targetRounded = DoubleRounder.round(this.y.get(i), 4);
            double outputRounded = DoubleRounder.round(outPuts.get(i), 4);

            if (Objects.equals(targetRounded, outputRounded)) {
                accuracy++;
            }
        }
        return (accuracy / outPuts.size()) * 100;
    }

}
