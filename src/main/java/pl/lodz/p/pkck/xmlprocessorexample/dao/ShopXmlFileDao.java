package pl.lodz.p.pkck.xmlprocessorexample.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.xmlprocessorexample.model.Shop;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ShopXmlFileDao implements Dao<Shop> {

    Logger log = LoggerFactory.getLogger(ShopXmlFileDao.class);

    @Override
    public Shop read(String path) {
        Shop shop = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Shop.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            shop = (Shop) jaxbUnmarshaller.unmarshal(new File("./" + path));
        } catch (JAXBException e) {
            log.error(e.getMessage(), e);
        }
        return shop;
    }

    @Override
    public void write(Shop obj, String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Shop.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, new File("./" + path));
        } catch (JAXBException e) {
            log.error(e.getMessage(), e);
        }
    }

}
