package pl.lodz.p.pkck.xmlprocessorexample.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "klient")
@XmlAccessorType(XmlAccessType.NONE)
public class Client {

    @XmlID
    @XmlAttribute(name = "id", required = true)
    private long id;

    @XmlElement(name = "imię", required = true)
    private String name;

    @XmlElement(name = "nazwisko", required = true)
    private String lastName;

    public Client() {
    }

    public Client(long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
