module org.example.poc {
    requires javafx.controls;
    requires javafx.fxml;
    requires weka;


    opens org.example.poc to javafx.fxml;
    exports org.example.poc;
}