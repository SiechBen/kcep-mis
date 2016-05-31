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
    protected EntityManager em;
    protected Query q;
    
}
