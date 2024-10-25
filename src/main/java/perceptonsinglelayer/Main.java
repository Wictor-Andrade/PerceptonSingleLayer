

package perceptonsinglelayer;

public class Main {

    public static void main(String[] args) {
        ExcelReader er = new ExcelReader();
        DataFrame database = er.load("./assets/km-to-milha.xlsx");
        database.printDataFrame();
    }
}
