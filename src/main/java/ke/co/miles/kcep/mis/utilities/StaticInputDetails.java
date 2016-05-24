/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
public class StaticInputDetails implements Serializable, Comparable<StaticInputDetails> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(length = 45)
    private String name;
    @OneToMany(mappedBy = "staticInputId")
    private List<InputTypeDetails> inputTypeList;

    public StaticInputDetails() {
    }

    public StaticInputDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<InputTypeDetails> getInputTypeList() {
        return inputTypeList;
    }

    public void setInputTypeList(List<InputTypeDetails> inputTypeList) {
        this.inputTypeList = inputTypeList;
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
        if (!(object instanceof StaticInputDetails)) {
            return false;
        }
        StaticInputDetails other = (StaticInputDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.StaticInput[ id=" + id + " ]";
    }

    @Override
    public int compareTo(StaticInputDetails o) {
        return this.id.compareTo(o.getId());
    }
}
