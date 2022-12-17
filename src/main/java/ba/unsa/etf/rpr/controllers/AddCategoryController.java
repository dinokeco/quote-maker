package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.CategoryDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.QuoteException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCategoryController {
    public Button okBttn;
    public TextField categoryNameField;
    private CategoryDaoSQLImpl dao = new CategoryDaoSQLImpl();
    private Category category;

    public void okBttnAction(ActionEvent actionEvent) {
        /**
         * When we added a new category on our window, we have to insert that category in the database.
         * Also, we have to update our category list on main windows (source is in the home-layout.fxml file),
         * and for that purpose we will use setOnHiding method that will be explained on the next tutorial.
         */

        //First we have to insert a new category in the database
        //We have to have one instance of CategoryDaoSqlImpl class and we have to call method for inserting a new category in our database
       category = new Category();
        category.setName(categoryNameField.getText());
        try {
            dao.add(category);
        } catch (QuoteException e) {
            System.out.println("Problem with adding a new category in the database");
            throw new RuntimeException(e);
        }
        //Ovdje bi trebali da dodamo ako je prazno polje da izbaci error al nema veze
        Stage stage = (Stage) okBttn.getScene().getWindow();
        stage.close();
    }

    public Category getCategory(){
        return category;
    }
}
