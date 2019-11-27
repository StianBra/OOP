module no.ntnu.imt3281.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens no.ntnu.imt3281.javafx to javafx.fxml;
    exports no.ntnu.imt3281.javafx;
}