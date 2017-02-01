package ke.co.miles.kcep.mis.requests.population.excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
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

    public HashMap<PersonDetails, AccountDetails> retrievePeopleFromExcel(String fileName) throws MilesException {

        long startTime = System.currentTimeMillis();
        HashMap<PersonDetails, AccountDetails> peopleMap = new HashMap<>();
        FileInputStream fileInputStream;
        WardDetails ward;
        VillageDetails village;
        PersonDetails farmer;
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
                LOGGER.log(Level.WARNING, "Error {0}", e.getMessage());
                return null;
            }

            // Get desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            /* Get the heading row */
            row = sheet.getRow(0);
            if (row.getCell(0).getStringCellValue().equals("Full name")
                    && row.getCell(1).getStringCellValue().equals("Gender(F or M)")
                    && row.getCell(2).getStringCellValue().equals("National ID")
                    && row.getCell(3).getStringCellValue().equals("Age(e.g. 32) or YOB (e.g. 1984)")
                    && row.getCell(4).getStringCellValue().equals("Bank account number")
                    && row.getCell(5).getStringCellValue().equals("SOL ID")
                    && row.getCell(6).getStringCellValue().equals("Equity branch name")
                    && row.getCell(7).getStringCellValue().equals("Divisional location name")
                    && row.getCell(8).getStringCellValue().equals("Ward ID")
                    && row.getCell(9).getStringCellValue().equals("Village name")) {

                int num = 0;
                /* Iterate through each row */
                rowIterator = sheet.iterator();
                rowIterator.next();
                rowIterator.next();
                rowIterator.next();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    row = rowIterator.next();
                    num++;

                    ward = new WardDetails();
                    village = new VillageDetails();
                    farmer = new PersonDetails();
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
                                        farmer.setId(num);
                                        farmer.setName(cell.getStringCellValue().trim());
                                        break;
                                    case 1:
                                        farmer.setSex(SexDetail.getSexDetail(cell.getStringCellValue()));
                                        break;
                                    case 2:
                                        try {
                                            farmer.setNationalId(cell.getStringCellValue());
                                        } catch (Exception e) {
                                            Double d = cell.getNumericCellValue();
                                            farmer.setNationalId(d.toString());
                                        }
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
                                                    farmer.setAge(new Short("28"));
                                                } else if (cellString.trim().equals(">35")) {
                                                    farmer.setAge(new Short("40"));
                                                } else if (cellString.trim().contains("-")) {
                                                    String[] ages = cellString.trim().split("-");
                                                    farmer.setAge(Short.valueOf(String.valueOf(Integer.valueOf(ages[0]) + Integer.valueOf(ages[1]) / 2)));
                                                } else if (cellString.trim().matches("[1-3][0-9][0-9][0-9]")) {
                                                    farmer.setAge(Short.valueOf(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - Integer.valueOf(cellString))));
                                                    farmer.setYearOfBirth(Short.valueOf(cellString));
                                                } else {
                                                    farmer.setAge(Short.valueOf(cellString));
                                                }
                                                if (farmer.getYearOfBirth() == null || farmer.getYearOfBirth().equals(new Short("0"))) {
                                                    farmer.setYearOfBirth(Short.valueOf(String.valueOf(farmer.getAge() == null ? null : Calendar.getInstance().get(Calendar.YEAR) - farmer.getAge())));
                                                }
                                            }

                                            if (cellValue != 0) {
                                                if (cellValue > 999) {
                                                    farmer.setAge(Short.valueOf(String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - cellValue)));
                                                    farmer.setYearOfBirth(cellValue.shortValue());
                                                } else {
                                                    farmer.setAge(cellValue.shortValue());
                                                    farmer.setYearOfBirth(Short.valueOf(String.valueOf(farmer.getAge() == null ? null : Calendar.getInstance().get(Calendar.YEAR) - farmer.getAge())));
                                                }
                                            }
                                        } catch (Exception e) {
                                        }
                                        break;
                                    case 4:
                                        try {
                                            account.setAccountNumber(cell.getStringCellValue());
                                        } catch (Exception e) {
                                            Double d = cell.getNumericCellValue();
                                            account.setAccountNumber(d.toString());
                                        }
                                        if (account.getAccountNumber().equals("")) {
                                            account.setAccountNumber("No account");
                                        }
                                        break;
                                    case 5:
                                        try {
                                            account.setSolId(cell.getStringCellValue());
                                        } catch (Exception e) {
                                            Double d = cell.getNumericCellValue();
                                            account.setSolId(d.toString());
                                        }
                                        break;
                                    case 6:
                                        try {
                                            eblBranch.setName(cell.getStringCellValue());
                                        } catch (Exception e) {
                                            Double d = cell.getNumericCellValue();
                                            eblBranch.setName(d.toString());
                                        }
                                        if (eblBranch.getName() == null) {
                                            account.setEblBranch(null);
                                        } else {
                                            account.setEblBranch(eblBranch);
                                        }
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
                                        farmer.setLocation(location);
                                        break;
                                    case 8:
                                        try {
                                            ward.setId(new Double(cell.getNumericCellValue()).shortValue());
                                            location.setWard(ward);
                                            farmer.setLocation(location);
                                        } catch (Exception e) {
                                            ward.setId(null);
                                            farmer.setLocation(location);
                                        }
                                        break;
                                    case 9:
                                        try {
                                            String villageName = cell.getStringCellValue();
                                            if (villageName == null || villageName.trim().length() < 2) {
                                                location.setVillage(null);
                                            } else {
                                                village.setName(villageName);
                                                location.setVillage(village);
                                                farmer.setLocation(location);
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
                        peopleMap.put(farmer, account);

                    }
                }
            } else {
                throw new InvalidArgumentException("invalid_people_upload_file");
            }
            fileInputStream.close();
        } catch (IOException | NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Error {0}", e.getMessage());
        }

        PopulationTimer.recordReadTime(startTime, fileName);

        return peopleMap;
    }

    private static final Logger LOGGER = Logger.getLogger(AgroDealerReader.class.getSimpleName());

}
