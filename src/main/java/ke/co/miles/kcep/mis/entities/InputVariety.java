/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "input_variety", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InputVariety.findByStaticInputId", query = "SELECT i FROM InputVariety i WHERE i.staticInput.id =:staticInputId"),
    @NamedQuery(name = "InputVariety.findAll", query = "SELECT i FROM InputVariety i"),
    @NamedQuery(name = "InputVariety.findById", query = "SELECT i FROM InputVariety i WHERE i.id = :id"),
    @NamedQuery(name = "InputVariety.findByVariety", query = "SELECT i FROM InputVariety i WHERE i.variety = :variety")})
public class InputVariety implements Serializable {

    @OneToMany(mappedBy = "inputVariety")
    private List<InputsCollection> inputsCollectionList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "variety")
    private String variety;
    @JoinColumn(name = "static_input", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StaticInput staticInput;

    public InputVariety() {
    }

    public InputVariety(Integer id) {
        this.id = id;
    }

    public InputVariety(Integer id, String variety) {
        this.id = id;
        this.variety = variety;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
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
        if (!(object instanceof InputVariety)) {
            return false;
        }
        InputVariety other = (InputVariety) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.InputVariety[ id=" + id + " ]";
    }

    @XmlTransient
    public List<InputsCollection> getInputsCollectionList() {
        return inputsCollectionList;
    }

    public void setInputsCollectionList(List<InputsCollection> inputsCollectionList) {
        this.inputsCollectionList = inputsCollectionList;
    }
    
}
