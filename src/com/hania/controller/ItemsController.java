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
import javafx.scene.Parent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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

    @FXML
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
    private ImageView imageView;

    @FXML
    private Spinner<Integer> amountSpinner;

    public ItemsController() {
        warehouse = new WarehouseImpl();
        warehouse.loadItems();
        items = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        fillTable();

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 31, 1);
        amountSpinner.setValueFactory(valueFactory);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Item selectedItem = table.getSelectionModel().getSelectedItem();
                amountSpinner.getValueFactory().setValue(selectedItem.getNumber());
                imageView.setImage(selectedItem.getImage());
            }
        });

        amountSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            Item selectedItem = table.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                int previousValue = selectedItem.getNumber();
                if (newValue != previousValue) {
                    if (newValue > previousValue) {
                        warehouse.addItem(selectedItem.getItemType());
                    } else {
                        warehouse.deleteItem(selectedItem.getItemType());
                    }
                    fillTable();
                }
            }
        });
    }

    private void fillTable() {
        table.getItems().clear();
        Map<ItemType, Integer> warehouseItems = warehouse.getItems();
        warehouseItems.forEach((key, value) -> items.add(new Item(key, value, resourceBundle)));
        table.setItems(items);
    }

    @FXML
    public void onBack() {
        try {
            changeScene();
        } catch (IOException e) {
            LOG.throwing("ItemsController", "onBack", e);
        }
    }

    private void changeScene() throws IOException {
        Parent pane = getFXMLLoader().load();
        Main.getPrimaryStage().getScene().setRoot(pane);
        Main.getPrimaryStage().show();
    }

    private FXMLLoader getFXMLLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(resourceBundle);
        loader.setLocation(getClass().getResource("../view/MainView.fxml"));
        return loader;
    }
}
