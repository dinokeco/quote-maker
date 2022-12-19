package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.QuoteException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CategoryController {

    public ListView<Category> categoriesList;

    public TextField categoryName;

    @FXML
    public void initialize() {
        try {
            refreshCategories();
        } catch (QuoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategory(ActionEvent event){
        try {
            Category c = new Category();
            c.setName(categoryName.getText());
            DaoFactory.categoryDao().add(c);
            refreshCategories();
        }catch (QuoteException e){
            throw new RuntimeException(e);
        }
    }

    public void updateCategory(ActionEvent event){

    }

    public void deleteCategory(ActionEvent event){
        try {
            Category cat = categoriesList.getSelectionModel().getSelectedItem();
            DaoFactory.categoryDao().delete(cat.getId());
            refreshCategories();
        }catch (QuoteException e){
            throw new RuntimeException(e);
        }
    }

    private void refreshCategories() throws QuoteException{
        try {
            List<Category> categories = DaoFactory.categoryDao().getAll();
            categoriesList.setItems(FXCollections.observableList(categories));
        } catch (QuoteException e) {
            throw new RuntimeException(e);
        }
    }
}
