module com.example.cafeteria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.postgresql.jdbc;


    opens com.example.cafeteria to javafx.fxml;
    exports com.example.cafeteria;
}