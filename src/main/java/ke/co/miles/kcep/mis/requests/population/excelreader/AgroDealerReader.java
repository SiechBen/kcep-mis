package ke.co.miles.kcep.mis.requests.population.excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.Generator;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.population.PopulationTimer;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.SexDetail;
import ke.co.miles.kcep.mis.utilities.WardDetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AgroDealerReader {

    public List<PersonDetails> retrievePeopleFromExcel(String fileName) throws MilesException {
        // TODO Auto-generated method stub

        long startTime = System.currentTimeMillis();

        List<PersonDetails> people = new ArrayList<>();
        FileInputStream file;
        WardDetails ward;
        PersonDetails agroDealer;
        LocationDetails location;
        Row row;
        Cell cell;
        Iterator<Row> rowIterator;
        Iterator<Cell> cellIterator;

        try {
            file = new FileInputStream(new File(fileName));

            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook;
            try {
                workbook = new XSSFWorkbook(file);
            } catch (Exception e) {
                MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                LOGGER.log(Level.WARNING, "Error {0}", e.getMessage());
                return null;
            }

            // Get desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(1);

            /* Get the heading row */
            row = sheet.getRow(0);
            if (row.getCell(0).getStringCellValue().equals("Full name")
                    && row.getCell(1).getStringCellValue().equals("Gender(F or M)")
                    && row.getCell(2).getStringCellValue().equals("Business name")
                    && row.getCell(3).getStringCellValue().equals("Ward ID")
                    && row.getCell(4).getStringCellValue().equals("Phone number")) {

                // Iterate through each row
                rowIterator = sheet.iterator();
                rowIterator.next();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    row = rowIterator.next();

                    ward = new WardDetails();
                    location = new LocationDetails();
                    agroDealer = new PersonDetails();

                    if (row != null) {
                        // For each row, iterate through all the columns
                        cellIterator = row.cellIterator();

                        while (cellIterator.hasNext()) {
                            cell = cellIterator.next();

                            if (cell != null) {
                                switch (cell.getColumnIndex()) {
                                    case 0:
                                        agroDealer.setName(cell.getStringCellValue().trim());
                                        break;
                                    case 1:
                                        agroDealer.setSex(SexDetail.getSexDetail(cell.getStringCellValue()));
                                        break;
                                    case 2:
                                        agroDealer.setBusinessName(cell.getStringCellValue().trim());
                                        break;
                                    case 3:
                                        try {
                                            ward.setId(new Double(cell.getNumericCellValue()).shortValue());
                                            location.setWard(ward);
                                            agroDealer.setLocation(location);
                                        } catch (Exception e) {
                                            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                                            ward.setId(null);
                                            agroDealer.setLocation(location);
                                        }
                                        break;
                                    case 4:
                                        String[] phoneNumbers = cell.getStringCellValue().split("/");
                                        agroDealer.setNationalId(phoneNumbers[0]);
                                        break;
                                    default:
                                }
                            }
                        }

                        // end iterating a row, add all the elements of a row in list
                        if (agroDealer.getNationalId().trim().length() != 0) {
                            people.add(agroDealer);
                        } else {
                            agroDealer.setNationalId(Generator.generateRandomPhoneNumber(Calendar.getInstance().getWeekYear()));
                        }
                    }

                }
            } else {
                throw new InvalidArgumentException("invalid_people_upload_file");
            }
            file.close();
        } catch (IOException | NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Error {0}", e.getMessage());
        }

        PopulationTimer.recordReadTime(startTime, fileName);

        return people;
    }

    private static final Logger LOGGER = Logger.getLogger(AgroDealerReader.class.getSimpleName());

}
