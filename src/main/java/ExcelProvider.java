import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;

public class ExcelProvider {
    private XSSFWorkbook wb;

    public ArrayList<SQLTable> read_data_for_db(String file){
        ArrayList<SQLTable> tables = new ArrayList<>();
        loadFile(file);
        int count = wb.getNumberOfSheets();
        for(int i = 0; i< count; i++){
            tables.add(read_table_data(i));
        }
        close();
        return tables;
    }
    public SQLTable  read_table_data( int sheet_number) {
//        loadFile(file);
        XSSFSheet sheet = wb.getSheetAt(sheet_number);
        String tableName = sheet.getSheetName();
        String coolumnNames [] = getColumsName(sheet);
        HashMap<String, ArrayList<Object>> sheetData = createHashmapFromSheet(sheet);
        SQLTable table = new SQLTable(tableName, coolumnNames, sheetData);

//        close();
        return table;
    }

//    public String[] run_for_names(String file){
//        loadFile(file);
//        XSSFSheet sheet = wb.getSheetAt(0);
//        String[] columnNmaes = getColumsName(sheet);
//        close();
//        return  columnNmaes;
//    }
    public void loadFile(String file) {

        try {
            wb = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException ex) {
            Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
//        } catch (InvalidFormatException ex) {
//            Logger.getLogger(Names_Excel_Provider.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }


    private void close() {
        try {
            wb.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private String[] getSheetNames(XSSFWorkbook wb){
//        int count = wb.getNumberOfSheets();
//        String [] sheetNames = new String[count];
//        for(int i=0; i <count; i++){
//            XSSFSheet sheet = wb.getSheetAt(i);
//            sheetNames[i] = sheet.getSheetName();
//        }
//        return sheetNames;
//    }

    private String[] getColumsName(XSSFSheet sheet){

        XSSFRow row = sheet.getRow(0);
        int count = row.getLastCellNum();
        String[] names = new String[count];
        for(int i =0; i < count ; i++ ){
            names[i] = row.getCell(i).getStringCellValue();
        }
        return names;
    }
    private String[] getColumsType(XSSFSheet sheet) {
        XSSFRow row = sheet.getRow(2);
        int count = row.getLastCellNum();
        String[] cellTypes = new String[count];
        for (int i = 0; i < count; i++) {

            Cell cell  = row.getCell(i);
            CellType type= cell.getCellType();
            if(type == CellType.NUMERIC) {
                if(DateUtil.isCellDateFormatted(cell)){
                    cellTypes[i] = "date";
                }
                else {
                    cellTypes[i] = "number";
                }
            }
            else if(type == CellType.STRING){
                cellTypes[i] = "string";
            }
            else if(type == CellType.BOOLEAN){
                cellTypes[i] = "boolean";
            }

        }
        return cellTypes;
    }

    public HashMap<String, ArrayList<Object>> createHashmapStructure(String[] columnNames){
        HashMap<String, ArrayList<Object>> columns = new HashMap<>() ;
        for (String columnName : columnNames) {
            columns.put(columnName, new ArrayList<Object>());
        }
        return columns;
    }
    public  void addCellValue(HashMap<String,ArrayList<Object>> sheetHashmap,XSSFRow row, String[] columnnames, String[] columnTypes){

        for (int k = 0; k < columnnames.length; k++) {
            Cell cell = row.getCell(k);
            String cellType = columnTypes[k];
            if (cell != null) {

                if (cellType.equals("string")) {

                    sheetHashmap.get(columnnames[k]).add(cell.getStringCellValue());
                } else if (cellType.equals("number")) {
                   ;
                    if(cell.getCellType() ==CellType.STRING){
                        sheetHashmap.get(columnnames[k]).add((Double)null);
                    }
                    else{

                        sheetHashmap.get(columnnames[k]).add(cell.getNumericCellValue());
                    }
                } else if (cellType.equals("date")) {
                    sheetHashmap.get(columnnames[k]).add(cell.getDateCellValue());
                } else if (cellType.equals("boolean")) {//                        System.out.println("i am in the boolean");
                    sheetHashmap.get(columnnames[k]).add(cell.getBooleanCellValue());
                }
            }
            else {
                sheetHashmap.get(columnnames[k]).add(null);
            }
        }
    }
    public HashMap<String,ArrayList<Object>>  readData( XSSFSheet sheet, HashMap<String,ArrayList<Object>> sheetHashmap,
                          String[] columnnames, String[] columnTypes){
        int max_row = sheet.getLastRowNum();
        for(int i =1; i <= max_row; i++) {
            XSSFRow row = sheet.getRow(i);
            addCellValue(sheetHashmap,row,columnnames,columnTypes);

        }
        return sheetHashmap;
    }

    public HashMap<String,ArrayList<Object>> createHashmapFromSheet(XSSFSheet sheet){

        String[] columnTypes = getColumsType(sheet);
        String[] columnNames = getColumsName(sheet);
        HashMap<String, ArrayList<Object>> sheetData = createHashmapStructure(columnNames);
        sheetData = readData(sheet, sheetData,columnNames,columnTypes);
        return sheetData;
    }











}
