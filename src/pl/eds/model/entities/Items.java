package pl.eds.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "eds_items")
public class Items {

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "EDS_ITEMS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    private long itemId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "heroes_heroId")
    private Heroes hero;

    public Items() {
	super();
    }

    public Items(long itemId, String name, Heroes hero) {
	super();
	this.itemId = itemId;
	this.name = name;
	this.hero = hero;
    }

    public long getItemId() {
	return itemId;
    }

    public void setItemId(long itemId) {
	this.itemId = itemId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Heroes getHero() {
        return hero;
    }

    public void setHero(Heroes hero) {
        this.hero = hero;
    }

}
