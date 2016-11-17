package pl.eds.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "eds_heroes")
public class Heroes implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3640766027044745419L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "EDS_HEROES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    private long heroId;

    private String name;

    @OneToMany(mappedBy = "hero")
    private List<Items> items;

    public Heroes() {
	super();
    }

    public Heroes(long heroId, String name, List<Items> items) {
	super();
	this.heroId = heroId;
	this.name = name;
	this.items = items;
    }

    public long getHeroId() {
	return heroId;
    }

    public void setHeroId(long heroId) {
	this.heroId = heroId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

}
