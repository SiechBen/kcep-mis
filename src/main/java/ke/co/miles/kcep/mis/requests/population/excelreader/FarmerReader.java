package ke.co.miles.kcep.mis.requests.population.excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import ke.co.miles.kcep.mis.requests.population.PopulationTimer;
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
                                    int age;
                                    Integer cellValue = 0;
                                    String cellString = "";
                                    try {
                                        cellString = cell.getStringCellValue();
                                    } catch (Exception e) {
                                        Double d = cell.getNumericCellValue();
                                        cellValue = d.intValue();
                                    }

                                    try {
                                        if (!cellString.trim().equals("") && !cellString.trim().equals("-")) {
                                            if (cellString.trim().equals("<35")) {
                                                person.setAge(new Short("28"));
                                            } else if (cellString.trim().equals(">35")) {
                                                person.setAge(new Short("40"));
                                            } else if (cellString.trim().contains("-")) {
                                                String[] ages = cellString.trim().split("-");
                                                person.setAge(Short.valueOf(String.valueOf(Integer.valueOf(ages[0]) + Integer.valueOf(ages[1]) / 2)));
                                            } else if (cellString.trim().matches("[1-3][0-9][0-9][0-9]")) {
                                                person.setAge(Short.valueOf(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - Integer.valueOf(cellString))));
                                                person.setYearOfBirth(Short.valueOf(cellString));
                                            } else {
                                                person.setAge(Short.valueOf(cellString));
                                            }
                                            if (person.getYearOfBirth() == null || person.getYearOfBirth().equals(new Short("0"))) {
                                                person.setYearOfBirth(Short.valueOf(String.valueOf(person.getAge() == null ? null : Calendar.getInstance().get(Calendar.YEAR) - person.getAge())));
                                            }
                                        }

                                        if (cellValue != 0) {
                                            if (cellValue > 999) {
                                                person.setAge(Short.valueOf(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - cellValue)));
                                                person.setYearOfBirth(cellValue.shortValue());
                                            } else {
                                                person.setAge(cellValue.shortValue());
                                                person.setYearOfBirth(Short.valueOf(String.valueOf(person.getAge() == null ? null : Calendar.getInstance().get(Calendar.YEAR) - person.getAge())));
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
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

        PopulationTimer.recordReadTime(startTime, fileName);

        return peopleMap;
    }

}
