package com.example.assesmentjava2.Controllers;

import com.example.assesmentjava2.DatabaseConnection;
import com.example.assesmentjava2.Models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn<Book, Integer> issnColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, Integer> publishYearColumn;

    @FXML
    private TableColumn<Book, String> authorsColumn;

    @FXML
    private TableColumn<Book, String> genreColumn;

    @FXML
    private TableColumn<Book, Boolean> isAvailableColumn;

    @FXML
    private TextField titleField;

    @FXML
    private TextField publishYearField;

    @FXML
    private TextField authorsField;

    @FXML
    private TextField genreField;

    @FXML
    private CheckBox isAvailableCheckbox;

    private DatabaseConnection db = new DatabaseConnection();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void createNewEmpl(ActionEvent event) {
        // Получаем значения из текстовых полей и флажка
        String title = titleField.getText();
        int publishYear = Integer.parseInt(publishYearField.getText());
        String authors = authorsField.getText();
        String genre = genreField.getText();
        boolean isAvailable = isAvailableCheckbox.isSelected();

        // Создаем новую книгу
        Book newBook = new Book(0, title, publishYear, authors, genre, isAvailable);

        // Добавляем книгу в базу данных
        db.addBook(newBook);

        // Очищаем текстовые поля
        titleField.clear();
        publishYearField.clear();
        authorsField.clear();
        genreField.clear();
        isAvailableCheckbox.setSelected(false);
    }

    public void loadUserToForm(ActionEvent actionEvent) {
        List<Book> books = db.getAllBooks();
        ObservableList<Book> bookList = FXCollections.observableArrayList(books);

        issnColumn.setCellValueFactory(new PropertyValueFactory<>("issn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        publishYearColumn.setCellValueFactory(new PropertyValueFactory<>("publishYear"));
        authorsColumn.setCellValueFactory(new PropertyValueFactory<>("authors"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        isAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        table.setItems(bookList);
    }

    public void disconnectAction(ActionEvent actionEvent) {
        db.disconnect();
        table.getItems().clear();
    }
}
