/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author siech
 */
public class LocationDetails implements Serializable, Comparable<LocationDetails> {

    private static final long serialVersionUID = 1L;

    public LocationDetails() {
    }

    public LocationDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CountyDetails getCounty() {
        return county;
    }

    public void setCounty(CountyDetails county) {
        this.county = county;
    }

    public SubCountyDetails getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(SubCountyDetails subCounty) {
        this.subCounty = subCounty;
    }

    public WardDetails getWard() {
        return ward;
    }

    public void setWard(WardDetails ward) {
        this.ward = ward;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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
        if (!(object instanceof LocationDetails)) {
            return false;
        }
        LocationDetails other = (LocationDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.Location[ id=" + id + " ]";
    }

    @Override
    public int compareTo(LocationDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the divisionalLocation
     */
    public DivisionalLocationDetails getDivisionalLocation() {
        return divisionalLocation;
    }

    /**
     * @param divisionalLocation the divisionalLocation to set
     */
    public void setDivisionalLocation(DivisionalLocationDetails divisionalLocation) {
        this.divisionalLocation = divisionalLocation;
    }

    /**
     * @return the village
     */
    public VillageDetails getVillage() {
        return village;
    }

    /**
     * @param village the village to set
     */
    public void setVillage(VillageDetails village) {
        this.village = village;
    }

    private Integer id;
    private CountyDetails county;
    private SubCountyDetails subCounty;
    private WardDetails ward;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private VillageDetails village;
    private DivisionalLocationDetails divisionalLocation;

}
