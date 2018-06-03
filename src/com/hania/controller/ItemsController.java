package com.hania.controller;

import com.hania.Main;
import com.hania.model.Item;
import com.hania.process.ItemType;
import com.hania.process.Warehouse;
import com.hania.process.WarehouseImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class ItemsController implements Initializable {

    private static final Logger LOG = Logger.getLogger(ItemsController.class.getName());

    private ResourceBundle resourceBundle;

    private Warehouse warehouse;

    private ObservableList<Item> items;

    private AnchorPane rootPane;

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, Integer> numberColumn;

    @FXML
    private TableColumn<Item, String> nameColumn;

    @FXML
    private TableColumn<Item, String> priceColumn;

    @FXML
    private TableColumn<Item, ImageView> imageColumn;

    @FXML
    private Spinner amountSpinner;

    public ItemsController() {
        warehouse = new WarehouseImpl();
        items = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        fillTable();
    }

    private void fillTable() {
        Map<ItemType, Integer> warehouseItems = warehouse.getItems();
        warehouseItems.forEach((key, value) -> items.add(new Item(key, value, resourceBundle)));
        table.setItems(items);
    }

    @FXML
    public void onBack() {
        FXMLLoader loader = getFXMLLoader();
        try {
            rootPane = loader.load();
        } catch (IOException e) {
            LOG.throwing("ItemsController", "onBack", e);
        }

        Main.setScene(rootPane);
    }

    private FXMLLoader getFXMLLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(resourceBundle);
        loader.setLocation(getClass().getResource("../view/MainView.fxml"));
        return loader;
    }

    @FXML
    public void onRotate() {
        //todo
        table.getSelectionModel().getSelectedCells().get(1);
    }
}
