package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Quote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ShowQuoteController {
    public TextArea quoteArea;
    public Button okBtn;
    public TextField createdField;
    private Quote quote;


    public ShowQuoteController(Quote quote) {
        this.quote = quote;
    }

    @FXML
    public void initialize() {
        quoteArea.setText(quote.getQuote());
        createdField.setText(quote.getCreated().toString());
    }

    public void okBtnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) quoteArea.getScene().getWindow();
        stage.close();
    }
}
