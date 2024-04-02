module com.example.assesmentjava2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.assesmentjava2.Models to javafx.base;
    opens com.example.assesmentjava2 to javafx.fxml;
    exports com.example.assesmentjava2;
    exports com.example.assesmentjava2.Controllers;
    opens com.example.assesmentjava2.Controllers to javafx.fxml;
}