package com.example.assesmentjava2;
import java.util.ArrayList;
import java.util.List;
import com.example.assesmentjava2.Models.Book;

import java.sql.*;

public class DatabaseConnection {
    private String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "database=assesment2;"
                    + "user=sa;"
                    + "password=Password.1;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";
    private Connection connection; // Добавлено поле для хранения соединения

    // Метод для подключения к базе данных
    public void connect() {
        try {
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    // Метод для отключения от базы данных
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error while disconnecting from the database: " + e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {

            String selectSql = "SELECT * FROM Book";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                int issn = resultSet.getInt("Issn");
                String title = resultSet.getString("Title");
                int publishYear = resultSet.getInt("PublishYear");
                String authors = resultSet.getString("Authors");
                String genre = resultSet.getString("Genre");
                boolean isAvailable = resultSet.getBoolean("IsAvaliable");

                Book book = new Book(issn, title, publishYear, authors, genre, isAvailable);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public void addBook(Book book) {
        // Параметризированный SQL-запрос для вставки данных в таблицу Book
        String insertSql = "INSERT INTO Book (Title, PublishYear, Authors, Genre, IsAvaliable) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            // Устанавливаем значения параметров в запросе
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getPublishYear());
            preparedStatement.setString(3, book.getAuthors());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setBoolean(5, book.isAvailable());

            // Выполняем SQL-запрос для вставки данных
            preparedStatement.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding book to the database: " + e.getMessage());
        }
    }

    public List<Book> selectBooksByDateRange(Date startDate, Date endDate) {
        List<Book> books = new ArrayList<>();
        String selectSql = "SELECT * FROM Book WHERE PublishYear BETWEEN ? AND ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int issn = resultSet.getInt("Issn");
                String title = resultSet.getString("Title");
                int publishYear = resultSet.getInt("PublishYear");
                String authors = resultSet.getString("Authors");
                String genre = resultSet.getString("Genre");
                boolean isAvailable = resultSet.getBoolean("IsAvailable");

                Book book = new Book(issn, title, publishYear, authors, genre, isAvailable);
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Error selecting books by date range from the database: " + e.getMessage());
        }

        return books;
    }
    public List<Book> selectAvailableBooks(boolean isAvailable) {
        List<Book> books = new ArrayList<>();
        String selectSql = "SELECT * FROM Book WHERE IsAvailable = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setBoolean(1, isAvailable);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int issn = resultSet.getInt("Issn");
                String title = resultSet.getString("Title");
                int publishYear = resultSet.getInt("PublishYear");
                String authors = resultSet.getString("Authors");
                String genre = resultSet.getString("Genre");
                boolean isAvailableFromDb = resultSet.getBoolean("IsAvailable");

                Book book = new Book(issn, title, publishYear, authors, genre, isAvailableFromDb);
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Error selecting available books from the database: " + e.getMessage());
        }

        return books;
    }
    public List<Book> selectBooksByGenre(String genre) {
        List<Book> books = new ArrayList<>();
        String selectSql = "SELECT * FROM Book WHERE Genre = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, genre);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int issn = resultSet.getInt("Issn");
                String title = resultSet.getString("Title");
                int publishYear = resultSet.getInt("PublishYear");
                String authors = resultSet.getString("Authors");
                String genreFromDb = resultSet.getString("Genre");
                boolean isAvailable = resultSet.getBoolean("IsAvailable");

                Book book = new Book(issn, title, publishYear, authors, genreFromDb, isAvailable);
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Error selecting books by genre from the database: " + e.getMessage());
        }

        return books;
    }

}
