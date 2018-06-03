package com.hania.model;

import com.hania.process.ItemType;
import javafx.scene.image.ImageView;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class Item {

    private static final String IMAGE_URL_BASE = "resources/items/";

    private int number;

    private String name;

    private String price;

    private ImageView image;

    public Item() {
    }

    public Item(ItemType itemType, int number, ResourceBundle resourceBundle) {
        this.number = number;
        String pattern = resourceBundle.getString("item." + itemType.toString().toLowerCase());
        this.name = MessageFormat.format(pattern, number);
        this.price = MessageFormat.format(resourceBundle.getString("item.price"), itemType.getPrice());
        this.image = new ImageView(IMAGE_URL_BASE + itemType.toString());
    }

}
