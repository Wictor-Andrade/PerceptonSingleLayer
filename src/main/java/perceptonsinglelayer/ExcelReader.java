package perceptonsinglelayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public DataFrame load(String path) {
        String excelFilePath = path; 

        DataFrame result;

        ArrayList<Double> coluna1 = new ArrayList<>();
        ArrayList<Double> coluna2 = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath)); Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            Sheet sheet = workbook.getSheetAt(0);

            int collumns = (short) sheet.getRow(0).getLastCellNum();

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

        return new DataFrame(coluna1, coluna2);
    }
}
