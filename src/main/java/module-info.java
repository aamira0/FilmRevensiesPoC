module org.example.poc {
    requires javafx.controls;
    requires javafx.fxml;
    requires weka.stable;


    opens org.example.poc to javafx.fxml;
    exports org.example.poc;
    exports org.example.poc.ModelTraining;
    opens org.example.poc.ModelTraining to javafx.fxml;
}