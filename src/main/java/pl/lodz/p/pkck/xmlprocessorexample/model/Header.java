package pl.lodz.p.pkck.xmlprocessorexample.model;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "nagłówek")
@XmlAccessorType(XmlAccessType.NONE)
public class Header {

    @XmlElement(name = "miejscowość", required = true)
    private String city;

    @XmlElement(name = "data", required = true)
    private LocalDate date;

    @XmlElements(@XmlElement(name = "autor"))
    private List<Author> authors;

    public Header() {
    }

    public Header(String city, LocalDate date, List<Author> authors) {
        this.city = city;
        this.date = date;
        this.authors = authors;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
