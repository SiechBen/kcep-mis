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
public class CategoryDetails implements Serializable, Comparable<CategoryDetails> {

    public CategoryDetails() {
    }

    public CategoryDetails(Integer id) {
        this.id = id;
    }

    public CategoryDetails(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoryDetails)) {
            return false;
        }
        CategoryDetails other = (CategoryDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.CategoryDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(CategoryDetails o) {
        return this.id.compareTo(o.getId());
    }

    /**
     * @return the child
     */
    public CategoryDetails getRelative() {
        return relative;
    }

    /**
     * @param child the child to set
     */
    public void setRelative(CategoryDetails child) {
        this.relative = child;
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private CategoryDetails relative;

}
