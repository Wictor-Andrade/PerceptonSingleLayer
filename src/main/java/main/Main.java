package main;

import perceptonsinglelayer.ExcelReader;
import perceptonsinglelayer.InputLayer;
import perceptonsinglelayer.Neuron;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione o arquivo Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo Excel", "xlsx", "xls"));

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            ExcelReader er = new ExcelReader();
            er.open(filePath);

            InputLayer il = er.getIl();
            ArrayList<Double> y = er.getOl();


            Neuron n = new Neuron(il, 0.0001, 97, y, Neuron.LINEAR, 241);

            double weight = n.Training();
            System.out.println("Pesos: " + weight);
        }
    }
}
