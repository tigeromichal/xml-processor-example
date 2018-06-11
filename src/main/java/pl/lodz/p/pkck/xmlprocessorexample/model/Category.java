package pl.lodz.p.pkck.xmlprocessorexample.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "kategoria")
@XmlAccessorType(XmlAccessType.NONE)
public class Category {

    @XmlID
    @XmlAttribute(name = "id", required = true)
    private String id;

    @XmlIDREF
    @XmlAttribute(name = "rodzic", required = false)
    private Category parent;

    @XmlElement(name = "nazwa", required = true)
    private String name;

    public Category() {
    }

    public Category(String id, Category parent, String name) {
        this.id = id;
        this.parent = parent;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
