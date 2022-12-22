package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.exceptions.QuoteException;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.util.Date;

public class QuoteController {

    public TableView quotesTable;

    public TableColumn<Quote, String> idColumn;
    public TableColumn<Quote, String> quoteColumn;
    public TableColumn<Quote, Date> createdColumn;
    public TableColumn<Quote, Object> actionColumn;

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("id"));
        quoteColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("quote"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<Quote, Date>("created"));

        actionColumn.setCellFactory(new Callback<TableColumn<Quote, Object>, TableCell<Quote, Object>>() {
            @Override
            public TableCell<Quote, Object> call(TableColumn<Quote, Object> quoteObjectTableColumn) {
                return new DoubleButtonCell();
            }
        });

        try {
            quotesTable.setItems(FXCollections.observableList(DaoFactory.quoteDao().getAll()));
        } catch (QuoteException e) {
            throw new RuntimeException(e);
        }
        System.out.println("quotes");
    }

    public void searchQuotes(ActionEvent event){
        System.out.println("search");
    }

    public class DoubleButtonCell extends TableCell<Quote, Object>{
        private Button edit;
        private Button delete;

        public DoubleButtonCell(){
            edit = new Button("Edit");
            delete = new Button("Delete");
        }

        @Override
        protected void updateItem(Object o, boolean b) {
            super.updateItem(o, b);
            HBox box = new HBox();
            box.getChildren().add(edit);
            box.getChildren().add(delete);
            setGraphic(box);
        }
    }
}
