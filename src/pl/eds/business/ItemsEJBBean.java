package pl.eds.business;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import pl.eds.mapper.ItemsMapper;
import pl.eds.model.entities.Items;

@Local
@Stateless
public class ItemsEJBBean implements ItemsMapper {

    @SuppressWarnings("unused")
    private static Logger log = Logger.getLogger(ItemsEJBBean.class);

    @PersistenceContext(unitName = "EJB_DATA_SERVER")
    private EntityManager em;
    
    @Override
    public Items getItem(long itemId) throws Exception {
	Items item = em.find(Items.class, itemId);
	if (item == null) {
	    throw new NoResultException();
	}
	return item;
    }

    @Override
    public void createItem(Items item) throws Exception {
	em.persist(item);
    }

}
