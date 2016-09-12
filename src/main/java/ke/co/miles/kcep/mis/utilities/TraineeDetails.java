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
public class TraineeDetails implements Serializable, Comparable<TraineeDetails> {

    public TraineeDetails() {
    }

    public TraineeDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonDetails getPerson() {
        return person;
    }

    public void setPerson(PersonDetails person) {
        this.person = person;
    }

    public TrainingDetails getTraining() {
        return training;
    }

    public void setTraining(TrainingDetails training) {
        this.training = training;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TraineeDetails)) {
            return false;
        }
        TraineeDetails other = (TraineeDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.TraineeDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(TraineeDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private PersonDetails person;
    private TrainingDetails training;

}
