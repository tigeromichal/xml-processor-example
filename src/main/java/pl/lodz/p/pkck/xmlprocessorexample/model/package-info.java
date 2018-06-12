@XmlSchema(
        namespace = "http://www.example.org/typy",
        xmlns = {
                @XmlNs(prefix = "", namespaceURI = "http://www.example.org/typy")
        },
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package pl.lodz.p.pkck.xmlprocessorexample.model;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;