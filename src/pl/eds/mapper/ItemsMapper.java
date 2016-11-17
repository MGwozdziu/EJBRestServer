package pl.eds.mapper;

import pl.eds.model.entities.Items;

public interface ItemsMapper {

    public Items getItem(long itemId) throws Exception;

    public void createItem(Items item) throws Exception;

}
