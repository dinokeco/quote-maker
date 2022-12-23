package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.QuoteManager;
import ba.unsa.etf.rpr.controllers.components.DoubleButtonCellFactory;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.exceptions.QuoteException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Date;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller for managing Quotes Entity
 * @author Dino Keco
 */
public class QuoteController {
    // managers
    private final QuoteManager quoteManager = new QuoteManager();

    // helper components
    @FXML
    public BorderPane quoteScreen;

    // components
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

        refreshQuotes();
    }

    /**
     * search quote event handler
     * @param event
     */
    public void searchQuotes(ActionEvent event){
        try {
            quotesTable.setItems(FXCollections.observableList(quoteManager.searchQuotes(search.getText())));
            quotesTable.refresh();
        } catch (QuoteException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Event handler for deletion of quote. It has confirm box before deletion
     * @param quoteId
     */
    public void deleteQuote(Integer quoteId){
        try {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (!result.get().getButtonData().isCancelButton()){
                quoteManager.delete(quoteId);
                refreshQuotes();
            }
        } catch (QuoteException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * Open form for editing or creating quote
     *
     * @param quoteId - only for edit if we know which quote is being edited.
     */
    public void editQuoteScene(Integer quoteId){
        try{
            ((Stage)quoteScreen.getScene().getWindow()).hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aoequote.fxml"));
            loader.setController(new AoUQuoteController(quoteId));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Edit Quote");
            stage.show();
            stage.setOnHiding(event -> {
                ((Stage)quoteScreen.getScene().getWindow()).show();
                refreshQuotes();
            });
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * fetch quotes from DB
     */
    private void refreshQuotes(){
        try {
            quotesTable.setItems(FXCollections.observableList(quoteManager.getAll()));
            quotesTable.refresh();
        } catch (QuoteException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
