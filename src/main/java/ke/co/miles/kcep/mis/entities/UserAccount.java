package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "user_account", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAccount.searchPersonByNameOrNationalId", query = "SELECT u FROM UserAccount u WHERE u.personRole.id = :personRoleId AND (u.person.nationalId LIKE :nationalId OR u.person.name LIKE :name)"),
    @NamedQuery(name = "UserAccount.findByPersonRoleIdAndCountyId", query = "SELECT u FROM UserAccount u WHERE u.personRole.id = :personRoleId AND u.person.location.county.id = :countyId"),
    @NamedQuery(name = "UserAccount.searchPersonByNationalId", query = "SELECT u FROM UserAccount u WHERE u.personRole.id = :personRoleId AND u.person.nationalId LIKE :nationalId"),
    @NamedQuery(name = "UserAccount.searchPersonByName", query = "SELECT u FROM UserAccount u WHERE u.personRole.id = :personRoleId AND u.person.name LIKE :name"),
    @NamedQuery(name = "UserAccount.findSubCountyFarmers", query = "SELECT u FROM UserAccount u WHERE u.person.location.subCounty.id = :subCountyId AND u.personRole.id = :personRoleId"),
    @NamedQuery(name = "UserAccount.findBySubCountyId", query = "SELECT u FROM UserAccount u WHERE u.person.location.subCounty.id = :subCountyId"),
    @NamedQuery(name = "UserAccount.findByCountyId", query = "SELECT u FROM UserAccount u WHERE u.person.location.county.id = :countyId"),
    @NamedQuery(name = "UserAccount.findByWardId", query = "SELECT u FROM UserAccount u WHERE u.person.location.ward.id = :wardId"),
    @NamedQuery(name = "UserAccount.findByPersonId", query = "SELECT u FROM UserAccount u WHERE u.person.id = :personId"),
    @NamedQuery(name = "UserAccount.findByPersonRoleId", query = "SELECT u FROM UserAccount u WHERE u.personRole.id = :personRoleId"),
    @NamedQuery(name = "UserAccount.findByUsernameAndPassword", query = "SELECT u FROM UserAccount u WHERE u.username = :username AND u.password = :password"),
    @NamedQuery(name = "UserAccount.findBySexAndPersonRoleId", query = "SELECT u FROM UserAccount u WHERE u.person.sex.id = :sexId AND u.personRole.id = :personRoleId"),
    @NamedQuery(name = "UserAccount.findByPersonRoleIds", query = "SELECT u FROM UserAccount u WHERE u.personRole.id IN (:personRoleIds)"),
    @NamedQuery(name = "UserAccount.findCountyPeopleNotHavingPersonRoleIds", query = "SELECT u FROM UserAccount u WHERE u.person.location.county.id = :countyId AND u.personRole.id NOT IN (:personRoleIds)"),
    @NamedQuery(name = "UserAccount.findNotHavingPersonRoleIds", query = "SELECT u FROM UserAccount u WHERE u.personRole.id NOT IN (:personRoleIds)"),
    @NamedQuery(name = "UserAccount.findAll", query = "SELECT u FROM UserAccount u"),
    @NamedQuery(name = "UserAccount.findById", query = "SELECT u FROM UserAccount u WHERE u.id = :id"),
    @NamedQuery(name = "UserAccount.findByUsername", query = "SELECT u FROM UserAccount u WHERE u.username = :username"),
    @NamedQuery(name = "UserAccount.findByPassword", query = "SELECT u FROM UserAccount u WHERE u.password = :password")})
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "person", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Person person;
    @JoinColumn(name = "person_role", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PersonRole personRole;

    public UserAccount() {
    }

    public UserAccount(Integer id) {
        this.id = id;
    }

    public UserAccount(Integer id, String username, String password) {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonRole getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRole personRole) {
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
        if (!(object instanceof UserAccount)) {
            return false;
        }
        UserAccount other = (UserAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.UserAccount[ id=" + id + " ]";
    }

}
