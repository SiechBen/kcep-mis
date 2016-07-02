package ke.co.miles.kcep.mis.utilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;

/**
 *
 * @author siech
 */
public class FarmDetails implements Serializable, Comparable<FarmDetails> {

    public FarmDetails() {
    }

    public FarmDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlotSize() {
        return plotSize;
    }

    public void setPlotSize(String plotSize) {
        this.plotSize = plotSize;
    }

    public LocationDetails getLocation() {
        return location;
    }

    public void setLocation(LocationDetails location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FarmDetails)) {
            return false;
        }
        FarmDetails other = (FarmDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.FarmDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(FarmDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String plotSize;
    private LocationDetails location;

}
