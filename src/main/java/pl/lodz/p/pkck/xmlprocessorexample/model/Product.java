package pl.lodz.p.pkck.xmlprocessorexample.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "produkt_definicja")
@XmlAccessorType(XmlAccessType.NONE)
public class Product {

    @XmlID
    @XmlAttribute(name = "id", required = true)
    private long id;

    @XmlIDREF
    @XmlAttribute(name = "kategoria", required = true)
    private Category category;

    @XmlElement(name = "nazwa", required = true)
    private String name;

    @XmlElement(name = "cena", required = true)
    private Price price;

    @XmlElement(name = "wyróżniony", required = true, nillable = true)
    private String marked;

    @XmlElement(name = "niewyróżniony", required = true, nillable = true)
    private String unmarked;

    public Product() {
    }

    public Product(long id, Category category, String name, Price price, String marked, String unmarked) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.marked = marked;
        this.unmarked = unmarked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }

    public String getUnmarked() {
        return unmarked;
    }

    public void setUnmarked(String unmarked) {
        this.unmarked = unmarked;
    }
}
