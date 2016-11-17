package pl.eds.cdm;

public class CDMHeroes {

    private long id;
    private String name;

    public CDMHeroes() {
	super();
    }

    public CDMHeroes(String name) {
	super();
	this.name = name;
    }
    
    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
