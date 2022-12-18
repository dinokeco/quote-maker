package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.CategoryDao;
import ba.unsa.etf.rpr.dao.CategoryDaoSQLImpl;
import ba.unsa.etf.rpr.dao.QuoteDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.exceptions.QuoteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {
    public ListView<Category> categoryListView;
    public TableView<Quote> quotesTableView;
    public TableColumn<Category, String> colQuoteId;
    public TableColumn<Category, String> colQuoteText;
    public TableColumn<Category, String> colQuoteDate;
    public Button showQuoteBtn;
    private CategoryDaoSQLImpl categoryDaoSQL;
    private QuoteDaoSQLImpl quoteDaoSQL;
    private ObservableList<Category> categories;
    private ObservableList<Quote> quotes;


    /**
     *
     */
    public HomeController() {
        try {
            categoryDaoSQL = new CategoryDaoSQLImpl();
            quoteDaoSQL = new QuoteDaoSQLImpl();
            categories = FXCollections.observableArrayList(categoryDaoSQL.getAll());
            quotes = FXCollections.observableArrayList();
        } catch (QuoteException e) {
            System.out.println("Something is not right with table of categories");
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        categoryListView.setItems(categories);
        colQuoteId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colQuoteText.setCellValueFactory(new PropertyValueFactory<>("quote"));
        colQuoteDate.setCellValueFactory(new PropertyValueFactory<>("created"));
        categoryListView.getSelectionModel().selectedItemProperty().addListener((obs, oldCat, newCat) -> {
            try {
                quotesTableView.getItems().clear();
                quotes.addAll(quoteDaoSQL.searchByCategory(newCat));
                quotesTableView.refresh();
                quotesTableView.setItems(quotes);
            } catch (QuoteException e) {
                System.out.println("Something went wrong with searchByCategory method from quoteDaoSQlImpl");
                throw new RuntimeException(e);
            }
        });
    }

    public void onActionShowQuote(ActionEvent actionEvent) {
        Quote quote = quotesTableView.getSelectionModel().getSelectedItem();
        if (quote == null)
            return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/show-quote.fxml"));
            ShowQuoteController showQuoteController = new ShowQuoteController(quote);
            loader.setController(showQuoteController);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Show qoute page");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void onActionAddQuote(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add-quote.fxml"));
            AddQuoteController addQuoteController = new AddQuoteController(categoryListView.getItems(), quoteDaoSQL);
            loader.setController(addQuoteController);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Add quote page");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onActionAddCategory(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add-category.fxml"));
        AddCategoryController controller = new AddCategoryController();
        loader.setController(controller);
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Add category");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            System.out.println("Nemoguce ucitati loader!");
            System.out.println(e.getMessage());
        }


        stage.setOnHiding(lambda -> {
            Category category = controller.getCategory();
            if (category != null) {
               categories.add(category);
               categoryListView.refresh();
            }
        });
    }

    public void openAbout(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/abou1t.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Add category");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }
}
