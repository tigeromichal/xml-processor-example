package pl.lodz.p.pkck.xmlprocessorexample.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import pl.lodz.p.pkck.xmlprocessorexample.exception.DaoException;
import pl.lodz.p.pkck.xmlprocessorexample.model.Shop;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class ShopXmlFileDao implements Dao<Shop> {

    Logger log = LoggerFactory.getLogger(ShopXmlFileDao.class);

    private String xmlSchemaPath;

    public ShopXmlFileDao(final String xmlSchemaPath) {
        this.xmlSchemaPath = xmlSchemaPath;
    }

    @Override
    public Shop read(String path) throws DaoException {
        Shop shop = null;
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("./" + xmlSchemaPath));
            JAXBContext jaxbContext = JAXBContext.newInstance(Shop.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setSchema(schema);
            shop = (Shop) jaxbUnmarshaller.unmarshal(new File("./" + path));
        } catch (JAXBException e) {
            log.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        } catch (SAXException e) {
            log.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return shop;
    }

    @Override
    public void write(Shop obj, String path) throws DaoException {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("./" + xmlSchemaPath));
            JAXBContext jaxbContext = JAXBContext.newInstance(Shop.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setSchema(schema);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, new File("./" + path));
        } catch (JAXBException e) {
            log.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        } catch (SAXException e) {
            log.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
    }

}
