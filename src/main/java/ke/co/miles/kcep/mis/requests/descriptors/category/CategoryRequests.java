/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.descriptors.category;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Category;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.CategoryDetails;

/**
 *
 * @author siech
 */
@Stateless
public class CategoryRequests extends EntityRequests implements CategoryRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addCategory(CategoryDetails categoryDetails) throws MilesException {

        if (categoryDetails == null) {
            throw new InvalidArgumentException("error_052_01");
        } else if (categoryDetails.getName() == null) {
            throw new InvalidArgumentException("error_052_02");
        } else if (categoryDetails.getName().length() > 400) {
            throw new InvalidArgumentException("error_052_03");
        }

        Category category;
        setQ(em.createNamedQuery("Category.findByName"));
        q.setParameter("name", categoryDetails.getName());
        try {
            category = (Category) q.getSingleResult();
        } catch (Exception e) {
            category = null;
        }
        if (category != null) {
            throw new InvalidArgumentException("error_052_04");
        }

        category = new Category();
        category.setName(categoryDetails.getName());
        if (categoryDetails.getRelative() != null) {
            category.setRelative(em.getReference(Category.class, categoryDetails.getRelative().getId()));
        }

        try {
            em.persist(category);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return category.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<CategoryDetails> retrieveCategories() throws MilesException {
        List<Category> categorys = new ArrayList<>();
        setQ(em.createNamedQuery("Category.findAll"));
        try {
            categorys = q.getResultList();
        } catch (Exception e) {
        }

        return convertCategoriesToCategoryDetailsList(categorys);
    }

    @Override
    public CategoryDetails retrieveCategory(int id) throws MilesException {
        Category category;
        setQ(em.createNamedQuery("Category.findById"));
        q.setParameter("id", id);
        try {
            category = (Category) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertCategoryToCategoryDetails(category);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editCategory(CategoryDetails categoryDetails) throws MilesException {

        if (categoryDetails == null) {
            throw new InvalidArgumentException("error_052_01");
        } else if (categoryDetails.getId() == null) {
            throw new InvalidArgumentException("error_052_05");
        } else if (categoryDetails.getName() == null) {
            throw new InvalidArgumentException("error_052_02");
        } else if (categoryDetails.getName().length() > 400) {
            throw new InvalidArgumentException("error_052_03");
        }

        Category category;
        setQ(em.createNamedQuery("Category.findByName"));
        q.setParameter("name", categoryDetails.getName());
        try {
            category = (Category) q.getSingleResult();
        } catch (Exception e) {
            category = null;
        }
        if (category != null) {
            if (category.getId().equals(categoryDetails.getId())) {
                throw new InvalidArgumentException("error_052_04");
            }
        }

        category = em.find(Category.class, categoryDetails.getId());
        category.setId(categoryDetails.getId());
        category.setName(categoryDetails.getName());

        try {
            em.merge(category);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeCategory(int id) throws MilesException {
        Category category = em.find(Category.class, id);
        try {
            em.remove(category);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public CategoryDetails convertCategoryToCategoryDetails(Category category) {

        CategoryDetails categoryDetails = new CategoryDetails();
        categoryDetails.setName(category.getName());
        try {
            categoryDetails.setId(category.getId());
        } catch (Exception e) {
        }
        try {
            categoryDetails.setRelative(convertCategoryToCategoryDetails(category.getRelative()));
        } catch (Exception e) {
        }
        return categoryDetails;

    }

    private List<CategoryDetails> convertCategoriesToCategoryDetailsList(List<Category> categorys) {

        List<CategoryDetails> categoryDetailsList = new ArrayList<>();
        for (Category category : categorys) {
            categoryDetailsList.add(convertCategoryToCategoryDetails(category));
        }

        return categoryDetailsList;

    }

//</editor-fold>
}
