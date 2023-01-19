module petrizzi.c482 {
    requires javafx.controls;
    requires javafx.fxml;
    requires annotations;


    opens petrizzi.c482 to javafx.fxml, javafx.base;
    opens petrizzi.c482.Models to javafx.fxml, javafx.base;
    exports petrizzi.c482;
}