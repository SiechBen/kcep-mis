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
public class UserAccountDetails implements Serializable, Comparable<UserAccountDetails> {

    public UserAccountDetails() {
    }

    public UserAccountDetails(Integer id) {
        this.id = id;
    }

    public UserAccountDetails(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonDetails getPerson() {
        return person;
    }

    public void setPerson(PersonDetails person) {
        this.person = person;
    }

    public PersonRoleDetails getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRoleDetails personRole) {
        this.personRole = personRole;
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
        if (!(object instanceof UserAccountDetails)) {
            return false;
        }
        UserAccountDetails other = (UserAccountDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.UserAccount[ id=" + id + " ]";
    }

    @Override
    public int compareTo(UserAccountDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String username;
    private String password;
    private PersonDetails person;
    private PersonRoleDetails personRole;

}
