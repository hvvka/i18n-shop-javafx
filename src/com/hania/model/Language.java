package com.hania.model;

import java.util.Locale;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public enum Language {

    PL("PL"),
    EN("GB"),
    DE("DE");

    private String country;

    Language(String country) {
        this.country = country;
    }

    public Locale getLocale() {
        return new Locale(this.name().toLowerCase(), this.country);
    }
}
