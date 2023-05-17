import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
public class ExcelWriter {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sheet1");
    HashMap<String,Double[]> results = new HashMap<>();

//    results.put("test", test);
    String[] headers = {"Показатель", "X", "Y", "Z"};

    public void CreateExcel(HashMap<String,Double[]> results) throws IOException {
        createHeaders(headers);
        addCalculations(results);
        Write();

    }
    public void createHeaders(String[] headers){
        Row row = sheet.createRow(0);
        for(int i = 0 ; i < headers.length; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }

    }

    public void addCalculations( HashMap<String,Double[]> results){

        AtomicInteger counter = new AtomicInteger(1);
        results.forEach((key, value)->{
            Row row = sheet.createRow(counter.get());
            Cell cell = row.createCell(0);
            cell.setCellValue(key);
            for(int i = 1 ; i <= value.length; i++){
                Cell valueCell = row.createCell(i);
                valueCell.setCellValue(value[i-1]);
            }
            counter.getAndIncrement();
        });
    }

    public void Write() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(".\\Results.xlsx");
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }



    }

