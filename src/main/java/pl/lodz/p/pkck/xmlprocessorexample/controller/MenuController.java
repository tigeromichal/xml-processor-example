package pl.lodz.p.pkck.xmlprocessorexample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.xmlprocessorexample.converter.FopPdfConverter;
import pl.lodz.p.pkck.xmlprocessorexample.dao.ShopXmlFileDao;
import pl.lodz.p.pkck.xmlprocessorexample.model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
    private ListView productsInOrderListView;
    @FXML
    private ChoiceBox orderProductChoiceBox;
    @FXML
    private TextField orderNumberTextField;
    @FXML
    private Button addProductToOrderButton;
    @FXML
    private Button editProductNumberButton;
    @FXML
    private Button removeProductFromOrderButton;
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
    @FXML
    private TextField inputXmlFileNameTextField2;
    @FXML
    private TextField xsltFileNameTextField;
    @FXML
    private TextField outputPdfFileNameTextField;
    @FXML
    private Button savePdfButton;
    @FXML
    private Label errorsLabel;

    private Shop shop = new Shop();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAuthorsListView();
        initializeClientsListView();
        initializeCategoriesListView();
        initializeProductsListView();
        initializeOrdersListView();
        initializeProductsInOrderListView();

        loadXmlButton.setOnAction(event -> {
            loadXml();
            populateGuiWithShopData();
        });
        saveXmlButton.setOnAction(event -> {
            updateShopWithGuiData();
            saveXml();
        });
        savePdfButton.setOnAction(event -> savePdf());

        addAuthorButton.setOnAction(event -> addAuthor());
        editAuthorButton.setOnAction(event -> editAuthor());
        removeAuthorButton.setOnAction(event -> removeAuthor());
        addClientButton.setOnAction(event -> addClient());
        editClientButton.setOnAction(event -> editClient());
        removeClientButton.setOnAction(event -> removeClient());
        addCategoryButton.setOnAction(event -> addCategory());
        editCategoryButton.setOnAction(event -> editCategory());
        removeCategoryButton.setOnAction(event -> removeCategory());
        addProductButton.setOnAction(event -> addProduct());
        editProductButton.setOnAction(event -> editProduct());
        removeProductButton.setOnAction(event -> removeProduct());
        addOrderButton.setOnAction(event -> addOrder());
        editOrderButton.setOnAction(event -> editOrder());
        removeOrderButton.setOnAction(event -> removeOrder());
        addProductToOrderButton.setOnAction(event -> addProductToOrder());
        editProductNumberButton.setOnAction(event -> editProductNumber());
        removeProductFromOrderButton.setOnAction(event -> removeProductFromOrder());

        errorsLabel.setWrapText(true);

        //loadXmlButton.fire();
    }

    private void loadXml() {
        String inputXmlSchemaFileName = inputXmlSchemaFileNameTextField.getText();
        ShopXmlFileDao dao = new ShopXmlFileDao("/src/main/resources/xml/" + inputXmlSchemaFileName);
        String inputXmlFileName = inputXmlFileNameTextField.getText();
        try {
            shop = dao.read("/src/main/resources/xml/" + inputXmlFileName);
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void saveXml() {
        String inputXmlSchemaFileName = inputXmlSchemaFileNameTextField.getText();
        ShopXmlFileDao dao = new ShopXmlFileDao("/src/main/resources/xml/" + inputXmlSchemaFileName);
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

        updateAuthorsListViews();
        updateClientsListViews();
        updateCategoriesListViews();
        updateProductsListViews();
        updateOrdersListViews();
    }

    private void updateAuthorsListViews() {
        authorsListView.setItems(FXCollections.observableArrayList(shop.getHeader().getAuthors()));
    }

    private void updateClientsListViews() {
        ObservableList<Client> clientsStringObservableList = FXCollections.observableArrayList(shop.getClients());
        clientsListView.setItems(clientsStringObservableList);
        orderClientChoiceBox.setItems(clientsStringObservableList);
    }

    private void updateCategoriesListViews() {
        ObservableList<Category> categoriesStringObservableList = FXCollections.observableArrayList(shop.getOffer().getCategories());
        categoriesListView.setItems(categoriesStringObservableList);
        categoryParentChoiceBox.setItems(categoriesStringObservableList);
        productCategoryChoiceBox.setItems(categoriesStringObservableList);
    }

    private void updateProductsListViews() {
        ObservableList<Product> productsStringObservableList = FXCollections.observableArrayList(shop.getOffer().getProducts());
        productsListView.setItems(productsStringObservableList);
        orderProductChoiceBox.setItems(productsStringObservableList);
    }

    private void updateOrdersListViews() {
        ordersListView.setItems(FXCollections.observableArrayList(shop.getOrders()));
    }

    private void updateProductsInOrderListViews() {
        if (ordersListView.getSelectionModel().getSelectedIndex() >= 0) {
            ObservableList<ProductOrder> productsInOrderObservableList = FXCollections.observableArrayList(
                    ((Order) (ordersListView.getSelectionModel().getSelectedItem())).getProducts());
            productsInOrderListView.setItems(productsInOrderObservableList);
        } else {
            productsInOrderListView.getItems().clear();
        }
    }

    private void updateShopWithGuiData() {
        shop.getHeader().setCity(headerCityTextField.getText());
        shop.getHeader().setDate(LocalDate.parse(headerDateTextField.getText()));
    }

    private void initializeAuthorsListView() {
        authorsListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                Author selectedAuthor = (Author) authorsListView.getSelectionModel().getSelectedItem();
                authorNameTextField.setText(selectedAuthor.getName());
                authorLastNameTextField.setText(selectedAuthor.getLastName());
                authorIndexNumberTextField.setText(selectedAuthor.getIndexNumber());
            } else {
                authorNameTextField.clear();
                authorLastNameTextField.clear();
                authorIndexNumberTextField.clear();
            }
        });
    }

    private void initializeClientsListView() {
        clientsListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                Client selectedClient = (Client) clientsListView.getSelectionModel().getSelectedItem();
                clientNameTextField.setText(selectedClient.getName());
                clientLastNameTextField.setText(selectedClient.getLastName());
            } else {
                clientNameTextField.clear();
                clientLastNameTextField.clear();
            }
        });
    }

    private void initializeCategoriesListView() {
        categoriesListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                Category selectedCategory = (Category) categoriesListView.getSelectionModel().getSelectedItem();
                categoryNameTextField.setText(selectedCategory.getName());
                if (selectedCategory.getParent() != null) {
                    categoryParentChoiceBox.getSelectionModel().select(selectedCategory.getParent());
                } else {
                    categoryParentChoiceBox.getSelectionModel().clearSelection();
                }
            } else {
                categoryNameTextField.clear();
                categoryParentChoiceBox.getSelectionModel().clearSelection();
            }
        });
    }

    private void initializeProductsListView() {
        productsListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                Product selectedProduct = (Product) productsListView.getSelectionModel().getSelectedItem();
                productNameTextField.setText(selectedProduct.getName());
                productCategoryChoiceBox.getSelectionModel().select(selectedProduct.getCategory());
                productPriceTextField.setText(Double.toString(selectedProduct.getPrice().getValue()));
                productCurrencyTextField.setText(selectedProduct.getPrice().getCurrency());
                productMarkedRadioButton.selectedProperty().setValue(selectedProduct.getMarked() != null);
            } else {
                productNameTextField.clear();
                productCategoryChoiceBox.getSelectionModel().clearSelection();
                productPriceTextField.clear();
                productCurrencyTextField.clear();
                productMarkedRadioButton.setSelected(false);
            }
        });
    }

    private void initializeOrdersListView() {
        ordersListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                Order selectedOrder = (Order) ordersListView.getSelectionModel().getSelectedItem();
                orderClientChoiceBox.getSelectionModel().select(selectedOrder.getClient());
                orderDateTextField.setText(selectedOrder.getDate().toString());
                orderDescriptionTextArea.setText(selectedOrder.getDescription());
            } else {
                orderClientChoiceBox.getSelectionModel().clearSelection();
                orderDateTextField.clear();
                orderDescriptionTextArea.clear();
            }
            updateProductsInOrderListViews();
        });
    }

    private void initializeProductsInOrderListView() {
        productsInOrderListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) {
                ProductOrder selectedProductOrder = (ProductOrder) productsInOrderListView.getSelectionModel().getSelectedItem();
                orderProductChoiceBox.getSelectionModel().select(selectedProductOrder.getProduct());
                orderNumberTextField.setText(Integer.toString(selectedProductOrder.getNumber()));
            } else {
                orderClientChoiceBox.getSelectionModel().clearSelection();
                orderDateTextField.clear();
                orderDescriptionTextArea.clear();
            }
            updateProductsInOrderListViews();
        });
    }

    private void addAuthor() {
        Author newAuthor = new Author();
        newAuthor.setName(authorNameTextField.getText());
        newAuthor.setLastName(authorLastNameTextField.getText());
        newAuthor.setIndexNumber(authorIndexNumberTextField.getText());
        shop.getHeader().getAuthors().add(newAuthor);
        updateAuthorsListViews();
    }

    private void editAuthor() {
        Author selectedAuthor = (Author) authorsListView.getSelectionModel().getSelectedItem();
        selectedAuthor.setName(authorNameTextField.getText());
        selectedAuthor.setLastName(authorLastNameTextField.getText());
        selectedAuthor.setIndexNumber(authorIndexNumberTextField.getText());
        updateAuthorsListViews();
    }

    private void removeAuthor() {
        Author selectedAuthor = (Author) authorsListView.getSelectionModel().getSelectedItem();
        shop.getHeader().getAuthors().remove(selectedAuthor);
        updateAuthorsListViews();
    }

    private void addClient() {
        Client newClient = new Client();
        String maxIdString = shop.getClients().stream().map(Client::getId).max(String.CASE_INSENSITIVE_ORDER).orElse(null);
        String newId = "K" + String.format("%03d", Integer.valueOf(maxIdString.substring(1)) + 1);
        newClient.setId(newId);
        newClient.setName(clientNameTextField.getText());
        newClient.setLastName(clientLastNameTextField.getText());
        shop.getClients().add(newClient);
        updateClientsListViews();
    }

    private void editClient() {
        Client selectedClient = (Client) clientsListView.getSelectionModel().getSelectedItem();
        selectedClient.setName(clientNameTextField.getText());
        selectedClient.setLastName(clientLastNameTextField.getText());
        updateClientsListViews();
    }

    private void removeClient() {
        Client selectedClient = (Client) clientsListView.getSelectionModel().getSelectedItem();
        shop.getClients().remove(selectedClient);
        updateClientsListViews();
    }

    private void addCategory() {
        Category newCategory = new Category();
        String maxIdString = shop.getOffer().getCategories().stream().map(Category::getId).max(String.CASE_INSENSITIVE_ORDER).orElse(null);
        String newId = "KAT" + String.format("%03d", Integer.valueOf(maxIdString.substring(3)) + 1);
        newCategory.setId(newId);
        newCategory.setName(categoryNameTextField.getText());
        if (categoryParentChoiceBox.getSelectionModel().getSelectedIndex() >= 0) {
            newCategory.setParent(shop.getOffer().getCategories().get(categoryParentChoiceBox.getSelectionModel().getSelectedIndex()));
        }
        shop.getOffer().getCategories().add(newCategory);
        updateCategoriesListViews();
    }

    private void editCategory() {
        Category selectedCategory = (Category) categoriesListView.getSelectionModel().getSelectedItem();
        selectedCategory.setName(categoryNameTextField.getText());
        if (categoryParentChoiceBox.getSelectionModel().getSelectedIndex() >= 0) {
            selectedCategory.setParent(shop.getOffer().getCategories().get(categoryParentChoiceBox.getSelectionModel().getSelectedIndex()));
        }
        updateCategoriesListViews();
    }

    private void removeCategory() {
        Category selectedCategory = (Category) categoriesListView.getSelectionModel().getSelectedItem();
        shop.getOffer().getCategories().remove(selectedCategory);
        updateCategoriesListViews();
    }

    private void addProduct() {
        Product newProduct = new Product();
        String maxIdString = shop.getOffer().getProducts().stream().map(Product::getId).max(String.CASE_INSENSITIVE_ORDER).orElse(null);
        String newId = "P" + String.format("%03d", Integer.valueOf(maxIdString.substring(1)) + 1);
        newProduct.setId(newId);
        newProduct.setName(productNameTextField.getText());
        newProduct.setCategory(shop.getOffer().getCategories().get(productCategoryChoiceBox.getSelectionModel().getSelectedIndex()));
        newProduct.setPrice(new Price(productCurrencyTextField.getText(), Double.valueOf(productPriceTextField.getText())));
        if (productMarkedRadioButton.isSelected()) {
            newProduct.setMarked("");
        } else {
            newProduct.setUnmarked("");
        }
        shop.getOffer().getProducts().add(newProduct);
        updateProductsListViews();
    }

    private void editProduct() {
        Product selectedProduct = (Product) productsListView.getSelectionModel().getSelectedItem();
        selectedProduct.setName(productNameTextField.getText());
        selectedProduct.setCategory(shop.getOffer().getCategories().get(productCategoryChoiceBox.getSelectionModel().getSelectedIndex()));
        selectedProduct.setPrice(new Price(productCurrencyTextField.getText(), Double.valueOf(productPriceTextField.getText())));
        if (productMarkedRadioButton.isSelected()) {
            selectedProduct.setMarked("");
        } else {
            selectedProduct.setUnmarked("");
        }
        updateProductsListViews();
    }

    private void removeProduct() {
        Product selectedProduct = (Product) productsListView.getSelectionModel().getSelectedItem();
        shop.getOffer().getProducts().remove(selectedProduct);
        updateProductsListViews();
    }

    private void addOrder() {
        Order newOrder = new Order();
        newOrder.setClient(shop.getClients().get(orderClientChoiceBox.getSelectionModel().getSelectedIndex()));
        newOrder.setDate(LocalDate.parse(orderDateTextField.getText()));
        newOrder.setDescription(orderDescriptionTextArea.getText());
        shop.getOrders().add(newOrder);
        updateOrdersListViews();
    }

    private void editOrder() {
        Order selectedOrder = (Order) ordersListView.getSelectionModel().getSelectedItem();
        selectedOrder.setClient(shop.getClients().get(orderClientChoiceBox.getSelectionModel().getSelectedIndex()));
        selectedOrder.setDate(LocalDate.parse(orderDateTextField.getText()));
        selectedOrder.setDescription(orderDescriptionTextArea.getText());
        updateOrdersListViews();
    }

    private void removeOrder() {
        Order selectedOrder = (Order) ordersListView.getSelectionModel().getSelectedItem();
        shop.getOrders().remove(selectedOrder);
        updateOrdersListViews();
    }

    private void addProductToOrder() {
        ProductOrder newProductOrder = new ProductOrder();
        newProductOrder.setNumber(Integer.valueOf(orderNumberTextField.getText()));
        newProductOrder.setProduct(shop.getOffer().getProducts().get(orderProductChoiceBox.getSelectionModel().getSelectedIndex()));
        Order selectedOrder = shop.getOrders().get(ordersListView.getSelectionModel().getSelectedIndex());
        selectedOrder.getProducts().add(newProductOrder);
        updateProductsInOrderListViews();
    }

    private void editProductNumber() {
        Order selectedOrder = (Order) ordersListView.getSelectionModel().getSelectedItem();
        ProductOrder selectedProductOrder = selectedOrder.getProducts().get(productsInOrderListView.getSelectionModel().getSelectedIndex());
        selectedProductOrder.setNumber(Integer.valueOf(orderNumberTextField.getText()));
        updateProductsInOrderListViews();
    }

    private void removeProductFromOrder() {
        Order selectedOrder = (Order) ordersListView.getSelectionModel().getSelectedItem();
        ProductOrder selectedProductOrder = selectedOrder.getProducts().get(productsInOrderListView.getSelectionModel().getSelectedIndex());
        selectedOrder.getProducts().remove(selectedProductOrder);
        updateProductsInOrderListViews();
    }

    private void savePdf() {
        String inputXmlFilePath = "/src/main/resources/xml/" + inputXmlFileNameTextField2.getText();
        String xsltFilePath = "/src/main/resources/xml/" + xsltFileNameTextField.getText();
        String outputPdfFilePath = "/src/main/resources/xml/" + outputPdfFileNameTextField.getText();
        FopPdfConverter converter = new FopPdfConverter();
        try {
            converter.write(inputXmlFilePath, xsltFilePath, outputPdfFilePath);
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void handleException(Exception e) {
        errorsLabel.setText(e.getMessage());
        log.error(e.getMessage(), e);
    }

}
