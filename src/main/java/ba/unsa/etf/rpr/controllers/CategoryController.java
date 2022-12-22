package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.QuoteException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CategoryController {

    private CategoryManager manager = new CategoryManager();

    public ListView<Category> categoriesList;

    public TextField categoryName;

    @FXML
    public void initialize() {
        try {
            refreshCategories();
            categoriesList.getSelectionModel().selectedItemProperty().addListener((obs, o, n)->{
                if (n != null){
                    categoryName.setText(n.getName());
                }
            });
        } catch (QuoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategory(ActionEvent event){
        try {
            Category c = new Category();
            c.setName(categoryName.getText());
            c = manager.add(c);
            categoriesList.getItems().add(c);
            categoryName.setText("");
            //refreshCategories();
        }catch (QuoteException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void updateCategory(ActionEvent event){
        try {
            Category cat = categoriesList.getSelectionModel().getSelectedItem();
            cat.setName(categoryName.getText());
            cat = manager.update(cat);
            refreshCategories();
        }catch (QuoteException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteCategory(ActionEvent event){
        try {
            Category cat = categoriesList.getSelectionModel().getSelectedItem();
            manager.delete(cat.getId());
            //refreshCategories();
            categoriesList.getItems().remove(cat); // perf optimization
        }catch (QuoteException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    private void refreshCategories() throws QuoteException{
        try {
            categoriesList.setItems(FXCollections.observableList(manager.getAll()));
            categoryName.setText("");
        } catch (QuoteException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
