module com.hugohirling.logicalcomponents {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.hugohirling.logicalcomponents to javafx.fxml;
    exports com.hugohirling.logicalcomponents;
}
