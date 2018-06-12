package pl.lodz.p.pkck.xmlprocessorexample.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.xmlprocessorexample.dao.ShopXmlFileDao;
import pl.lodz.p.pkck.xmlprocessorexample.model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MenuController extends Controller {

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

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


    private Shop shop = new Shop();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAuthorsListView();
        initializeClientsListView();
        initializeCategoriesListView();
        initializeProductsListView();
        initializeOrdersListView();

        loadXmlButton.setOnAction(event -> {
            loadXml();
            populateGuiWithShopData();
        });
        saveXmlButton.setOnAction(event -> {
            updateShopWithGuiData();
            saveXml();
        });

        loadXmlButton.fire();
    }

    private void loadXml() {
        ShopXmlFileDao dao = new ShopXmlFileDao();
        String inputXmlFileName = inputXmlFileNameTextField.getText();
        try {
            shop = dao.read("/src/main/resources/xml/" + inputXmlFileName);
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void saveXml() {
        ShopXmlFileDao dao = new ShopXmlFileDao();
        String outputXmlFileName = outputXmlFileNameTextField.getText();
        try {
            dao.write(shop, "/src/main/resources/xml/" + outputXmlFileName);
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void populateGuiWithShopData() {
        headerCityTextField.setText(shop.getHeader().getCity());
        headerDateTextField.setText(shop.getHeader().getDate().toString());

        authorsListView.setItems(FXCollections.observableArrayList(shop.getHeader().getAuthors()
                .stream()
                .map(Author::toString)
                .collect(Collectors.toList())));

        clientsListView.setItems(FXCollections.observableArrayList(shop.getClients()
                .stream()
                .map(Client::toString)
                .collect(Collectors.toList())));

        categoriesListView.setItems(FXCollections.observableArrayList(shop.getOffer().getCategories()
                .stream()
                .map(Category::toString)
                .collect(Collectors.toList())));

        categoryParentChoiceBox.setItems(FXCollections.observableArrayList(shop.getOffer().getCategories()
                .stream()
                .map(Category::toString)
                .collect(Collectors.toList())));

        productsListView.setItems(FXCollections.observableArrayList(shop.getOffer().getProducts()
                .stream()
                .map(Product::toString)
                .collect(Collectors.toList())));

        productCategoryChoiceBox.setItems(FXCollections.observableArrayList(shop.getOffer().getCategories()
                .stream()
                .map(Category::toString)
                .collect(Collectors.toList())));

        ordersListView.setItems(FXCollections.observableArrayList(shop.getOrders()
                .stream()
                .map(Order::toString)
                .collect(Collectors.toList())));

        orderClientChoiceBox.setItems(FXCollections.observableArrayList(shop.getClients()
                .stream()
                .map(Client::toString)
                .collect(Collectors.toList())));

    }

    private void updateShopWithGuiData() {
        shop.getHeader().setCity(headerCityTextField.getText());
        shop.getHeader().setDate(LocalDate.parse(headerDateTextField.getText()));
    }

    private void initializeAuthorsListView() {
        authorsListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Author selectedAuthor = shop.getHeader().getAuthors().get(newValue.intValue());
            authorNameTextField.setText(selectedAuthor.getName());
            authorLastNameTextField.setText(selectedAuthor.getLastName());
            authorIndexNumberTextField.setText(selectedAuthor.getIndexNumber());
        });
    }

    private void initializeClientsListView() {
        clientsListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Client selectedClient = shop.getClients().get(newValue.intValue());
            clientNameTextField.setText(selectedClient.getName());
            clientLastNameTextField.setText(selectedClient.getLastName());
        });
    }

    private void initializeCategoriesListView() {
        categoriesListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Category selectedCategory = shop.getOffer().getCategories().get(newValue.intValue());
            categoryNameTextField.setText(selectedCategory.getName());
            if (selectedCategory.getParent() != null) {
                int parentIndex = IntStream.range(0, shop.getOffer().getCategories().size())
                        .filter(i -> shop.getOffer().getCategories().get(i).getId().equals(selectedCategory.getParent().getId()))
                        .findFirst().getAsInt();
                categoryParentChoiceBox.getSelectionModel().select(parentIndex);
            } else {
                categoryParentChoiceBox.getSelectionModel().clearSelection();
            }
        });
    }

    private void initializeProductsListView() {
        productsListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Product selectedProduct = shop.getOffer().getProducts().get(newValue.intValue());
            productNameTextField.setText(selectedProduct.getName());
            int categoryIndex = IntStream.range(0, shop.getOffer().getCategories().size())
                    .filter(i -> shop.getOffer().getCategories().get(i).getId().equals(selectedProduct.getCategory().getId()))
                    .findFirst().getAsInt();
            productCategoryChoiceBox.getSelectionModel().select(categoryIndex);
            productPriceTextField.setText(Double.toString(selectedProduct.getPrice().getValue()));
            productCurrencyTextField.setText(selectedProduct.getPrice().getCurrency());
            productMarkedRadioButton.selectedProperty().setValue(selectedProduct.getMarked() != null);
        });
    }

    private void initializeOrdersListView() {
        ordersListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Order selectedOrder = shop.getOrders().get(newValue.intValue());
            int clientIndex = IntStream.range(0, shop.getClients().size())
                    .filter(i -> shop.getClients().get(i).getId().equals(selectedOrder.getClient().getId()))
                    .findFirst().getAsInt();
            orderClientChoiceBox.getSelectionModel().select(clientIndex);
            orderDateTextField.setText(selectedOrder.getDate().toString());
            orderDescriptionTextArea.setText(selectedOrder.getDescription());
        });
    }

    private void handleException(Exception e) {
        log.error(e.getMessage(), e);
    }

}
