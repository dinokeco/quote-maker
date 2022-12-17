package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.QuoteDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.exceptions.QuoteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AddQuoteController {

    public ChoiceBox<Category> choiceCatId;
    public TextField fldAddQuote;
    public DatePicker fldDatePickerId;
    private ObservableList<Category> observableList;
    private QuoteDaoSQLImpl quoteDaoSQL;
    public AddQuoteController(List<Category> categoryList, QuoteDaoSQLImpl quoteDaoSQL) {
        observableList = FXCollections.observableArrayList(categoryList);
        this.quoteDaoSQL = quoteDaoSQL;
    }

    @FXML
    public void initialize() {
        choiceCatId.setItems(observableList);
    }

    public void onActionAddQuote(ActionEvent actionEvent) {
        Quote newQuote = new Quote();
        try {
            newQuote.setCategory(choiceCatId.getSelectionModel().getSelectedItem());
            newQuote.setQuote(fldAddQuote.getText());
            if(fldDatePickerId == null)
                newQuote.setCreated(Date.valueOf(LocalDate.now()));
            else
                newQuote.setCreated(Date.valueOf(fldDatePickerId.getValue()));
            quoteDaoSQL.add(newQuote);
            Stage stage = (Stage) fldAddQuote.getScene().getWindow();
            stage.close();
        } catch (QuoteException e) {
            System.out.println("Something went wrong in onActionAddQuote method");
            throw new RuntimeException(e);
        }
    }
}
