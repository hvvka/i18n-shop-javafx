package com.hania.controller;

import com.hania.Main;
import com.hania.model.Language;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class MainController implements Initializable {

    private static final Logger LOG = Logger.getLogger(MainController.class.getName());
    private static final String BUNDLE_NAME = "MessageBundle";

    private ResourceBundle resourceBundle;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ComboBox<Language> languageComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        languageComboBox.getItems().addAll(Language.values());
    }

    @FXML
    public void onShowProducts() {
        FXMLLoader loader = getFXMLLoader();
        try {
            rootPane = loader.load();
        } catch (IOException e) {
            LOG.throwing("MainController", "onShowProducts", e);
        }

        Main.setScene(rootPane);
    }

    private FXMLLoader getFXMLLoader() {
        FXMLLoader loader = new FXMLLoader();
        chooseResourceBundle(loader);
        loader.setLocation(getClass().getResource("../view/ItemsView.fxml"));
        return loader;
    }

    private void chooseResourceBundle(FXMLLoader loader) {
        Language selectedLanguage = languageComboBox.getSelectionModel().getSelectedItem();
        if (selectedLanguage == Language.PL) {
            resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, Language.PL.getLocale());
        } else if (selectedLanguage == Language.DE) {
            resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, Language.DE.getLocale());
        } else if (selectedLanguage == Language.EN) {
            resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, Language.EN.getLocale());
        }
        loader.setResources(resourceBundle);
    }
}
