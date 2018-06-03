package com.hania.controller;

import com.hania.Main;
import com.hania.model.Item;
import com.hania.process.ItemType;
import com.hania.process.Warehouse;
import com.hania.process.WarehouseImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private List<Item> items;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView table;

    @FXML
    private Spinner amountSpinner;

    public ItemsController() {
        warehouse = new WarehouseImpl();
        items = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        fillTable();
    }

    private void fillTable() {
        Map<ItemType, Integer> warehouseItems = warehouse.getItems();
        warehouseItems.forEach((key, value) -> items.add(new Item(key, value, resourceBundle)));
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
