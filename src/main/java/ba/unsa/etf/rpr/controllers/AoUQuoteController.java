package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.QuoteManager;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.exceptions.QuoteException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.Date;
import java.time.LocalDate;

/**
 * JavaFX controller for creation and alteration of Quote object
 *
 * @author Dino Keco
 */
public class AoUQuoteController {
    // helper components
    @FXML
    public GridPane aouQuotePane;

    // managers
    private final CategoryManager categoryManager = new CategoryManager();
    private final QuoteManager quoteManager = new QuoteManager();

    // model
    private QuoteModel model = new QuoteModel();

    // id of quote that is edited
    private Integer editQuoteId;

    // form fields
    public TextArea quote;
    public DatePicker created;
    public ComboBox<Category> categoryId;

    public AoUQuoteController(Integer editQuoteId){
        this.editQuoteId = editQuoteId;
    }

    public void initialize(){
        try{
            categoryId.setItems(FXCollections.observableList(categoryManager.getAll()));
            quote.textProperty().bindBidirectional(model.quote);
            created.valueProperty().bindBidirectional(model.created);
            categoryId.valueProperty().bindBidirectional(model.category);
            if (editQuoteId != null) {
                model.fromQuote(quoteManager.getById(editQuoteId));
            }
        }catch (QuoteException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * cancel button event handler
     * @param event
     */
    public void cancelAoUForm(ActionEvent event){
        aouQuotePane.getScene().getWindow().hide();
    }

    /**
     * save button event handler (update and add quote)
     * @param event
     */
    public void saveAoUForm(ActionEvent event){
        try{
            Quote q = model.toQuote();
            if (editQuoteId != null){
                q.setId(editQuoteId);
                quoteManager.update(q);
            }else{
                quoteManager.add(q);
            }
            aouQuotePane.getScene().getWindow().hide();
        }catch (QuoteException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


    /**
     * Helper Model class that supports 2 way data binding with form for Quote management
     * @author Dino Keco
     *
     */
    public class QuoteModel{
        public SimpleStringProperty quote = new SimpleStringProperty("");
        public SimpleObjectProperty<LocalDate> created = new SimpleObjectProperty<LocalDate>();
        public SimpleObjectProperty<Category> category = new SimpleObjectProperty<Category>();

        public void fromQuote(Quote q){
            this.quote.set(q.getQuote());
            this.created.set(((Date)q.getCreated()).toLocalDate());
            this.category.set(q.getCategory());
        }

        public Quote toQuote(){
            Quote q = new Quote();
            q.setQuote(this.quote.getValue());
            q.setCreated(Date.valueOf(this.created.getValue()));
            q.setCategory(this.category.getValue());
            return q;
        }
    }
}
