package ba.unsa.etf.rpr.controllers.components;

import ba.unsa.etf.rpr.domain.Quote;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class DoubleButtonTableCell<T> extends TableCell<T, T> {

    private Button edit;
    private Button delete;

    public DoubleButtonTableCell(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo){
        edit = new Button("Edit");
        edit.setOnAction(buttonOne);
        delete = new Button("Delete");
        delete.setOnAction(buttonTwo);
    }

    @Override
    protected void updateItem(T o, boolean b) {
        super.updateItem(o, b);
        if (o != null) {
            HBox box = new HBox();
            edit.setUserData(o);
            delete.setUserData(o);
            box.getChildren().add(edit);
            box.getChildren().add(delete);
            setGraphic(box);
        }
    }
}

