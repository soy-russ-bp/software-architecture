module cliente{
    requires comun;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires java.desktop;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    

    opens ada8.cliente to javafx.fxml;
    opens ada8.cliente.controller to javafx.fxml;

    exports ada8.cliente;
    exports ada8.cliente.controller;
    exports ada8.cliente.data_model;
    
}