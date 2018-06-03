package com.hania.model;

import com.hania.process.ItemType;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class Item {

    private int number;

    private String name;

    private double price;

    private String imageURL;

    public Item(ItemType itemType, int number, ResourceBundle resourceBundle) {
        this.number = number;
        String pattern = resourceBundle.getString("item.zero.diode");
        this.name = MessageFormat.format(pattern, number);
    }

}
