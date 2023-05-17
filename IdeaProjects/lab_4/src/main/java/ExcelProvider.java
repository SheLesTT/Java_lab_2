import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelProvider {
    private XSSFWorkbook wb;



    ArrayList<Double> X = new ArrayList<Double>();

    ArrayList<Double> Y = new ArrayList<Double>();
    //        names.add(f_teacher_second_name);
    ArrayList<Double> Z = new ArrayList<Double>();
//        names.add(f_first_name);

    public  void  load_file(String file){
        loadFile(file);
    }
    public void run_for_data() {

        readData();
        close();
    }


    public void loadFile(String file) {

        try {
            wb = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException ex) {
            String errorMessage = "An error occurred: " + ex.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NotOfficeXmlFileException e){
            String errorMessage = "An error occurred: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
//        } catch (InvalidFormatException ex) {
//            Logger.getLogger(Names_Excel_Provider.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }


    private void close() {
        try {
            if (wb != null){
            wb.close();}
        } catch (IOException ex) {
//            Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void readData() {
        try {
            XSSFSheet sheet = wb.getSheetAt(7);
            int rows = sheet.getLastRowNum();
            for (int r = 1; r <= rows; r++) {
                XSSFRow row = sheet.getRow(r);
                X.add((row.getCell(0).getNumericCellValue()));
                Y.add((row.getCell(1).getNumericCellValue()));
                Z.add((row.getCell(2).getNumericCellValue()));
            }
        }catch (NullPointerException e){


        }




    }

    public ArrayList<Double>[] getData() {
        ArrayList<Double>[] data = new ArrayList[3];
        data[0] = X;
        data[1] = Y;
        data[2] = Z;
        return data;
    }

}


