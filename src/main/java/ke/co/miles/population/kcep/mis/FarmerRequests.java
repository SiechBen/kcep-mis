/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.population.kcep.mis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import ke.co.miles.defaults.Default;
import ke.co.miles.kcep.mis.utilities.AccountDetails;
import ke.co.miles.kcep.mis.utilities.ContactDetails;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.DivisionalLocationDetails;
import ke.co.miles.kcep.mis.utilities.EblBranchDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.kcep.mis.utilities.VillageDetails;
import ke.co.miles.population.kcep.mis.readexcel.FarmerReader;

/**
 *
 * @author siech
 */
public class FarmerRequests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Connection connection = MysqlConnection.getConnection();

        ResultSet result;
        Statement statement;
        MessageDigest messageDigest;
        String password, likelyEmail;
        int j =0;

//        int firstRow = Integer.parseInt(JOptionPane.showInputDialog("Kindly enter the row number of the first record."));
//        int lastRow = Integer.parseInt(JOptionPane.showInputDialog("Kindly enter the row number of the last record."));
//        firstRow = 6;
//        lastRow = 137;
        String fileName = "data/Farmers.xlsx";
        HashMap<PersonDetails, AccountDetails> peopleMap = new FarmerReader().retrievePeopleFromExcel(fileName);
        ContactDetails contact;
        CountyDetails county;
        EblBranchDetails eblBranch;
        SubCountyDetails subCounty;
        DivisionalLocationDetails divisionalLocation;
        LocationDetails location;

        //<editor-fold defaultstate="collapsed" desc="Loop excel records">
//        for (int i = firstRow - 1; i < lastRow; i++, j++) {
        for (PersonDetails person : peopleMap.keySet()) {
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
                password = Default.generateSHAPassword(messageDigest, person.getNationalId());
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Message digest algorithm not found");
                return;
            }

            likelyEmail = (person.getName() + "@gmail.com").replaceAll(" ", "").toLowerCase();

            contact = new ContactDetails();
            contact.setEmail(Default.generateRandomEmailAddress(likelyEmail));

            person.setContact(contact);

            DateFormat databaseDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
            DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();
            try {
                date = userDateFormat.parse(userDateFormat.format(date));
                date = databaseDateFormat.parse(databaseDateFormat.format(date));
                person.setDateOfBirth(date);
            } catch (Exception e) {
            }

            //Set the start date
            person.setDateOfBirth(date);

            try {
                statement = connection.createStatement();

                //Create the person's contact record
                statement.executeUpdate("INSERT INTO contact(phone,email,postal_address) VALUES ('" + person.getNationalId() + "','" + person.getContact().getEmail() + "',NULL)");
                result = statement.executeQuery("SELECT * FROM contact ORDER BY id DESC LIMIT 1");
                result.next();
                person.getContact().setId(result.getInt("id"));

                county = new CountyDetails();
                subCounty = new SubCountyDetails();

                //Create village record if not exist
                try {
                    result = statement.executeQuery("SELECT * FROM village WHERE name LIKE '%" + person.getLocation().getVillage().getName() + "%' ");
                    result.next();
                    person.getLocation().getVillage().setId(result.getInt("id"));
                } catch (SQLException e) {
                    statement.executeUpdate("INSERT INTO village(name) VALUES('" + person.getLocation().getVillage().getName() + "')");
                    result = statement.executeQuery("SELECT * FROM village ORDER BY id DESC LIMIT 1 ");
                    result.next();
                    person.getLocation().getVillage().setId(result.getInt("id"));
                } catch (NullPointerException ex) {
                    VillageDetails village = null;
                    location = person.getLocation();
                    location.setVillage(village);
                    person.setLocation(location);
                }

                //Create divisional location record if not exist
                if (person.getLocation().getDivisionalLocation() != null && person.getLocation().getDivisionalLocation().getName().trim().length() > 2) {
                    result = statement.executeQuery("SELECT * FROM divisional_location WHERE name LIKE '%" + person.getLocation().getDivisionalLocation().getName() + "%'");
                    try {
                        result.next();
                        divisionalLocation = person.getLocation().getDivisionalLocation();
                        divisionalLocation.setId(result.getShort("id"));
                        person.getLocation().setDivisionalLocation(divisionalLocation);
                    } catch (SQLException e) {
                        statement.executeUpdate("INSERT INTO divisional_location(name) VALUES('" + person.getLocation().getDivisionalLocation().getName() + "')");
                        result = statement.executeQuery("SELECT * FROM divisional_location ORDER BY id DESC LIMIT 1 ");
                        result.next();
                        divisionalLocation = person.getLocation().getDivisionalLocation();
                        divisionalLocation.setId(result.getShort("id"));
                        person.getLocation().setDivisionalLocation(divisionalLocation);
                    }
                } else {
                    divisionalLocation = null;
                    location = person.getLocation();
                    location.setDivisionalLocation(divisionalLocation);
                    person.setLocation(location);
                }

                //Determine the person's sub-county, and county
                try {
                    result = statement.executeQuery("SELECT * FROM ward where id=" + person.getLocation().getWard().getId() + "");
                    result.next();
                    subCounty.setId(result.getShort("sub_county"));
                    result = statement.executeQuery("SELECT * FROM sub_county where id=" + subCounty.getId() + "");
                    result.next();
                    county.setId(result.getShort("county"));
                    subCounty.setCounty(county);
                    person.getLocation().setCounty(county);
                    person.getLocation().setSubCounty(subCounty);
                } catch (Exception e) {
                    person.setLocation(null);
                }

                //Create the person's location record
                if (person.getLocation() != null) {
                    statement.executeUpdate("INSERT INTO location(ward,sub_county,county,village,divisional_location) VALUES (" + person.getLocation().getWard().getId() + "," + person.getLocation().getSubCounty().getId() + "," + person.getLocation().getCounty().getId() + "," + (person.getLocation().getVillage() == null ? null : person.getLocation().getVillage().getId()) + "," + (person.getLocation().getDivisionalLocation() == null ? null : person.getLocation().getDivisionalLocation().getId()) + ")");
                    result = statement.executeQuery("SELECT * FROM location ORDER BY id DESC LIMIT 1");
                    result.next();
                    person.getLocation().setId(result.getInt("id"));
                }

                //Create ebl branch record if not exist
                try {
                    result = statement.executeQuery("SELECT * FROM ebl_branch WHERE name LIKE '%" + peopleMap.get(person).getEblBranch().getName() + "%' ");
                    result.next();
                    eblBranch = peopleMap.get(person).getEblBranch();
                    eblBranch.setId(result.getShort("id"));
                    peopleMap.get(person).setEblBranch(eblBranch);
                } catch (SQLException e) {
                    statement.executeUpdate("INSERT INTO ebl_branch(name) VALUES('" + peopleMap.get(person).getEblBranch().getName() + "')");
                    result = statement.executeQuery("SELECT * FROM ebl_branch ORDER BY id DESC LIMIT 1 ");
                    result.next();
                    eblBranch = peopleMap.get(person).getEblBranch();
                    eblBranch.setId(result.getShort("id"));
                    peopleMap.get(person).setEblBranch(eblBranch);
                }

                //Create the person's record
                statement.executeUpdate("INSERT INTO person(name,sex,national_id,age,location,contact) VALUES ('" + person.getName() + "'," + (person.getSex() == null ? null : person.getSex().getId()) + ",'" + person.getNationalId() + "','" + person.getAge() + "'," + (person.getLocation() == null ? null : person.getLocation().getId()) + "," + person.getContact().getId() + ")");
                result = statement.executeQuery("SELECT * FROM person ORDER BY id DESC LIMIT 1");
                result.next();
                /**
                 * Doing person.setId(result.getInt("id)) alters the of the
                 * person which is a peopleMap key. Doing this renders
                 * peopleMap.get(person) misleading.
                 */
                int personId = result.getInt("id");

                //Create bank account for person
                statement.executeUpdate("INSERT INTO account(account_number,ebl_branch,sol_id,farmer) VALUES('" + peopleMap.get(person).getAccountNumber() + "'," + peopleMap.get(person).getEblBranch().getId() + ",'" + peopleMap.get(person).getSolId() + "'," + personId + ")");

                //Create the person's user account record
                statement.executeUpdate("INSERT INTO user_account(person, username, password, person_role) VALUES (" + personId + ",'" + person.getContact().getEmail() + "','" + password + "'," + PersonRoleDetail.FARMER.getId() + ")");

                System.out.println(++j +" "+person.getName() + " saved successfully");

            } catch (SQLException e) {
                System.err.println("An error occurred: " + e);
            }

        }
//</editor-fold>
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        try {
            System.out.format("Time taken to populate schema %s was %d seconds%n%n", "kcep_mis", timeTaken / 60);
            FileWriter fileWriter = new FileWriter("data/time_taken.txt", true);
            new File("data/time_taken.txt").getParentFile().mkdir();
            try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter))) {
                printWriter.println("Schema %s was successfully populated" + "kcep_mis" + ".");
                printWriter.println("\nIt took  " + timeTaken / 60 + " seconds to complete the population.\n");
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
        } catch (IOException e) {
        }

    }

}
