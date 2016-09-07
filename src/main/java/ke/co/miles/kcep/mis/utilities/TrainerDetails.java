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
public class TrainerDetails implements Serializable, Comparable<TrainerDetails> {

    private static final long serialVersionUID = 1L;

    public TrainerDetails() {
    }

    public TrainerDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PhenomenonDetails getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(PhenomenonDetails person) {
        this.phenomenon = person;
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
        if (!(object instanceof TrainerDetails)) {
            return false;
        }
        TrainerDetails other = (TrainerDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Trainer[ id=" + id + " ]";
    }

    @Override
    public int compareTo(TrainerDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private PhenomenonDetails phenomenon;
    private TrainingDetails training;

}
