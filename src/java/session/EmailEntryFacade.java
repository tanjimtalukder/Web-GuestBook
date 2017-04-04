/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EmailEntry;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author TanjimTalukder
 */
@Stateless
public class EmailEntryFacade extends AbstractFacade<EmailEntry> {
    @PersistenceContext(unitName = "tTalukderA1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmailEntryFacade() {
        super(EmailEntry.class);
    }
    //add additional business logic
    public void persistEmailEntryData(int id, String firstname, String lastname,String emailaddress) {
        try {
            EmailEntry g = new EmailEntry();
            g.setId(id);
            g.setFirstName(firstname);
            g.setLastName(lastname);
            g.setEmailAddress(emailaddress);
            em.persist(g);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public List<EmailEntry> findByLastName(String name) {
        // Query q = em.createNamedQuery("Guestbook.findByLastname");
        Query q = em.createQuery("SELECT g FROM EmailEntry g WHERE g.lastName = :lastName");
        q.setParameter("lastName", name);
        List<EmailEntry> guestList = q.getResultList();
        return guestList;
    }
    
    public List<EmailEntry> showAllRecords() {
        Query q=em.createNamedQuery("EmailEntry.findAll");
        List<EmailEntry> guestList = q.getResultList();
        return guestList;        
    }    
    public void editRecord(EmailEntry entity) throws Exception {
        try {
            edit(entity);
        }
        catch (Exception e) {
            throw new Exception("Edit Transaction Failed");
        }
    }    
    public List<EmailEntry> findById(int id) {
        Query q=em.createNamedQuery("EmailEntry.findById");
        q.setParameter("id", id);
        List<EmailEntry> guestList = q.getResultList();
        return guestList;
    }    
    public void deleteById(EmailEntry entity) throws Exception {
        try {
            remove(entity);
        }
        catch(Exception e) {
            throw new Exception("Delete Transaction Failed");
        }
    }    
    public int getNumberOfRecords() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<EmailEntry> rt = cq.from(EmailEntry.class);
        cq.select(cb.count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
