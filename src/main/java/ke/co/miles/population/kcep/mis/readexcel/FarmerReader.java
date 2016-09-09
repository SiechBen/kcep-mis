package ke.co.miles.population.kcep.mis.readexcel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import ke.co.miles.kcep.mis.utilities.AccountDetails;
import ke.co.miles.kcep.mis.utilities.DivisionalLocationDetails;
import ke.co.miles.kcep.mis.utilities.EblBranchDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.SexDetail;
import ke.co.miles.kcep.mis.utilities.VillageDetails;
import ke.co.miles.kcep.mis.utilities.WardDetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FarmerReader {

    public HashMap<PersonDetails, AccountDetails> retrievePeopleFromExcel(String fileName) {
        // TODO Auto-generated method stub

        long startTime = System.currentTimeMillis();
        HashMap<PersonDetails, AccountDetails> peopleMap = new HashMap<>();
        FileInputStream fileInputStream;
        WardDetails ward;
        VillageDetails village;
        PersonDetails person;
        AccountDetails account;
        LocationDetails location;
        Row row;
        Cell cell;
        Iterator<Row> rowIterator;
        Iterator<Cell> cellIterator;
        EblBranchDetails eblBranch;
        DivisionalLocationDetails divisionalLocation;

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
            PersonDetails a = new PersonDetails();
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                num++;

                ward = new WardDetails();
                village = new VillageDetails();
                person = new PersonDetails();
                account = new AccountDetails();
                location = new LocationDetails();
                eblBranch = new EblBranchDetails();
                divisionalLocation = new DivisionalLocationDetails();

                if (row != null) {
                    // For each row, iterate through all the columns
                    cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();

                        if (cell != null) {
                            switch (cell.getColumnIndex()) {
                                case 0:
                                    person.setId(num);
                                    person.setName(cell.getStringCellValue().trim());
                                    break;
                                case 1:
                                    person.setSex(SexDetail.getSexDetail(cell.getStringCellValue()));
                                    break;
                                case 2:
                                    person.setNationalId(cell.getStringCellValue());
                                    break;
                                case 3:
                                    person.setAge(cell.getStringCellValue());
                                    break;
                                case 4:
                                    account.setAccountNumber(cell.getStringCellValue());
                                    if (cell.getStringCellValue().equals("")) {
                                        account.setAccountNumber("No account");
                                    }
                                    break;
                                case 5:
                                    account.setSolId(cell.getStringCellValue());
                                    break;
                                case 6:
                                    eblBranch.setName(cell.getStringCellValue());
                                    account.setEblBranch(eblBranch);
                                    break;
                                case 7:
                                    try {
                                        String divisionalLocationName = cell.getStringCellValue();
                                        if (divisionalLocationName == null || divisionalLocationName.trim().length() < 2) {
                                            location.setDivisionalLocation(null);
                                        } else {
                                            divisionalLocation.setName(divisionalLocationName);
                                            location.setDivisionalLocation(divisionalLocation);
                                        }
                                    } catch (Exception e) {
                                        location.setDivisionalLocation(null);
                                    }
                                    person.setLocation(location);
                                    break;
                                case 8:
                                    ward.setId(new Double(cell.getNumericCellValue()).shortValue());
                                    location.setWard(ward);
                                    person.setLocation(location);
                                    break;
                                case 9:
                                    try {
                                        String villageName = cell.getStringCellValue();
                                        if (villageName == null || villageName.trim().length() < 2) {
                                            location.setVillage(null);
                                        } else {
                                            village.setName(villageName);
                                            location.setVillage(village);
                                            person.setLocation(location);
                                        }
                                    } catch (Exception e) {
                                        location.setVillage(null);
                                    }
                                    break;
                                default:
                            }
                        }
                    }

                    // end iterating a row, add all the elements of a row in list
                    peopleMap.put(person, account);

                }
            }
            fileInputStream.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error " + e);
        }

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        System.out.format("Time taken to read %s was %d seconds%n%n", fileName, timeTaken / 60);
        try {
            FileWriter fileWriter = new FileWriter("data/time_taken.txt", true);
            new File("data/time_taken.txt").getParentFile().mkdir();
            try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter))) {
                printWriter.println("\nRecords were read successfully from " + fileName + ".");
                printWriter.println("\nIt took  " + timeTaken / 60 + " seconds to complete the read.\n");
                //<editor-fold defaultstate="collapsed" desc="TimeUnit">
//                printWriter.write("Reading started at "
//                        + String.format("%02d:%02d:%02d",
//                                TimeUnit.MILLISECONDS.toHours(startTime),
//                                TimeUnit.MILLISECONDS.toMinutes(startTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(startTime)),
//                                TimeUnit.MILLISECONDS.toSeconds(startTime) - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startTime))) + " \n");
//                printWriter.write("Reading stopped at "
//                        + String.format("%02d:%02d:%02d",
//                                TimeUnit.MILLISECONDS.toHours(endTime),
//                                TimeUnit.MILLISECONDS.toMinutes(endTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(endTime)),
//                                TimeUnit.MILLISECONDS.toSeconds(endTime) - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(endTime))) + " \n");

//</editor-fold>
                printWriter.flush();
            }
        } catch (Exception e) {
        }

        return peopleMap;
    }

}
