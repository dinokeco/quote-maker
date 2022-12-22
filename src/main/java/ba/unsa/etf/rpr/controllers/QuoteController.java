package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.QuoteManager;
import ba.unsa.etf.rpr.controllers.components.DoubleButtonCellFactory;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.exceptions.QuoteException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.Optional;

public class QuoteController {

    private final QuoteManager quoteManager = new QuoteManager();

    public TableView quotesTable;
    public TextField search;

    public TableColumn<Quote, String> idColumn;
    public TableColumn<Quote, String> quoteColumn;
    public TableColumn<Quote, Date> createdColumn;
    public TableColumn<Quote, Integer> actionColumn;

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("id"));
        quoteColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("quote"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<Quote, Date>("created"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("id"));

        actionColumn.setCellFactory(new DoubleButtonCellFactory(editEvent -> {
                int quoteId = Integer.parseInt(((Button)editEvent.getSource()).getUserData().toString());
                editQuoteScene(quoteId);
            }, (deleteEvent -> {
                int quoteId = Integer.parseInt(((Button)deleteEvent.getSource()).getUserData().toString());
                deleteQuote(quoteId);
        })));

        try {
            quotesTable.setItems(FXCollections.observableList(quoteManager.getAll()));
            quotesTable.refresh();
        } catch (QuoteException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void searchQuotes(ActionEvent event){
        try {
            quotesTable.setItems(FXCollections.observableList(quoteManager.searchQuotes(search.getText())));
            quotesTable.refresh();
        } catch (QuoteException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void deleteQuote(int quoteId){
        try {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (!result.get().getButtonData().isCancelButton()){
                quoteManager.delete(quoteId);
                quotesTable.setItems(FXCollections.observableList(quoteManager.getAll()));
                quotesTable.refresh();
            }
        } catch (QuoteException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void editQuoteScene(int quoteId){
        // TODO implement
    }

}
