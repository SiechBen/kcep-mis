/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.population.kcep.mis.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ke.co.miles.population.kcep.mis.PopulationTimer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author siech
 */
public class GFSReader {

    public List<GFSSCodeDetails> retrieveGFFSCodesFromExcel(String fileName) {
        // TODO Auto-generated method stub

        long startTime = System.currentTimeMillis();
        List<GFSSCodeDetails> gfssCodes = new ArrayList<>();
        GFSSCodeDetails gfssCode;
        FileInputStream fileInputStream;
        Row row;
        Cell cell;
        Iterator<Row> rowIterator;
        Iterator<Cell> cellIterator;

        try {
            fileInputStream = new FileInputStream(new File(fileName));

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook;
            try {
                workbook = new XSSFWorkbook(fileInputStream);
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
                return null;
            }

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Iterate through each row
            rowIterator = sheet.iterator();
            int num = 0;
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                gfssCode = new GFSSCodeDetails(++num);

                if (row != null) {
                    // For each row, iterate through all the columns
                    cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();

                        if (cell != null) {
                            switch (cell.getColumnIndex()) {
                                case 0:
                                    gfssCode.setCode(String.valueOf(cell.getNumericCellValue()));
                                    System.out.print("Code: " + gfssCode.getCode() + " ");
                                    break;
                                case 1:
                                    gfssCode.setItem(cell.getStringCellValue().trim());
                                    System.out.println("Item: " + gfssCode.getItem());
                                    break;

                                default:
                            }
                        }

                    }

                    gfssCode.setId(num);
                    gfssCodes.add(gfssCode);

                    // end iterating a row, add all the elements of a row in list
                }
            }
            fileInputStream.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error " + e);
        }

        PopulationTimer.recordReadTime(startTime, fileName);

        return gfssCodes;
    }

}
