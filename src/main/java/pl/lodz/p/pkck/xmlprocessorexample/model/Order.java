package pl.lodz.p.pkck.xmlprocessorexample.model;

import pl.lodz.p.pkck.xmlprocessorexample.utils.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement(name = "zamówienie")
@XmlAccessorType(XmlAccessType.NONE)
public class Order {

    @XmlIDREF
    @XmlAttribute(name = "klient", required = true)
    private Client client;

    @XmlElements(@XmlElement(name = "produkt"))
    private ArrayList<ProductOrder> products;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "data", required = true)
    private LocalDate date;

    @XmlElement(name = "opis", required = true)
    private String description;

    public Order() {
    }

    public Order(ArrayList<ProductOrder> products, LocalDate date, String description) {
        this.products = products;
        this.date = date;
        this.description = description;
    }

    public ArrayList<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductOrder> products) {
        this.products = products;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
