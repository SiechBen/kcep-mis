package ke.co.miles.population.kcep.mis.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import ke.co.miles.defaults.Default;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.SexDetail;
import ke.co.miles.kcep.mis.utilities.WardDetails;
import ke.co.miles.population.kcep.mis.PopulationTimer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AgroDealerReader {

    public List<PersonDetails> retrievePeopleFromExcel(String fileName) {
        // TODO Auto-generated method stub

        long startTime = System.currentTimeMillis();

        List<PersonDetails> people = new ArrayList<>();
        FileInputStream file;
        WardDetails ward;
        PersonDetails person;
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
                System.out.println("Error " + e.getMessage());
                return null;
            }

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Iterate through each row
            rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                row = rowIterator.next();

                ward = new WardDetails();
                location = new LocationDetails();
                person = new PersonDetails();

                if (row != null) {
                    // For each row, iterate through all the columns
                    cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();

                        if (cell != null) {
                            switch (cell.getColumnIndex()) {
                                case 0:
                                    person.setName(cell.getStringCellValue().trim());
                                    break;
                                case 1:
                                    person.setSex(SexDetail.getSexDetail(new Double(cell.getNumericCellValue()).shortValue()));
                                    break;
                                case 2:
                                    person.setBusinessName(cell.getStringCellValue().trim());
                                    break;
                                case 3:
                                    ward.setId(new Double(cell.getNumericCellValue()).shortValue());
                                    location.setWard(ward);
                                    person.setLocation(location);
                                    break;
                                case 4:
                                    String[] phoneNumbers = cell.getStringCellValue().split("/");
                                    person.setNationalId(phoneNumbers[0]);
                                    break;
                                default:
                            }
                        }
                    }

                    // end iterating a row, add all the elements of a row in list
                    if (person.getNationalId().trim().length() != 0) {
                        people.add(person);
                    } else {
                        person.setNationalId(Default.generateRandomPhoneNumber(Calendar.getInstance().getWeekYear()));
                    }
                }
            }
            file.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error " + e);
        }

        PopulationTimer.recordReadTime(startTime, fileName);

        return people;
    }

}
