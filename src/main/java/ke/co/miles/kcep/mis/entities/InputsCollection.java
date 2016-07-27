/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "inputs_collection", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InputsCollection.findByFarmerId", query = "SELECT i FROM InputsCollection i WHERE i.farmer.id = :farmerId"),
    @NamedQuery(name = "InputsCollection.findAll", query = "SELECT i FROM InputsCollection i"),
    @NamedQuery(name = "InputsCollection.findById", query = "SELECT i FROM InputsCollection i WHERE i.id = :id"),
    @NamedQuery(name = "InputsCollection.findByDateCollected", query = "SELECT i FROM InputsCollection i WHERE i.dateCollected = :dateCollected"),
    @NamedQuery(name = "InputsCollection.findByQuantity", query = "SELECT i FROM InputsCollection i WHERE i.quantity = :quantity")})
public class InputsCollection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_collected")
    @Temporal(TemporalType.DATE)
    private Date dateCollected;
    @Size(max = 45)
    @Column(name = "quantity")
    private String quantity;
    @JoinColumn(name = "input_type", referencedColumnName = "id")
    @ManyToOne
    private InputType inputType;
    @JoinColumn(name = "agro_dealer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person agroDealer;
    @JoinColumn(name = "farmer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person farmer;
    @JoinColumn(name = "static_input", referencedColumnName = "id")
    @ManyToOne
    private StaticInput staticInput;

    public InputsCollection() {
    }

    public InputsCollection(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateCollected() {
        return dateCollected;
    }

    public void setDateCollected(Date dateCollected) {
        this.dateCollected = dateCollected;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    public Person getAgroDealer() {
        return agroDealer;
    }

    public void setAgroDealer(Person agroDealer) {
        this.agroDealer = agroDealer;
    }

    public Person getFarmer() {
        return farmer;
    }

    public void setFarmer(Person farmer) {
        this.farmer = farmer;
    }

    public StaticInput getStaticInput() {
        return staticInput;
    }

    public void setStaticInput(StaticInput staticInput) {
        this.staticInput = staticInput;
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
        if (!(object instanceof InputsCollection)) {
            return false;
        }
        InputsCollection other = (InputsCollection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.InputsCollection[ id=" + id + " ]";
    }
    
}
