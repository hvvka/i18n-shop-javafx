package com.hania.process;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public enum ItemType {
    DIODE(1.23),
    TRANSISTOR(2.4),
    CAPACITOR(5.6),
    INDUCTOR(6.3),
    MEMRISTOR(6.34),
    ANTENNA(6.30),
    OSCILLATOR(8.65),
    SENSOR(0.87),
    DETECTOR(8.43),
    TRANSDUCER(2.56),
    MOTOR(1.28),
    BATTERY(7.54);

    private double price;

    ItemType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
