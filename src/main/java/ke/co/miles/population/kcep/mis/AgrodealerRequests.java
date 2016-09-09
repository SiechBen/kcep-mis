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
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import ke.co.miles.defaults.Default;
import ke.co.miles.kcep.mis.utilities.ContactDetails;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.population.kcep.mis.readexcel.AgroDealerReader;

/**
 *
 * @author siech
 */
public class AgrodealerRequests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Connection connection = MysqlConnection.getConnection();

        ResultSet result;
        Statement statement;
        MessageDigest messageDigest;
        int firstRow, lastRow;
        String password, likelyEmail;

//        firstRow = Integer.parseInt(JOptionPane.showInputDialog("Kindly enter the row number of the first record."));
//        lastRow = Integer.parseInt(JOptionPane.showInputDialog("Kindly enter the row number of the last record."));
//        firstRow = 6;
//        lastRow = 137;
        String fileName = "data/Agro-dealers.xlsx";
        List<PersonDetails> people = new AgroDealerReader().retrievePeopleFromExcel(fileName);
        int j = 0;

        //<editor-fold defaultstate="collapsed" desc="Loop excel records">
        for (PersonDetails person : people) {

            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
                password = Default.generateSHAPassword(messageDigest, person.getNationalId());
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Message digest algorithm not found");
                return;
            }

            likelyEmail = (person.getName() + "@gmail.com").replaceAll(" ", "").toLowerCase();

            ContactDetails contact = new ContactDetails();
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

                CountyDetails county = new CountyDetails();
                SubCountyDetails subCounty = new SubCountyDetails();

                //Determine the person's sub-county, and county
                result = statement.executeQuery("SELECT * FROM ward where id=" + person.getLocation().getWard().getId() + "");
                result.next();
                subCounty.setId(result.getShort("sub_county"));
                result = statement.executeQuery("SELECT * FROM sub_county where id=" + subCounty.getId() + "");
                result.next();
                county.setId(result.getShort("county"));
                subCounty.setCounty(county);
                person.getLocation().setCounty(county);
                person.getLocation().setSubCounty(subCounty);

                //Create the person's location record
                statement.executeUpdate("INSERT INTO location(ward,sub_county,county) VALUES (" + person.getLocation().getWard().getId() + "," + person.getLocation().getSubCounty().getId() + "," + person.getLocation().getCounty().getId() + ")");
                result = statement.executeQuery("SELECT * FROM location ORDER BY id DESC LIMIT 1");
                result.next();
                person.getLocation().setId(result.getInt("id"));

                //Create the person's record
                statement.executeUpdate("INSERT INTO person(name,sex,national_id,date_of_birth,business_name,location,contact) VALUES ('" + person.getName() + "'," + person.getSex().getId() + ",'" + person.getNationalId() + "'," + null + ",'" + person.getBusinessName() + "'," + person.getLocation().getId() + "," + person.getContact().getId() + ")");
                result = statement.executeQuery("SELECT * FROM person ORDER BY id DESC LIMIT 1");
                result.next();
                person.setId(result.getInt("id"));

                //Create the person's user account record
                statement.executeUpdate("INSERT INTO user_account(person, username, password, person_role) VALUES (" + person.getId() + ",'" + person.getContact().getEmail() + "','" + password + "'," + PersonRoleDetail.AGRO_DEALER.getId() + ")");

                System.out.println(person.getName() + " saved successfully");

            } catch (Exception e) {
                System.err.println("An error occurred: " + e);
            }
        }
//</editor-fold>

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
