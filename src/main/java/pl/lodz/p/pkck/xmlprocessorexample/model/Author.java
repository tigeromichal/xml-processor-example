package pl.lodz.p.pkck.xmlprocessorexample.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "autor")
@XmlAccessorType(XmlAccessType.NONE)
public class Author {

    @XmlAttribute(name = "imię", required = true)
    private String name;

    @XmlAttribute(name = "nazwisko", required = true)
    private String lastName;

    @XmlAttribute(name = "nr_indeksu", required = true)
    private String indexNumber;

    public Author() {
    }

    public Author(String name, String lastName, String indexNumber) {
        this.name = name;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
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

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    @Override
    public String toString() {
        return name + " " + lastName + " " + indexNumber;
    }

}
