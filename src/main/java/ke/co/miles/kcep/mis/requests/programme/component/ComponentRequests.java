/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.programme.component;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Component;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ComponentDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ComponentRequests extends EntityRequests implements ComponentRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addComponent(ComponentDetails componentDetails) throws MilesException {

        if (componentDetails == null) {
            throw new InvalidArgumentException("error_029_01");
        } else if (componentDetails.getComponent() == null) {
            throw new InvalidArgumentException("error_029_02");
        } else if (componentDetails.getComponent().length() > 200) {
            throw new InvalidArgumentException("error_029_03");
        }

        Component component;
        q = em.createNamedQuery("Component.findByComponent");
        q.setParameter("component", componentDetails.getComponent());
        try {
            component = (Component) q.getSingleResult();
        } catch (Exception e) {
            component = null;
        }
        if (component != null) {
            throw new InvalidArgumentException("error_029_04");
        }

        component = new Component();
        component.setComponent(componentDetails.getComponent());

        try {
            em.persist(component);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return component.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<ComponentDetails> retrieveCounties() throws MilesException {
        List<Component> components = new ArrayList<>();
        q = em.createNamedQuery("Component.findAll");
        try {
            components = q.getResultList();
        } catch (Exception e) {
        }

        return convertCountiesToComponentDetailsList(components);
    }

    @Override
    public ComponentDetails retrieveComponent(int id) throws MilesException {
        Component component;
        q = em.createNamedQuery("Component.findById");
        q.setParameter("id", id);
        try {
            component = (Component) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertComponentToComponentDetails(component);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editComponent(ComponentDetails componentDetails) throws MilesException {

        if (componentDetails == null) {
            throw new InvalidArgumentException("error_029_01");
        } else if (componentDetails.getId() == null) {
            throw new InvalidArgumentException("error_029_05");
        } else if (componentDetails.getComponent() == null) {
            throw new InvalidArgumentException("error_029_02");
        } else if (componentDetails.getComponent().length() > 200) {
            throw new InvalidArgumentException("error_029_03");
        }

        Component component;
        q = em.createNamedQuery("Component.findByComponent");
        q.setParameter("component", componentDetails.getComponent());
        try {
            component = (Component) q.getSingleResult();
        } catch (Exception e) {
            component = null;
        }
        if (component != null) {
            if (component.getId().equals(componentDetails.getId())) {
                throw new InvalidArgumentException("error_029_04");
            }
        }

        component = em.find(Component.class, componentDetails.getId());
        component.setId(componentDetails.getId());
        component.setComponent(componentDetails.getComponent());

        try {
            em.merge(component);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeComponent(int id) throws MilesException {
        Component component = em.find(Component.class, id);
        try {
            em.remove(component);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ComponentDetails convertComponentToComponentDetails(Component component) {

        ComponentDetails componentDetails = new ComponentDetails();
        try {
            componentDetails.setId(component.getId());
        } catch (Exception e) {
        }
        try {
            componentDetails.setComponent(component.getComponent());
        } catch (Exception e) {
        }
        return componentDetails;

    }

    private List<ComponentDetails> convertCountiesToComponentDetailsList(List<Component> components) {

        List<ComponentDetails> componentDetailsList = new ArrayList<>();
        for (Component component : components) {
            componentDetailsList.add(convertComponentToComponentDetails(component));
        }

        return componentDetailsList;

    }

//</editor-fold>
}
