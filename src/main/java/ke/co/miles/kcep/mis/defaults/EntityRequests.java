/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.defaults;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author siech
 */
public class EntityRequests {

    @PersistenceContext(name = "KCEP-MIS-PU")
    private EntityManager em;
    private Query q;

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the q
     */
    public Query getQ() {
        return q;
    }

    /**
     * @param q the q to set
     */
    public void setQ(Query q) {
        this.q = q;
    }
    
}
