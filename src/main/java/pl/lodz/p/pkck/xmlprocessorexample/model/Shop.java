package pl.lodz.p.pkck.xmlprocessorexample.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sklep")
@XmlAccessorType(XmlAccessType.NONE)
public class Shop {

    @XmlElement(name = "nagłówek", required = true)
    private Header header;

    @XmlElementWrapper(name = "klienci", required = true)
    @XmlElement(name = "klient")
    private List<Client> clients;

    @XmlElement(name = "oferta", required = true)
    private Offer offer;

    @XmlElementWrapper(name = "zamówienia", required = true)
    @XmlElement(name = "zamówienie")
    private List<Order> orders;

    public Shop() {
    }

    public Shop(Header header, List<Client> clients, Offer offer, List<Order> orders) {
        this.header = header;
        this.clients = clients;
        this.offer = offer;
        this.orders = orders;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
