
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


public class Names_Excel_Provider {


    private String file;
    private XSSFWorkbook wb;
    Random rand = new Random();


    ArrayList<String> m_teacher_second_name = new ArrayList<String>();
//        names.add(m_teacher_second_name);


    ArrayList<String> f_teacher_second_name = new ArrayList<String>();
    //        names.add(f_teacher_second_name);
    ArrayList<String> f_firstname = new ArrayList<String>();
//        names.add(f_first_name);

    ArrayList<String> m_firstname = new ArrayList<String>();
//        names.add(m_firstname);

    ArrayList<String> m_middle_name = new ArrayList<String>();
//        names.add(m_middle_name);

    ArrayList<String> f_middle_name = new ArrayList<String>();
//        names.add(f_middle_name);

    ArrayList<String> m_s_last_name = new ArrayList<String>();
//        names.add(m_s_last_name);

    ArrayList<String> f_s_last_name = new ArrayList<String>();

    public void run() {
        loadFile();
        readNames();
        close();
        System.out.println(f_teacher_second_name.size());
        System.out.println(m_teacher_second_name.size());
       System.out.println(m_middle_name);
    }

    public Names_Excel_Provider() {
        init();
    }

    private void init() {
        file = "C:\\jupyter\\second_names.xlsx";
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
                String val =cell.getStringCellValue();
                if(! val.equals("")) {
                    list.add(val);
                }
            }
        }

    }
    private void readNames() {
        XSSFSheet sheet = wb.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        for (int r = 1; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);
            cell_is_full(m_teacher_second_name, row.getCell(1));
            cell_is_full(f_teacher_second_name, row.getCell(2));
            cell_is_full(f_firstname, row.getCell(3));
            cell_is_full(m_firstname, row.getCell(4));
            cell_is_full(f_middle_name, row.getCell(5));
            cell_is_full(m_middle_name, row.getCell(6));
            cell_is_full(m_s_last_name, row.getCell(7));
            cell_is_full(f_s_last_name, row.getCell(8));
        }
        for(String name: f_firstname) {
            System.out.println(name);
        }
    }

//    public ArrayList<String> getM_teacher_second_name() {
//        return m_teacher_second_name;
//    }
//
//    public ArrayList<String> getM_firstname() {
//        return m_firstname;
//    }
//
//    public ArrayList<String> getM_middle_name() {
//        return m_middle_name;
//    }
//
//    public ArrayList<String> getF_middle_name() {
//        return f_middle_name;
//    }
//
//    public ArrayList<String> getM_s_last_name() {
//        return m_s_last_name;
//    }
//
//    public ArrayList<String> getF_s_last_name() {
//        return f_s_last_name;
//    }
//
//    public ArrayList<String> getF_teacher_second_name() {
//        return f_teacher_second_name;
//    }

    public String getF_firstname() {
        int number = rand.nextInt(f_firstname.size());
        return f_firstname.get(number);
    }

    public String getF_middle_name() {
        int number = rand.nextInt(f_middle_name.size());
        return f_middle_name.get(number);
    }

    public String getF_teacher_second_name() {
        int n = rand.nextInt(f_teacher_second_name.size());
        return f_teacher_second_name.get(n);
    }

    public String getM_firstname() {
        int n = rand.nextInt(m_firstname.size());
        return m_firstname.get(n);
    }

    public String getM_s_last_name() {
        int n = rand.nextInt(m_s_last_name.size());
        return m_s_last_name.get(n);
    }

    public String getF_s_last_name() {
        int n = rand.nextInt(f_s_last_name.size());
        return f_s_last_name.get(n);
    }

    public String getM_middle_name() {
        int n = rand.nextInt(m_middle_name.size());
        return m_middle_name.get(n);
    }

    public String getM_teacher_second_name() {
        int n = rand.nextInt(m_teacher_second_name.size());
        return m_teacher_second_name.get(n);
    }

    //    public static HashMap<String, ArrayList<String>> getNames() throws IOException {
//        String excelFilePath = "C:\\jupyter\\second_names.xlsx";
//        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(excelFilePath));
//        XSSFSheet sheet = myExcelBook.getSheetAt(0);


//        names.add(f_s_last_name);


//        System.out.println(m_middle_name);
//        names.put("m_middle_name", m_middle_name);
//        names.put("f_middle_name", f_middle_name);
//        names.put("f_firstname", f_firstname);
//        names.put("m_firstname", m_firstname);
//        names.put("f_s_last_name", f_s_last_name);
//        names.put("m_s_last_name", m_s_last_name);
//        names.put("m_teacher_second_name", m_teacher_second_name);
//        names.put("f_teacher_second_name", f_teacher_second_name);


    }



