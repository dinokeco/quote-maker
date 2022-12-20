package ba.unsa.etf.rpr.controllers;

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

import java.util.List;

public class CategoryController {

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
            DaoFactory.categoryDao().add(c);
            refreshCategories();
        }catch (QuoteException e){
            throw new RuntimeException(e);
        }
    }

    public void updateCategory(ActionEvent event){
        try {
            Category cat = categoriesList.getSelectionModel().getSelectedItem();
            cat.setName(categoryName.getText());
            DaoFactory.categoryDao().update(cat);
            refreshCategories();
        }catch (QuoteException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteCategory(ActionEvent event){
        try {
            Category cat = categoriesList.getSelectionModel().getSelectedItem();
            DaoFactory.categoryDao().delete(cat.getId());
            refreshCategories();
        }catch (QuoteException e){
            String msg = e.getMessage();
            if (msg.contains("FOREIGN KEY")){
                msg = "Cannot delete category which is related to quotes. First delete related quotes before deleting category.";
            }
            new Alert(Alert.AlertType.NONE, msg, ButtonType.OK).show();
        }
    }

    private void refreshCategories() throws QuoteException{
        try {
            List<Category> categories = DaoFactory.categoryDao().getAll();
            categoriesList.setItems(FXCollections.observableList(categories));
            categoryName.setText("");
        } catch (QuoteException e) {
            throw new RuntimeException(e);
        }
    }
}
