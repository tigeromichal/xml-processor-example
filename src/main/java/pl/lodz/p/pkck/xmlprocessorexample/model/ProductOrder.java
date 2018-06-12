package pl.lodz.p.pkck.xmlprocessorexample.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "produkt")
@XmlAccessorType(XmlAccessType.NONE)
public class ProductOrder {

    @XmlIDREF
    @XmlAttribute(name = "id", required = true)
    private Product product;

    @XmlAttribute(name = "liczba", required = true)
    private int number;

    public ProductOrder() {
    }

    public ProductOrder(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return product.getName() + " [" + Integer.toString(number) + "]";
    }
}
