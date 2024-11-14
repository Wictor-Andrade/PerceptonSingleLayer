package perceptonsinglelayer;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelReader {

    private InputLayer il;
    private ArrayList<Double> ol;

    public void open(String PATH) {
        String excelFilePath = PATH;
        ArrayList<Double> coluna1 = new ArrayList<>();
        ArrayList<Double> coluna2 = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath)); Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            // Pega a primeira planilha (sheet) do arquivo
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cellColuna1 = row.getCell(0);
                Cell cellColuna2 = row.getCell(1);
                if (cellColuna1 != null) {
                    double value = cellColuna1.getNumericCellValue();
                    coluna1.add(value);
                }
                if (cellColuna2 != null) {
                    if (cellColuna2.getCellType() == CellType.FORMULA) {
                        evaluator.evaluateFormulaCell(cellColuna2);
                        Double value = cellColuna2.getNumericCellValue();
                        coluna2.add(value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList inputs = new ArrayList<Input>();
        Input x1 = new Input(coluna1);

        inputs.add(x1);

        this.il = new InputLayer(inputs);
        this.ol = coluna2;

    }

    public InputLayer getIl() {
        return il;
    }

    public ArrayList<Double> getOl() {
        return ol;
    }


}

