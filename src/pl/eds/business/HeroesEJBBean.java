package pl.eds.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import pl.eds.cdm.CDMHeroes;
import pl.eds.mapper.HeroesMapper;
import pl.eds.mapper.ItemsMapper;
import pl.eds.model.entities.Heroes;
import pl.eds.model.entities.Items;
import pl.eds.utils.CollectionsUtil;

@Local
@Stateless
public class HeroesEJBBean implements HeroesMapper {

    @SuppressWarnings("unused")
    private static Logger log = Logger.getLogger(HeroesEJBBean.class);

    @PersistenceContext(unitName = "EJB_DATA_SERVER")
    private EntityManager em;
    
    @EJB
    private ItemsMapper itemsMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<CDMHeroes> getHeroes() throws Exception {
	Query req = em.createQuery("SELECT h FROM Heroes h");
	if (!CollectionsUtil.isNullOrEmpty(req.getResultList())) {
	    List<CDMHeroes> cdmHeroList = new ArrayList<CDMHeroes>();
	    for (Heroes hero: (List<Heroes>) req.getResultList()) {
		CDMHeroes cdmHero = new CDMHeroes();
		
		cdmHero.setId(hero.getHeroId());
		cdmHero.setName(hero.getName());
		
		cdmHeroList.add(cdmHero);
	    }
	    return cdmHeroList;
	} else {
	    throw new NoResultException();
	}
    }
    
    @Override
    public Heroes getHero(long heroId) throws Exception {
	Heroes hero = em.find(Heroes.class, heroId);
	if (hero == null) {
	    throw new NoResultException();
	}
	return hero;
    }

    @Override
    public void createHero(Heroes hero) throws Exception {
	em.persist(hero);
    }

    @Override
    public void createItem(long heroId, Items item) throws Exception {
	item.setHero(getHero(heroId));
	itemsMapper.createItem(item);
    }

}
