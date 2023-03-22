

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Random;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Courses_Excel_Provider {
    private String file;
    private XSSFWorkbook wb;
    Random rand = new Random();


    ArrayList<String> english_courses = new ArrayList<String>();
//        names.add(m_teacher_second_name);


    ArrayList<String> english_professors = new ArrayList<String>();
    //        names.add(f_teacher_second_name);
    ArrayList<String> english_uni = new ArrayList<String>();
//        names.add(f_first_name);

    ArrayList<String> russian_courses = new ArrayList<String>();
//        names.add(m_firstname);



    public void run() {
        loadFile();
        readNames();
        close();
    }

    public Courses_Excel_Provider() {
        init();
    }

    private void init() {
        file = "C:\\jupyter\\courses.xlsx";
    }

    public void loadFile() {
        try {
            wb = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException ex) {
            Logger.getLogger(Names_Excel_Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
//        } catch (InvalidFormatException ex) {
//            Logger.getLogger(Names_Excel_Provider.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }


    private void close() {
        try {
            wb.close();
        } catch (IOException ex) {
            Logger.getLogger(Names_Excel_Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void cell_is_full(ArrayList<String> list, Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.STRING) {
                list.add(cell.getStringCellValue());
            }
        }

    }
    private void readNames() {
        XSSFSheet sheet = wb.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        for (int r = 1; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);
            cell_is_full(english_courses, row.getCell(0));
            cell_is_full(english_professors, row.getCell(1));
            cell_is_full(english_uni, row.getCell(2));
            cell_is_full(russian_courses, row.getCell(3));

        }
    }

    public String getEnglish_courses() {
        int n = rand.nextInt(english_courses.size());
        return english_courses.get(n);
    }

    public String getEnglish_professors() {
        int n  = rand.nextInt(english_professors.size());
        return english_professors.get(n);
    }

    public String getEnglish_uni() {
        int n = rand.nextInt(english_uni.size());
        return english_uni.get(n);
    }

    public String getRussian_courses() {
        int n = rand.nextInt(russian_courses.size());
        return russian_courses.get(n);
    }
}
