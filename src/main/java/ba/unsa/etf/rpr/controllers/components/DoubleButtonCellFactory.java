package ba.unsa.etf.rpr.controllers.components;

import ba.unsa.etf.rpr.domain.Quote;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DoubleButtonCellFactory<T> implements Callback<TableColumn<T, T>, TableCell<T, T>> {

    private EventHandler<ActionEvent> buttonOne;

    private EventHandler<ActionEvent> buttonTwo;

    public DoubleButtonCellFactory(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo){
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }
    /**
     * @param quoteObjectTableColumn
     * @return
     */
    @Override
    public TableCell<T, T> call(TableColumn<T, T> quoteObjectTableColumn) {
        return new DoubleButtonTableCell<T>(buttonOne, buttonTwo);
    }
}
