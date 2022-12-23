package ba.unsa.etf.rpr.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    @FXML
    public void initialize() {
    }

    public void closeApp(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }
    public void openAbout(ActionEvent actionEvent){
        openDialog("About", "/fxml/about.fxml", null);
    }

    public void openEditCategories(ActionEvent event){
        openDialog("Manage Categories", "/fxml/category.fxml", new CategoryController());
    }

    public void openEditQuotes(ActionEvent event){
        openDialog("Manage Quotes", "/fxml/quote.fxml", new QuoteController());
    }

    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Error");
            alert.show();
        }
    }
}
