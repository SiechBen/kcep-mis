/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;

/**
 *
 * @author siech
 */
public enum UploadedFileTypeDetail implements Serializable {

    FARMERS_EXCEL_FILE(new Integer("169"), "Farmers Excel File"),
    AGRO_DEALERS_EXCEL_FILE(new Integer("170"), "Agro-dealers Excel File");

    private UploadedFileTypeDetail(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static UploadedFileTypeDetail getUploadedFileTypeDetail(int id) {
        switch (id) {
            case 169:
                return FARMERS_EXCEL_FILE;
            case 170:
                return AGRO_DEALERS_EXCEL_FILE;
            default:
                return null;
        }
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    private final Integer id;
    private final String name;
}
