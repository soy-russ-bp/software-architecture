module com.example.ada7_base {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ada7_base to javafx.fxml;
    exports com.example.ada7_base;
    exports com.example.ada7_base.controller;
    opens com.example.ada7_base.controller to javafx.fxml;
}