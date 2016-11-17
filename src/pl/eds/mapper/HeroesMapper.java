package pl.eds.mapper;

import java.util.List;

import pl.eds.cdm.CDMHeroes;
import pl.eds.model.entities.Heroes;
import pl.eds.model.entities.Items;

public interface HeroesMapper {

    public List<CDMHeroes> getHeroes() throws Exception;
    
    public Heroes getHero(long id) throws Exception;
    
    public void createHero(Heroes hero) throws Exception;

    public void createItem(long heroId, Items item) throws Exception;
    
}
