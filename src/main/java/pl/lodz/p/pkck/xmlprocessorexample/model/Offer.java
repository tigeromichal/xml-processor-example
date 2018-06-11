package pl.lodz.p.pkck.xmlprocessorexample.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "oferta")
@XmlAccessorType(XmlAccessType.NONE)
public class Offer {

    @XmlElementWrapper(name = "kategorie", required = true)
    @XmlElement(name = "kategoria")
    private List<Category> categories;

    @XmlElementWrapper(name = "produkty", required = true)
    @XmlElement(name = "produkt_definicja")
    private List<Product> products;

    public Offer() {
    }

    public Offer(List<Category> categories, List<Product> products) {
        this.categories = categories;
        this.products = products;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
