package pl.lodz.p.pkck.xmlprocessorexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.xmlprocessorexample.dao.ShopXmlFileDao;
import pl.lodz.p.pkck.xmlprocessorexample.model.Shop;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    private Shop shop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void loadXml() {
        ShopXmlFileDao dao = new ShopXmlFileDao();
        shop = dao.read("/src/main/resources/xml/zamówienia.xml");
    }

}
