package com.hania.model;

import com.hania.process.ItemType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class Item {

    private static final String IMAGE_URL_BASE = "resources/items/";

    private ItemType itemType;

    private int number;

    private String name;

    private String price;

    private ImageView image;

    public Item() {
    }

    public Item(ItemType itemType, int number, ResourceBundle resourceBundle) {
        this.itemType = itemType;
        this.number = number;
        String pattern = resourceBundle.getString("item." + itemType.toString().toLowerCase());
        this.name = MessageFormat.format(pattern, number);
        this.price = MessageFormat.format(resourceBundle.getString("item.price"), itemType.getPrice());

        Image img = new Image(new File(IMAGE_URL_BASE + itemType.toString() + ".jpg").toURI().toString());
        this.image = new ImageView(img);
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
