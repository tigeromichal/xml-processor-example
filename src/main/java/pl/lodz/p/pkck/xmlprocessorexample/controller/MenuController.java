package pl.lodz.p.pkck.xmlprocessorexample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.xmlprocessorexample.dao.ShopXmlFileDao;
import pl.lodz.p.pkck.xmlprocessorexample.model.Shop;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @FXML
    private TextField headerCityTextField;
    @FXML
    private TextField headerDateTextField;
    @FXML
    private ListView authorsListView;
    @FXML
    private TextField authorNameTextField;
    @FXML
    private TextField authorLastNameTextField;
    @FXML
    private TextField authorIndexNumberTextField;
    @FXML
    private Button addAuthorButton;
    @FXML
    private Button editAuthorButton;
    @FXML
    private Button removeAuthorButton;
    @FXML
    private ListView clientsListView;
    @FXML
    private TextField clientNameTextField;
    @FXML
    private TextField clientLastNameTextField;
    @FXML
    private Button addClientButton;
    @FXML
    private Button editClientButton;
    @FXML
    private Button removeClientButton;
    @FXML
    private ListView categoriesListView;
    @FXML
    private ChoiceBox categoryParentChoiceBox;
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private Button addCategoryButton;
    @FXML
    private Button editCategoryButton;
    @FXML
    private Button removeCategoryButton;
    @FXML
    private ListView productsListView;
    @FXML
    private ChoiceBox productCategoryChoiceBox;
    @FXML
    private TextField productNameTextField;
    @FXML
    private TextField productPriceTextField;
    @FXML
    private TextField productCurrencyTextField;
    @FXML
    private RadioButton productMarkedRadioButton;
    @FXML
    private Button addProductButton;
    @FXML
    private Button editProductButton;
    @FXML
    private Button removeProductButton;
    @FXML
    private ListView ordersListView;
    @FXML
    private ChoiceBox orderClientChoiceBox;
    @FXML
    private ChoiceBox orderProductChoiceBox;
    @FXML
    private TextField orderNumberTextField;
    @FXML
    private TextField orderDateTextField;
    @FXML
    private TextArea orderDescriptionTextArea;
    @FXML
    private Button addOrderButton;
    @FXML
    private Button editOrderButton;
    @FXML
    private Button removeOrderButton;
    @FXML
    private TextField inputXmlFileNameTextField;
    @FXML
    private TextField inputXmlSchemaFileNameTextField;
    @FXML
    private TextField outputXmlFileNameTextField;
    @FXML
    private Button loadXmlButton;
    @FXML
    private Button saveXmlButton;


    private Shop shop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadXmlButton.setOnAction(event -> loadXml());
        saveXmlButton.setOnAction(event -> saveXml());
    }

    private void loadXml() {
        ShopXmlFileDao dao = new ShopXmlFileDao();
        String inputXmlFileName = inputXmlFileNameTextField.getText();
        try {
            shop = dao.read("/src/main/resources/xml/" + inputXmlFileName);
        } catch (Exception e) {

        }
    }

    private void saveXml() {
        ShopXmlFileDao dao = new ShopXmlFileDao();
        String outputXmlFileName = outputXmlFileNameTextField.getText();
        try {
            dao.write(shop, "/src/main/resources/xml/" + outputXmlFileName);
        } catch (Exception e) {

        }
    }

}
