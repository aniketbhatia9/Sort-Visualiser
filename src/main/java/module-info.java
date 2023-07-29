module com.example.sort_visualiser {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sort_visualiser to javafx.fxml;
    exports com.example.sort_visualiser;
}