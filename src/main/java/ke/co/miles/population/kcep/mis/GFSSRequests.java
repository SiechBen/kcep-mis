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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import ke.co.miles.kcep.mis.utilities.CategoryDetails;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;
import ke.co.miles.kcep.mis.utilities.PhenomenonTypeDetails;
import ke.co.miles.population.kcep.mis.readexcel.GFSReader;
import ke.co.miles.population.kcep.mis.readexcel.GFSSCodeDetails;

/**
 *
 * @author siech
 */
public class GFSSRequests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Connection connection = MysqlConnection.getConnection();

        ResultSet result;
        Statement statement;
        int j = 0;

//        int firstRow = Integer.parseInt(JOptionPane.showInputDialog("Kindly enter the row number of the first record."));
//        int lastRow = Integer.parseInt(JOptionPane.showInputDialog("Kindly enter the row number of the last record."));
//        firstRow = 6;
//        lastRow = 137;
        String fileName = "data/GFSS.xlsx";
        List<GFSSCodeDetails> gfssCodes = new GFSReader().retrieveGFFSCodesFromExcel(fileName);
        PhenomenonDetails phenomenon = new PhenomenonDetails();

        //<editor-fold defaultstate="collapsed" desc="Loop excel records">
//        for (int i = firstRow - 1; i < lastRow; i++, j++) {
        for (GFSSCodeDetails gfssCode : gfssCodes) {
            try {
                statement = connection.createStatement();

                //Create the person's contact record
                statement.executeUpdate("INSERT INTO category(name) VALUES('" + gfssCode.getItem() + "')");
                statement.executeUpdate("INSERT INTO phenomenon_type(name) VALUES('" + gfssCode.getCode() + "')");
                result = statement.executeQuery("SELECT id FROM category ORDER BY id DESC LIMIT 1");
                result.next();
                phenomenon.setCategory(new CategoryDetails(result.getInt("id")));
                result = statement.executeQuery("SELECT id FROM phenomenon_type ORDER BY id DESC LIMIT 1");
                result.next();
                phenomenon.setPhenomenonType(new PhenomenonTypeDetails(result.getInt("id")));
                statement.executeUpdate("INSERT INTO phenomenon(category, phenomenon_type) VALUES(" + phenomenon.getCategory().getId() + "," + phenomenon.getPhenomenonType().getId() + ")");
                System.out.println(++j + " GFSS code[" + gfssCode.getCode() + "] populated successfully");

            } catch (SQLException e) {
                System.err.println("An error occurred: " + e);
            }

        }
//</editor-fold>
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        try {
            System.out.format("\nTime taken to populate schema %s was %d seconds%n%n", "kcep_mis", timeTaken / 60);
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
