module com.example.ada7_base {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires java.desktop;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.ada7_base to javafx.fxml;
    opens com.example.ada7_base.controller to javafx.fxml;

    exports com.example.ada7_base;
    exports com.example.ada7_base.controller;
    exports com.example.ada7_base.data_model;
    
}