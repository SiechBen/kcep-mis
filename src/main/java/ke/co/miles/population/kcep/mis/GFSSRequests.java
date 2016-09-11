/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.population.kcep.mis;

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

        PopulationTimer.recordPopulationTime(startTime);
        
    }
}
